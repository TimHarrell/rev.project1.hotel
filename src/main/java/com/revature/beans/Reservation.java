package com.revature.beans;

import java.sql.Date;

public class Reservation {
	private Date date;
	private Profile profile;
	private Integer roomNum;
	
	Reservation() {
		super();
	}
	
	Reservation(Date date, Profile profile, Integer roomNum ) {
		this.date = date;
		this.profile = profile;
		this.roomNum = roomNum;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Profile getProfile() {
		return profile;
	}
	public void setProfile(Profile profile) {
		this.profile = profile;
	}
	public Integer getRoomNum() {
		return roomNum;
	}
	public void setRoomNum(Integer roomNum) {
		this.roomNum = roomNum;
	}
	
	
}
