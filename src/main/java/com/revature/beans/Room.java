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

	public int getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public void setNumBeds(Integer numBeds) {
		this.numBeds = numBeds;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((numBeds == null) ? 0 : numBeds.hashCode());
		result = prime * result + price;
		result = prime * result + roomNumber;
		result = prime * result + ((smoking == null) ? 0 : smoking.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Room other = (Room) obj;
		if (numBeds == null) {
			if (other.numBeds != null)
				return false;
		} else if (!numBeds.equals(other.numBeds))
			return false;
		if (price != other.price)
			return false;
		if (roomNumber != other.roomNumber)
			return false;
		if (smoking == null) {
			if (other.smoking != null)
				return false;
		} else if (!smoking.equals(other.smoking))
			return false;
		return true;
	}
	
}
