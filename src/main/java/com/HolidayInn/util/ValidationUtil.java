package com.HolidayInn.util;

import java.util.regex.Pattern;
import jakarta.servlet.http.Part;

/**
 * Utility class for validating user input fields.
 * Provides methods to check for valid names, emails, phone numbers, passwords,
 * and image file types to ensure clean and secure input.
 * 
 * @author Santosh Lama 
 * LMU ID- 23048594
 */
public class ValidationUtil {

	/**
	 * Checks if the given string is null or empty.
	 */
	public static boolean isNullOrEmpty(String value) {
		return value == null || value.trim().isEmpty();
	}

	/**
	 * Validates that a string starts with a letter and contains only alphabetic 
	 * characters in order to be a valid name.
	 */
	public static boolean isValidFullName(String value) {
	    return value != null && value.matches("^[a-zA-Z ]+$");
	}


	/**
	 * Validates that a string starts with a letter and contains only alphanumeric
	 * characters.
	 */
	public static boolean isAlphanumericStartingWithLetter(String value) {
		return value != null && value.matches("^[a-zA-Z][a-zA-Z0-9]*$");
	}

	/**
	 * Validates that a Nepali phone number starts with '98' and is 10 digits long.
	 */
	public static boolean isValidPhoneNumber(String number) {
		return number != null && number.matches("^98\\d{8}$");
	}

	/**
	 * Validates that a citizenship number is exactly 14 digits.
	 */
	public static boolean isValidCitizenshipNumber(String citizenshipNumber) {
		return citizenshipNumber != null && citizenshipNumber.matches("^\\d{14}$");
	}

	/**
	 * Validates the format of an email address.
	 */
	public static boolean isValidEmail(String email) {
		String emailRegex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
		return email != null && Pattern.matches(emailRegex, email);
	}

	/**
	 * Validates that a password has at least one uppercase letter, one number, one
	 * special character, and is at least 8 characters long.
	 */
	public static boolean isValidPassword(String password) {
		String passwordRegex = "^(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
		return password != null && password.matches(passwordRegex);
	}

	/**
	 * Validates that both password fields match.
	 */
	public static boolean doPasswordsMatch(String password, String retypePassword) {
		return password != null && password.equals(retypePassword);
	}

	/**
	 * Validates that the uploaded file has an allowed image extension.
	 */
	public static boolean isValidImageExtension(Part imagePart) {
		if (imagePart == null || isNullOrEmpty(imagePart.getSubmittedFileName())) {
			return false;
		}
		String fileName = imagePart.getSubmittedFileName().toLowerCase();
		return fileName.endsWith(".jpg") || fileName.endsWith(".jpeg") || fileName.endsWith(".png")
				|| fileName.endsWith(".gif");
	}
}
