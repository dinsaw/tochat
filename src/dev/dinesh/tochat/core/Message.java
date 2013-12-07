/**
 * 
 */
package dev.dinesh.tochat.core;

/**
 * @author dinesh
 *
 */
public class Message {
	String message;
	Chatter author;
	
	public Message(String message, Chatter author) {
		super();
		this.message = message;
		this.author = author;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Chatter getAuthor() {
		return author;
	}
	public void setAuthor(Chatter author) {
		this.author = author;
	}
}
