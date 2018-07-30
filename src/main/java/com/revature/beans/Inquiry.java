package com.revature.beans;

import java.util.ArrayList;

public class Inquiry {
	private String topic;
	private ArrayList<String> convo; // stored in another table with name id
	private String userId;
	private long id;
	private Boolean active;
	
	
	
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	public ArrayList<String> getConvo() {
		return convo;
	}
	public void setConvo(ArrayList<String> convo) {
		this.convo = convo;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String profile) {
		this.userId = profile;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
	
	public Inquiry() {
		super();
	}
	
	public Inquiry(String topic, ArrayList<String> convo, String profile, long id, Boolean active) {
		super();
		this.topic = topic;
		this.convo = convo;
		this.userId = profile;
		this.id = id;
		this.active = active;
	}
	
	
	
}
