package com.revature.beans;

public class Message {
	private String message;
	private String sender;
	private int messageNumber;
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
	public int getMessageNumber() {
		return messageNumber;
	}
	public void setMessageNumber(int messageNumber) {
		this.messageNumber = messageNumber;
	}
	
	
	public Message(String message, String sender, int messageNumber) {
		super();
		this.message = message;
		this.sender = sender;
		this.messageNumber = messageNumber;
	}
}
