package com.revature.beans;

import java.sql.Date;
import java.util.ArrayList;

public class Reservation {
	private Date date;
	private String userId;
	private Integer roomNum;
	
	public Reservation() {
		super();
	}
	
	public Reservation(Date date, String userId, Integer roomNum ) {
		this.date = date;
		this.userId = userId;
		this.roomNum = roomNum;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getuserId() {
		return userId;
	}
	public void setProfile(String userId) {
		this.userId = userId;
	}
	public Integer getRoomNum() {
		return roomNum;
	}
	public void setRoomNum(Integer roomNum) {
		this.roomNum = roomNum;
	}
	
	public static ArrayList<Room> Rooms() {
		ArrayList<Room> rooms = new ArrayList<>();
		for(int i = 0; i < 20; i++) {
			rooms.add(new Room(i));
		}
		return rooms;
	}
	
}
