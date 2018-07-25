package com.revature.beans;

public class Profile {
	private String userId;
	private String firstName;
	private String lastName;
	private transient String password;
	
	public Profile(String userId, String firstName, String lastName, String password) {
		super();
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
	}
	
	
	
}
