package dev.dinesh.tochat.core;

import java.awt.Color;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

import javax.swing.JTextArea;

import dev.dinesh.tochat.core.listener.ChatBroker;
import dev.dinesh.tochat.core.listener.ChatListener;
import dev.dinesh.tochat.gui.chatonents.ChatArea;
import dev.dinesh.tochat.util.ProgramVars;

/**
 * @author dinesh
 *
 */
public class Client implements Runnable, ChatBroker {
	private int serverPort;
	private InetAddress serverAddress = null;
	private int clientPort = 999;
	private int buffer_size = 1024;
	private DatagramSocket ds;
	private byte[] buffer = new byte[getBuffer_size()];
	private Vector<Chatter> chatters = new Vector<Chatter>();
	private Thread t;
	private JTextArea debugOutputTextArea = new JTextArea();
	private ChatArea currentChatArea = new ChatArea();
	private ArrayList<ChatListener> chatListeners = new ArrayList<ChatListener>();
	public JTextArea getDebugOutputTextArea() {
		return this.debugOutputTextArea;
	}

	public Client(int gclientPort, int gserverPort, InetAddress gserverAddress) {
		System.out.println("gc;linet" + gclientPort);
		setClientPort(gclientPort);
		setServerAddress(gserverAddress);
		setServerPort(gserverPort);
		try {
			System.out.println(getClientPort());
			this.ds = new DatagramSocket(getClientPort());
		} catch (SocketException e) {
			e.printStackTrace();
		}
		this.debugOutputTextArea.setBackground(Color.BLACK);
		this.debugOutputTextArea.setForeground(Color.WHITE);
		this.debugOutputTextArea.setEditable(false);
	}

	public Client(int gclientport, int gserverport, InetAddress gserverAddress,
			int gbuffer_size) {
		this(gclientport, gserverport, gserverAddress);

		setBuffer_size(gbuffer_size);
	}

	public void clientStart() {
		this.t = new Thread(this);
		this.t.start();
	}

	private void addChatter(DatagramPacket p) {
		String gName = new String(p.getData(), 0,
				ProgramVars.getUserNameCharLim());
		this.chatters.add(new Chatter(p.getAddress(), p.getPort(), gName.trim()));
		System.out.println(gName+" added");
		this.debugOutputTextArea.append("\n-> New Chatter Added name:" + gName
				+ " address:" + p.getAddress() + " port:" + p.getPort() + " @"
				+ new Date());
	}

	private boolean isNewChatter(DatagramPacket p) {
		
		String gName = new String(p.getData(), 0,
				ProgramVars.getUserNameCharLim());
		Chatter chatter = new Chatter(p.getAddress(), p.getPort(), gName.trim());
		System.out.println("check for"+chatter.getPort()+""+chatter.getName());
		if (chatters.contains(chatter)) {
			return false;
		}

		return true;
	}

	@SuppressWarnings("deprecation")
	public void clientStop() {
		this.t.stop();
		this.ds.close();
		this.debugOutputTextArea.append("\n-> server stopped @" + new Date());
	}

	public void sendTo(String msgwithfrom) {
		byte[] buffer = msgwithfrom.getBytes();
		System.out.println(buffer);
		DatagramPacket p = new DatagramPacket(buffer, msgwithfrom.length(),
				getServerAddress(), getServerPort());
		System.out.println(p.getData() + " " + getServerAddress());
		try {
			this.ds.send(p);
			System.out.println("sendeded packet :" + p.getData());
		} catch (IOException e) {
			this.debugOutputTextArea.append("\n-> Error on sending to server @"
					+ new Date());
			e.printStackTrace();
		}
	}

	public int getServerPort() {
		return this.serverPort;
	}

	public void setServerPort(int gserverport) {
		this.serverPort = gserverport;
	}

	public int getBuffer_size() {
		return this.buffer_size;
	}

	public void setBuffer_size(int gbuffer_size) {
		this.buffer_size = gbuffer_size;
	}

	public ChatArea getCurrentChatArea() {
		return this.currentChatArea;
	}

	public void setCurrentChatArea(ChatArea currentChatArea) {
		this.currentChatArea = currentChatArea;
	}

	public InetAddress getServerAddress() {
		return this.serverAddress;
	}

	public void setServerAddress(InetAddress serverAddress) {
		this.serverAddress = serverAddress;
	}

	public int getClientPort() {
		return this.clientPort;
	}

	public void setClientPort(int clientPort) {
		this.clientPort = clientPort;
	}

	public void run() {
		System.out.println("running @" + new Date());
		this.debugOutputTextArea.append("\n-> running @" + new Date());
		while (true) {
			DatagramPacket incoming = new DatagramPacket(this.buffer,
					this.buffer.length);
			try {
				this.ds.receive(incoming);
				this.debugOutputTextArea.append("\n-> received from :"
						+ incoming.getAddress() + " @" + new Date());
				if (isNewChatter(incoming)) {
					this.debugOutputTextArea
							.append("\n-> New Chatter Found on address :"
									+ incoming.getAddress() + " @" + new Date());
					System.out.println("\n-> New Chatter Found on address :"
									+ incoming.getAddress() + " @" + new Date());
					addChatter(incoming);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			String from = new String(incoming.getData(), 0,
					ProgramVars.getUserNameCharLim());
			String msg = new String(incoming.getData(),
					ProgramVars.getUserNameCharLim(), incoming.getLength());
			
			//this.currentChatArea.appendMessage(from, msg);
			Chatter chatter = getChatterForName(from);
			if (chatter!=null) {
				System.out.println("Notified");
				notifyForMessageReceived(new Message(msg, chatter));
			}
			
			System.out.println("mesage = "+msg);
			this.buffer = new byte[getBuffer_size()];
		}
	}
	private Chatter getChatterForName(String name) {
		for (Chatter chatter : chatters) {
			System.out.println("chatter name:"+chatter.getName()+"----"+name+"^");
			if (chatter.getName().trim().equals(name.trim())) {
				return chatter;
			}
		}
		return null;
	}
	@Override
	public void addChatListener(ChatListener chatListener) {
		chatListeners.add(chatListener);
	}

	@Override
	public void removeChatListener(ChatListener chatListener) {
		chatListeners.remove(chatListener);
	}

	@Override
	public void notifyForMessageReceived(Message message) {
		for (ChatListener listener : chatListeners) {
			listener.onMessageReceived(message);
		}
	}

	@Override
	public void notifyOnNewChatter(Chatter c) {

	}
}