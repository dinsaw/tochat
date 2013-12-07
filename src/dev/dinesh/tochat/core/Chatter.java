package dev.dinesh.tochat.core;

import java.net.InetAddress;

public class Chatter {
	private String name = null;
	private InetAddress address;
	private int port;

	public Chatter(InetAddress gAddress, int gPort, String gName) {
		setAddress(gAddress);
		setPort(gPort);
		setName(gName);
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public InetAddress getAddress() {
		return this.address;
	}

	public void setAddress(InetAddress address) {
		this.address = address;
	}

	public int getPort() {
		return this.port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	@Override
	public String toString() {
		return name + "|" + address + "|" + port;
	}
	@Override
	public boolean equals(Object obj) {
		Chatter chatter = (Chatter) obj;
		System.out.println("testequal");
		System.out.println(chatter.getAddress()+""+address+chatter.getPort()+port+chatter.getName());
		if (chatter.getName().trim().equals(name.trim())) {
			return true;
		}
		System.out.println(chatter.getAddress()+""+address+chatter.getPort()+port);
		return false;
	}
}