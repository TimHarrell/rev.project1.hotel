package com.revature.beans;

public class Profile {
	private String userId;
	private String firstName;
	private String lastName;
	private transient String password;
	private Boolean host;
	
	public Profile(String userId, String firstName, String lastName, String password) {
		super();
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.host = false;
	}
	
	public Profile(String userId, String firstName, String lastName, String password, Boolean host) {
		super();
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.host = host;
	}
	

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public Boolean isHost() {
		return host;
	}
	
	public void setHost(Boolean host) {
		this.host = host;
	}
	
	
	
}
