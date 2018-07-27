package com.revature.beans;

public class Room {
	private Integer numBeds;
	private Boolean smoking;
	private int roomNumber;
	private int price;
	public Room() {
		super();
	}
	
	public Room(int numBeds, Boolean smoking) {
		this.numBeds = numBeds;
		this.smoking = smoking;
	}
	
	public Room(int numBeds, int smoking) {
		this.numBeds = numBeds;
		if(smoking == 0) this.smoking = false;
		else this.smoking = true;
	}
	
	public Room(int roomNumber) {
		this.roomNumber = roomNumber;
		if(roomNumber < 5) {
			numBeds = 1;
			smoking = true;
			price = 50;
		} 
		else if(roomNumber < 10) {
			numBeds = 1;
			smoking = false;
			price = 70;
		}
		else if(roomNumber < 15) {
			numBeds = 2;
			smoking = true;
			price = 70;
		}
		else if(roomNumber < 20) {
			numBeds = 2;
			smoking = false;
			price = 90;
		}
	}
	
	
	public String getNumBedsAsString() {
		return numBeds.toString();
	}
	
	public String getSmokingAsString() {
		if(smoking) return "smoking";
		return "non-smoking";
	}
	public int getNumBeds() {
		return numBeds;
	}

	public void setNumBeds(int numBeds) {
		this.numBeds = numBeds;
	}

	public Boolean getSmoking() {
		return smoking;
	}

	public void setSmoking(Boolean smoking) {
		this.smoking = smoking;
	}
	
}
