package dev.dinesh.tochat.gui.chatonents;

import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import dev.dinesh.tochat.core.Client;
import dev.dinesh.tochat.core.Message;
import dev.dinesh.tochat.core.listener.ChatListener;
import dev.dinesh.tochat.util.ProgramVars;

/**
 * @author dinesh
 *
 */
public class ChatFrame extends JFrame implements ActionListener, KeyListener,
		ChatListener {
	private static final long serialVersionUID = 1L;
	private JScrollPane jsp;
	private ChatArea ca;
	private ChatTextBox ctb;
	private JButton sendButton;
	private Client client = null;

	public ChatFrame(int gclientport, int gserverPort, String serverip,
			int gbuffer_size) {
		super("T-O-C-H-A-T Chatbox [user:" + ProgramVars.getUSER_NAME()
				+ "][port:" + gclientport + "]");
		try {
			System.out.println("chatframe client:" + gclientport);
			this.client = new Client(gclientport, gserverPort,
					InetAddress.getByName(serverip), gbuffer_size);
			this.client.clientStart();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		setFrameProperty();
		initControls();
		addControls();
		setVisible(true);
	}

	private void addControls() {
		JPanel containPanel = new JPanel() {
			private static final long serialVersionUID = 1L;

			public Insets getInsets(Insets insets) {
				return new Insets(25, 5, 5, 5);
			}
		};
		containPanel.setLayout(null);
		this.jsp.setLocation(10, 10);
		this.ctb.setLocation(10, 500);
		this.sendButton.setLocation(500, 500);
		containPanel.add(this.jsp);
		containPanel.add(this.ctb);
		containPanel.add(this.sendButton);
		add(containPanel);
	}

	private void initControls() {
		int cawidth = 580;
		int caheight = 480;
		this.ca = new ChatArea(cawidth, caheight);

		//this.client.setCurrentChatArea(this.ca);
		client.addChatListener(this);
		this.ctb = new ChatTextBox();
		this.sendButton = new JButton("Send");
		this.ctb.setSize(cawidth - 100, 70);
		this.sendButton.setSize(90, 60);
		this.jsp = new JScrollPane(this.ca);
		this.jsp.setHorizontalScrollBarPolicy(30);
		this.jsp.setVerticalScrollBarPolicy(20);
		this.jsp.setSize(cawidth, caheight);
		this.sendButton.setEnabled(false);

		getRootPane().setDefaultButton(this.sendButton);

		this.sendButton.addActionListener(this);
		this.ctb.addKeyListener(this);
	}

	private void setFrameProperty() {
		setSize(620, 610);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(3);
	}

	public void actionPerformed(ActionEvent ae) {
		Object o = ae.getSource();
		if (o == this.sendButton) {
			System.out.println("IN chatframe : " + this.ctb.getText());

			this.client.sendTo(ProgramVars.getQualifiedName(ProgramVars
					.getUSER_NAME()) + this.ctb.getText());

			this.ctb.setText("");
		}
	}

	public void keyPressed(KeyEvent arg0) {
	}

	public void keyReleased(KeyEvent ke) {
		Object o = ke.getSource();
		if (o == this.ctb) {
			this.sendButton.setEnabled(this.ctb.getText().trim().length() != 0);
		}
	}

	public void keyTyped(KeyEvent ke) {
	}

	@Override
	public void onMessageReceived(Message message) {
		ca.appendMessage(message.getAuthor().getName(), message.getMessage());

	}
}