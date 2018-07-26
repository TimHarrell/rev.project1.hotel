package com.revature.exceptions;

public class InvalidLoginCredentials extends Exception{
	public InvalidLoginCredentials() {
		super("Invalid Login Credentials.");
	}
}
