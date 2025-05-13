package com.HolidayInn.Model;
/**
 * Model class representing a hotel room with all associated properties.
 * Contains room details (number, type, bed configuration), pricing information,
 * and current availability status for room management operations.
 * 
 * @author Santosh Lama 
 * LMU ID- 23048594
 */
public class RoomModel {
	private int roomId;
	private int roomNumber;
	private String roomType;
	private String roomImage;
	private String roomGrade;
	private String bedType;
	private int price;
	private String roomStatus;
	
	/**
	 * Gets the unique database identifier for the room
	 * @return int room ID
	 */
	public int getRoomId() {
	    return roomId;
	}

	/**
	 * Sets the unique database identifier for the room
	 * @param roomId int room ID to set
	 */
	public void setRoomId(int roomId) {
	    this.roomId = roomId;
	}

	/**
	 * Retrieves the room's display/number identifier
	 * @return int room number
	 */
	public int getRoomNumber() {
	    return roomNumber;
	}

	/**
	 * Sets the room's display/number identifier
	 * @param roomNumber int room number to set
	 */
	public void setRoomNumber(int roomNumber) {
	    this.roomNumber = roomNumber;
	}

	/**
	 * Gets the type/category of room (e.g., Standard, Deluxe)
	 * @return String room type
	 */
	public String getRoomType() {
	    return roomType;
	}

	/**
	 * Sets the type/category of room
	 * @param roomType String room type to set
	 */
	public void setRoomType(String roomType) {
	    this.roomType = roomType;
	}

	/**
	 * Retrieves the path to room's image/photo
	 * @return String image file path
	 */
	public String getRoomImage() {
	    return roomImage;
	}

	/**
	 * Sets the path to room's image/photo
	 * @param roomImage String image file path
	 */
	public void setRoomImage(String roomImage) {
	    this.roomImage = roomImage;
	}

	/**
	 * Gets the quality grade/classification of room
	 * @return String room grade (e.g., Luxury, Economy)
	 */
	public String getRoomGrade() {
	    return roomGrade;
	}

	/**
	 * Sets the quality grade/classification of room
	 * @param roomGrade String room grade to set
	 */
	public void setRoomGrade(String roomGrade) {
	    this.roomGrade = roomGrade;
	}

	/**
	 * Retrieves the bed configuration type
	 * @return String bed type (e.g., King, Queen, Twin)
	 */
	public String getBedType() {
	    return bedType;
	}

	/**
	 * Sets the bed configuration type
	 * @param bedType String bed type to set
	 */
	public void setBedType(String bedType) {
	    this.bedType = bedType;
	}

	/**
	 * Gets the nightly rate for the room
	 * @return int price in base currency
	 */
	public int getPrice() {
	    return price;
	}

	/**
	 * Sets the nightly rate for the room
	 * @param price int price in base currency
	 */
	public void setPrice(int price) {
	    this.price = price;
	}

	/**
	 * Retrieves current availability status
	 * @return String status (Available/Occupied/Maintenance)
	 */
	public String getRoomStatus() {
	    return roomStatus;
	}

	/**
	 * Sets current availability status
	 * @param roomStatus String status to set
	 */
	public void setRoomStatus(String roomStatus) {
	    this.roomStatus = roomStatus;
	}
}
