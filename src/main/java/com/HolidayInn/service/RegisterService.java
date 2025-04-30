package com.HolidayInn.service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.HolidayInn.Model.UserModel;
import com.HolidayInn.config.DbConfig;
import com.HolidayInn.util.PasswordUtil;
import com.HolidayInn.util.ValidationUtil;

public class RegisterService {
	private Connection dbConn;

	/**
	 * Constructor initializes the database connection.
	 */
	public RegisterService() {
		try {
			this.dbConn = DbConfig.getDbConnection();
		} catch (SQLException | ClassNotFoundException ex) {
			System.err.println("Database connection error: " + ex.getMessage());
			ex.printStackTrace();
		}
	}

	public boolean addUser(UserModel userModel) {

		if (dbConn == null) {
			System.err.println("Database connection is not available.");
			return false;
		}
		String insertQuery = "INSERT INTO user (full_name, username, gender, dob, citizenship_number, phone, email, image_path, password) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?,?)";
		
		String encryptedPassword = PasswordUtil.encrypt(userModel.getUserName(), userModel.getPassword());

		try (PreparedStatement insertStmt = dbConn.prepareStatement(insertQuery)) {
			insertStmt.setString(1, userModel.getFullName());
			insertStmt.setString(2, userModel.getUserName());
			insertStmt.setString(3, userModel.getGender());
			insertStmt.setDate(4, Date.valueOf(userModel.getDob()));
			insertStmt.setString(5, userModel.getCitizenshipNumber());
			insertStmt.setString(6, userModel.getPhone());
			insertStmt.setString(7, userModel.getEmail());
			insertStmt.setString(8, userModel.getImagePath());
			insertStmt.setString(9, encryptedPassword);

			return insertStmt.executeUpdate() > 0;
		} catch (SQLException e) {
			System.err.println("Error during student registration: " + e.getMessage());
			e.printStackTrace();
			return false;
		}

	}

	/**
	 * Checks if a username already exists in the database.
	 *
	 * @param username the username to check
	 * @return true if taken, false otherwise
	 */
	public boolean isUsernameTaken(String username) {
		if (dbConn == null) {
			return false;
		}

		String query = "SELECT 1 FROM user WHERE username = ?";
		try (PreparedStatement ps = dbConn.prepareStatement(query)) {
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			return rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Checks if an phone already exists in the database.
	 *
	 * @param phone the phone to check
	 * @return true if taken, false otherwise
	 */
	public boolean isPhoneTaken(String phone) {
		if (dbConn == null) {
			return false;
		}
		String query = "SELECT 1 FROM user WHERE phone = ?";
		try (PreparedStatement ps = dbConn.prepareStatement(query)) {
			ps.setString(1, phone);
			ResultSet rs = ps.executeQuery();
			return rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Checks if an email already exists in the database.
	 *
	 * @param email the email to check
	 * @return true if taken, false otherwise
	 */
	public boolean isEmailTaken(String email) {
		if (dbConn == null) {
			return false;
		}
		String query = "SELECT 1 FROM user WHERE email = ?";
		try (PreparedStatement ps = dbConn.prepareStatement(query)) {
			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();
			return rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
    /**
     * Checks if a citizenship number already exists in the database.
     *
     * @param citizenshipNumber the citizenship number to check
     * @return true if already exists, false otherwise
     */
    public boolean isCitizenshipNumberTaken(String citizenshipNumber) {
        if (dbConn == null) {
            return false;
        }

        String query = "SELECT 1 FROM user WHERE citizenship_number = ?";
        try (PreparedStatement ps = dbConn.prepareStatement(query)) {
            ps.setString(1, citizenshipNumber);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            System.err.println("Error checking citizenship number: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

}
