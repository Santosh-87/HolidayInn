package com.HolidayInn.Model;

import java.sql.Date;

public class BookingDetailsModel {
	private int bookingId;
	private String userId;
	private String fullName;
	private int roomNumber;
	private Date checkInDate;
	private Date checkOutDate;
	private double totalPrice;

	/**
	 * Gets the unique booking identifier
	 * 
	 * @return int booking ID
	 */
	public int getBookingId() {
		return bookingId;
	}

	/**
	 * Sets the unique booking identifier
	 * 
	 * @param bookingId int booking ID to set
	 */
	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}

	/**
	 * Retrieves the user ID associated with the booking
	 * 
	 * @return String user ID
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * Sets the user ID for the booking
	 * 
	 * @param userId String user ID to associate
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * Gets the full name of the guest making the booking
	 * 
	 * @return String guest's full name
	 */
	public String getFullName() {
		return fullName;
	}

	/**
	 * Sets the guest's full name for the booking
	 * 
	 * @param fullName String name to set
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	/**
	 * Retrieves the room number for the booking
	 * 
	 * @return int room number
	 */
	public int getRoomNumber() {
		return roomNumber;
	}

	/**
	 * Sets the room number for the booking
	 * 
	 * @param roomNumber int room number to book
	 */
	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}

	/**
	 * Gets the scheduled check-in date
	 * 
	 * @return Date check-in date (java.sql.Date)
	 */
	public Date getCheckInDate() {
		return checkInDate;
	}

	/**
	 * Sets the check-in date for the booking
	 * 
	 * @param checkInDate Date object for check-in
	 */
	public void setCheckInDate(Date checkInDate) {
		this.checkInDate = checkInDate;
	}

	/**
	 * Gets the scheduled check-out date
	 * 
	 * @return Date check-out date (java.sql.Date)
	 */
	public Date getCheckOutDate() {
		return checkOutDate;
	}

	/**
	 * Sets the check-out date for the booking
	 * 
	 * @param checkOutDate Date object for check-out
	 */
	public void setCheckOutDate(Date checkOutDate) {
		this.checkOutDate = checkOutDate;
	}

	/**
	 * Retrieves the total price for the booking
	 * 
	 * @return double total amount in base currency
	 */
	public double getTotalPrice() {
		return totalPrice;
	}

	/**
	 * Sets the total price for the booking
	 * 
	 * @param totalPrice double amount to set
	 */
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
}
