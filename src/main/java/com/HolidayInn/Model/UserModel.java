package com.HolidayInn.Model;

/**
 * UserModel is a data transfer object (DTO) that holds the user information
 * required for registration, authentication, and profile management in the
 * system. It includes personal details such as name, gender, date of birth,
 * contact info, and login credentials.
 * 
 * @author Santosh Lama LMU ID- 23048594
 */

public class UserModel {

//	private String userId;
	private String fullName;
	private String userName;
	private String gender;
	private String dob;
	private String citizenshipNumber;
	private String phone;
	private String email;
	private String imagePath;

	private String password;

	/**
	 * Retrieves the user's full name (first + last names)
	 * 
	 * @return String containing the user's full name
	 */
	public String getFullName() {
		return fullName;
	}

	/**
	 * Sets the user's full name (first + last names)
	 * 
	 * @param fullName String containing full name to set
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	/**
	 * Gets the unique username used for login
	 * 
	 * @return String username value
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * Sets the unique username for authentication
	 * 
	 * @param userName String username to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * Retrieves the user's gender identity
	 * 
	 * @return String gender value (Male/Female/Other)
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * Sets the user's gender identity
	 * 
	 * @param gender String gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * Gets the date of birth in String format
	 * 
	 * @return String date of birth (yyyy-MM-dd)
	 */
	public String getDob() {
		return dob;
	}

	/**
	 * Sets the date of birth
	 * 
	 * @param dob String date in yyyy-MM-dd format
	 */
	public void setDob(String dob) {
		this.dob = dob;
	}

	/**
	 * Gets the government-issued citizenship number
	 * 
	 * @return String citizenship number
	 */
	public String getCitizenshipNumber() {
		return citizenshipNumber;
	}

	/**
	 * Sets the citizenship identification number
	 * 
	 * @param citizenshipNumber String ID number
	 */
	public void setCitizenshipNumber(String citizenshipNumber) {
		this.citizenshipNumber = citizenshipNumber;
	}

	/**
	 * Retrieves the user's phone number
	 * 
	 * @return String phone number
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * Sets the user's contact phone number
	 * 
	 * @param phone String phone number to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * Gets the user's email address
	 * 
	 * @return String email value
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the user's email address
	 * 
	 * @param email String email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Retrieves the path to user's profile image
	 * 
	 * @return String image file path
	 */
	public String getImagePath() {
		return imagePath;
	}

	/**
	 * Sets the path to user's profile picture
	 * 
	 * @param imagePath String file path
	 */
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	/**
	 * Gets the encrypted password (read-only for security)
	 * 
	 * @return String password hash
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the user password (will be encrypted before storage)
	 * 
	 * @param password String plaintext password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Default constructor creates empty UserModel
	 */
	public UserModel() {

	}

	/**
	 * Full constructor for complete user registration
	 * 
	 * @param fullName          User's full name
	 * @param userName          Unique username
	 * @param gender            Gender identity
	 * @param dob               Date of birth
	 * @param citizenshipNumber Government ID
	 * @param phone             Contact number
	 * @param email             Email address
	 * @param imagePath         Profile image location
	 * @param password          Login credential
	 */
	public UserModel(String fullName, String userName, String gender, String dob, String citizenshipNumber,
			String phone, String email, String imagePath, String password) {
	}

	/**
	 * Minimal constructor for authentication purposes
	 * @param userName Login username
	 * @param password Login password
	 */
	public UserModel(String userName, String password) {
		this.userName = userName;
		this.password = password;
	}

}
