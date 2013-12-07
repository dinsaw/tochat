package dev.dinesh.tochat.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import dev.dinesh.tochat.core.Message;
import dev.dinesh.tochat.core.Server;
import dev.dinesh.tochat.core.listener.ChatListener;

/**
 * @author dinesh
 *
 */
public class HostFrame extends JFrame implements ActionListener, ChatListener {
	private static final long serialVersionUID = 1L;
	private Server cserver;
	private JButton startButton;
	private JButton stopButton;
	private JTextArea debugOutput;
	private JScrollPane jspDebug;

	public HostFrame(int gserverPort, int gbuffer_size) {
		super("Host Frame [port:" + gserverPort + "]");
		System.out.println("gserverport" + gserverPort);
		this.cserver = new Server(gserverPort, gbuffer_size);
		setFrameProperty();
		initControls();
		addControls();
	}

	private void setFrameProperty() {
		setSize(800, 800);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(3);
		setVisible(true);
	}

	private void initControls() {
		this.startButton = new JButton("Start Server");
		this.stopButton = new JButton("Stop Server");
		this.debugOutput = this.cserver.getDebugOutputTextArea();
		
		this.jspDebug = new JScrollPane(this.debugOutput, 22, 31);

		this.startButton.setSize(200, 40);
		this.stopButton.setSize(200, 40);
		this.jspDebug.setSize(780, 700);

		this.stopButton.setEnabled(false);

		this.startButton.addActionListener(this);
		this.stopButton.addActionListener(this);
	}

	private void addControls() {
		getContentPane().setLayout(null);

		this.startButton.setLocation(10, 10);
		this.stopButton.setLocation(210, 10);
		this.jspDebug.setLocation(10, 70);

		add(this.startButton);
		add(this.stopButton);
		add(this.jspDebug);
	}

	public void actionPerformed(ActionEvent ae) {
		Object o = ae.getSource();
		if (o == this.startButton) {
			this.startButton.setEnabled(false);
			this.stopButton.setEnabled(true);
			this.cserver.serverStart();
		} else if (o == this.stopButton) {
			this.startButton.setEnabled(true);
			this.stopButton.setEnabled(false);
			this.cserver.serverStop();
		}
	}

	@Override
	public void onMessageReceived(Message message) {

	}
}