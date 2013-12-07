package dev.dinesh.tochat.core;

import java.awt.Color;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.JTextArea;

import dev.dinesh.tochat.core.listener.ChatBroker;
import dev.dinesh.tochat.core.listener.ChatListener;
import dev.dinesh.tochat.util.ProgramVars;

public class Server
implements Runnable,ChatBroker
{
	private static int serverPort = 998;

	private static int buffer_size = 1024;
	private DatagramSocket ds;
	private static byte[] buffer = new byte[getBuffer_size()];
	private Vector<Chatter> chatters = new Vector<Chatter>();
	private Thread t;
	private JTextArea debugOutputTextArea = new JTextArea();
	private ArrayList<ChatListener> chatListeners = new ArrayList<ChatListener>();
	public JTextArea getDebugOutputTextArea()
	{
		return this.debugOutputTextArea;
	}

	public Server(int gserverPort)
	{
		serverPort = gserverPort;
		this.debugOutputTextArea.setBackground(Color.BLACK);
		this.debugOutputTextArea.setForeground(Color.WHITE);
		this.debugOutputTextArea.setEditable(false);
	}
	public Server(int gserverPort, int gbuffer_size) {
		this(gserverPort);
		setBuffer_size(gbuffer_size);
	}

	public void serverStart() {
		try {
			this.ds = new DatagramSocket(serverPort);
		}
		catch (SocketException e) {
			e.printStackTrace();
		}
		this.t = new Thread(this);
		this.t.start();
	}
	private void addChatter(DatagramPacket p) {
		String gName = new String(p.getData(), 0, ProgramVars.getUserNameCharLim());
		Chatter chatter = new Chatter(p.getAddress(), p.getPort(), gName.trim());
		this.chatters.add(chatter);
		this.debugOutputTextArea.append("\n-> New Chatter Added name:"+ chatter + " @" + new Date());
	}
	private boolean isNewChatter(DatagramPacket p) {
		
		String gName = new String(p.getData(), 0,
				ProgramVars.getUserNameCharLim());
		Chatter chatter = new Chatter(p.getAddress(), p.getPort(), gName.trim());
		if (chatters.contains(chatter)) {
			return false;
		}

		return true;
	}

	@SuppressWarnings("deprecation")
	public void serverStop() {
		this.t.stop();
		this.ds.close();
		this.debugOutputTextArea.append("\n-> server stopped @" + new Date());
	}

	private void brodcast(DatagramPacket p) {
		this.debugOutputTextArea.append("\n-> BroadCasting... @" + new Date());
		for (Iterator<Chatter> iterator = this.chatters.iterator(); iterator.hasNext(); ) {
			Chatter chatter = (Chatter)iterator.next();
			sendTo(chatter, p);
		}
	}

	private void sendTo(Chatter chatter, DatagramPacket p) {
		try {
			p.setAddress(chatter.getAddress());
			p.setPort(chatter.getPort());
			this.ds.send(p);
			this.debugOutputTextArea.append("\n-> send to :" + chatter.getName() + " @" + new Date());
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public static int getServerPort()
	{
		return serverPort;
	}

	public static void setServerPort(int serverPort)
	{
		Server.serverPort = serverPort;
	}

	public static int getBuffer_size()
	{
		return buffer_size;
	}

	public static void setBuffer_size(int buffer_size)
	{
		Server.buffer_size = buffer_size;
	}

	public void run() {
		System.out.println("running on port " + getServerPort() + " @" + new Date());

		this.debugOutputTextArea.append("\n-> running @" + new Date());
		while (true)
		{
			DatagramPacket incoming = new DatagramPacket(buffer, buffer.length);
			System.out.println("buffer len : " + buffer.length);
			try {
				System.out.println("data=" + incoming.getData() + " ds_address:" + this.ds.getInetAddress());

				this.ds.receive(incoming);

				String msg = new String(incoming.getData());
				System.out.println("in server :" + msg);

				System.out.println("data=" + incoming.getData().toString() + " packect_len=" + incoming.getLength());

				this.debugOutputTextArea.append("\n-> received from :" + incoming.getAddress() + " @" + new Date());

				if (isNewChatter(incoming)) {
					this.debugOutputTextArea.append("\n-> New Chatter Found on address :" + incoming.getAddress() + " @" + new Date());
					addChatter(incoming);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			brodcast(incoming);
			buffer = new byte[getBuffer_size()];
		}
	}

	@Override
	public void addChatListener(ChatListener cl) {
		chatListeners.add(cl);
	}

	@Override
	public void removeChatListener(ChatListener cl) {
		chatListeners.remove(cl);
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