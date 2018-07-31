package com.revature.beans;

public class Message {
	private String message;
	private String sender;
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	
	public Message() {
		super();
	}
	public Message(String message, String sender) {
		super();
		this.message = message;
		this.sender = sender;
	}
}
