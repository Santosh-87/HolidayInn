package com.HolidayInn.util;

import java.util.regex.Pattern;
import jakarta.servlet.http.Part;

/**
 * Utility class for validating user input fields. Provides methods to check for
 * valid names, emails, phone numbers, passwords, and image file types to ensure
 * clean and secure input.
 * 
 * @author Santosh Lama 
 * LMU ID- 23048594
 */
public class ValidationUtil {

	/**
	 * Checks if a string is null or empty (containing only whitespace).
	 *
	 * @param value the string to check
	 * @return true if the string is null or empty, false otherwise
	 */
	public static boolean isNullOrEmpty(String value) {
		return value == null || value.trim().isEmpty();
	}

	/**
	 * Validates that a full name contains only alphabetic characters and spaces.
	 *
	 * @param value the name string to validate
	 * @return true if the name contains only letters and spaces, false otherwise
	 */
	public static boolean isValidFullName(String value) {
		return value != null && value.matches("^[a-zA-Z ]+$");
	}

	/**
	 * Validates that a string starts with a letter and contains only alphanumeric
	 * characters.
	 *
	 * @param value the string to validate
	 * @return true if the string starts with a letter and contains only
	 *         alphanumeric characters, false otherwise
	 */
	public static boolean isAlphanumericStartingWithLetter(String value) {
		return value != null && value.matches("^[a-zA-Z][a-zA-Z0-9]*$");
	}

	/**
	 * Validates a Nepali phone number format (starts with '98' and 10 digits
	 * total).
	 *
	 * @param number the phone number string to validate
	 * @return true if the phone number matches the required format, false otherwise
	 */
	public static boolean isValidPhoneNumber(String number) {
		return number != null && number.matches("^98\\d{8}$");
	}

	/**
	 * Validates a Nepali citizenship number format (exactly 14 digits).
	 *
	 * @param citizenshipNumber the citizenship number string to validate
	 * @return true if the number contains exactly 14 digits, false otherwise
	 */
	public static boolean isValidCitizenshipNumber(String citizenshipNumber) {
		return citizenshipNumber != null && citizenshipNumber.matches("^\\d{14}$");
	}

	/**
	 * Validates an email address format using standard email pattern.
	 *
	 * @param email the email string to validate
	 * @return true if the email matches standard format, false otherwise
	 */
	public static boolean isValidEmail(String email) {
		String emailRegex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
		return email != null && Pattern.matches(emailRegex, email);
	}

	/**
	 * Validates password strength requirements: - At least 8 characters long -
	 * Contains at least one uppercase letter - Contains at least one number -
	 * Contains at least one special character
	 *
	 * @param password the password string to validate
	 * @return true if the password meets all requirements, false otherwise
	 */
	public static boolean isValidPassword(String password) {
		String passwordRegex = "^(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
		return password != null && password.matches(passwordRegex);
	}

	/**
	 * Verifies that two password strings match exactly.
	 *
	 * @param password       the original password string
	 * @param retypePassword the password confirmation string
	 * @return true if both passwords are non-null and equal, false otherwise
	 */
	public static boolean doPasswordsMatch(String password, String retypePassword) {
		return password != null && password.equals(retypePassword);
	}

	/**
	 * Validates that an uploaded file has an allowed image extension (.jpg, .jpeg,
	 * .png, .gif).
	 *
	 * @param imagePart the file part to validate
	 * @return true if the file has a valid image extension, false otherwise
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