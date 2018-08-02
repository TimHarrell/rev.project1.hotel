package com.revature.beans;

import java.sql.Date;

public class PendingReservation {
	private Date date;
	private String userId;
	private Integer roomNumber;
	private Integer transactionNumber;
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getUserId() {
		return userId;
	}
	public void setProfile(String userId) {
		this.userId = userId;
	}
	public Integer getRoomNumber() {
		return roomNumber;
	}
	public void setRoomNumber(Integer roomNumber) {
		this.roomNumber = roomNumber;
	}
	public Integer getTransactionNumber() {
		return transactionNumber;
	}
	public void setTransactionNumber(Integer transactionNumber) {
		this.transactionNumber = transactionNumber;
	}
	public PendingReservation(Date date, String userId, Integer roomNumber, Integer transactionNumber) {
		super();
		this.date = date;
		this.userId = userId;
		this.roomNumber = roomNumber;
		this.transactionNumber = transactionNumber;
	}
	
	
}
