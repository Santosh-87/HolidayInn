package com.HolidayInn.Model;

/**
 * UserModel is a data transfer object (DTO) that holds the user information 
 * required for registration, authentication, and profile management in the system.
 * It includes personal details such as name, gender, date of birth, contact info,
 * and login credentials.
 * 
 * @author Santosh Lama 
 * LMU ID- 23048594
 */

public class UserModel {
	
	private String fullName;
	private String userName;
	private String gender;
	private String dob;
	private String citizenshipNumber;
	private String phone;
	private String email;
	private String imagePath;

	private String password;
	
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getCitizenshipNumber() {
		return citizenshipNumber;
	}
	public void setCitizenshipNumber(String citizenshipNumber) {
		this.citizenshipNumber = citizenshipNumber;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public UserModel() {
		
	}
	
	public UserModel(String fullName, String userName, String gender, String dob, String citizenshipNumber,
		String phone, String email, String imagePath, String password) {
		
		super();
		this.fullName = fullName;
		this.userName = userName;
		this.gender = gender;
		this.dob = dob;
		this.citizenshipNumber = citizenshipNumber;
		this.phone = phone;
		this.email = email;
		this.imagePath= imagePath;
		this.password = password;
	}
	
	public UserModel(String userName, String password) {
		this.userName = userName;
		this.password = password;
	}
	
	
}
