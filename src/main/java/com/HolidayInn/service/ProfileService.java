package com.HolidayInn.service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.HolidayInn.Model.UserModel;
import com.HolidayInn.config.DbConfig;
/**
 * Provides services for viewing user profiles by fetching all stored
 * user information from the database. Manages the database connection
 * and handles SQL operations to retrieve complete user records.
 * 
 * @author Santosh Lama 
 * LMU ID- 23048594
 */
public class ProfileService {
	private Connection dbConn;

	public ProfileService() {

		try {
			this.dbConn = DbConfig.getDbConnection();
		} catch (SQLException | ClassNotFoundException ex) {
			System.err.println("Database connection error in ViewProfileService: " + ex.getMessage());
			ex.printStackTrace();
		}
	}
	/**
	 * Fetches complete user profile by username. Returns UserModel with 8 fields 
	 * or null if not found/error. Uses secure parameterized query. Logs errors.
	 * 
	 * @param username Target user's login ID
	 * @return UserModel or null
	 */
	public UserModel getUserDetails(String username) {
		if (dbConn == null) {
			System.err.println("DB connection is null in ProfileService");
			return null;
		}
		String query = "SELECT * FROM user WHERE username= ?";
		try (PreparedStatement stmt = dbConn.prepareStatement(query)) {
			stmt.setString(1, username);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				UserModel user = new UserModel();
//				user.setUserId(rs.getString("user_id"));
				user.setFullName(rs.getString("full_name"));
				user.setUserName(rs.getString("username"));
				user.setEmail(rs.getString("email"));
				user.setPhone(rs.getString("phone"));
				user.setGender(rs.getString("gender"));
				user.setDob(rs.getDate("dob").toString());
				user.setImagePath(rs.getString("image_path"));
				user.setCitizenshipNumber(rs.getString("citizenship_number"));
				return user;
			}
		} catch (SQLException e) {
			System.err.println("Error fetching user details: " + e.getMessage());
		}
		return null;
	}

    /**
     * Updates a user's profile information in the database.
     *
     * @param userModel the UserModel object containing updated profile data
     * @param originalUsername the original username of the user to be updated
     * @return true if the update was successful, false if update failed or 
     *         connection error occurs
     */
    public boolean updateUser(UserModel userModel, String originalUsername) {
        if (dbConn == null) {
            System.err.println("Database connection is not available.");
            return false;
        }

        String updateQuery = "UPDATE user SET full_name = ?, gender = ?, dob = ?, " +
                           "citizenship_number = ?, phone = ?, email = ? " +
                           "WHERE username = ?";

        try (PreparedStatement updateStmt = dbConn.prepareStatement(updateQuery)) {
            updateStmt.setString(1, userModel.getFullName());
            updateStmt.setString(2, userModel.getGender());
            updateStmt.setDate(3, Date.valueOf(userModel.getDob()));
            updateStmt.setString(4, userModel.getCitizenshipNumber());
            updateStmt.setString(5, userModel.getPhone());
            updateStmt.setString(6, userModel.getEmail());
            updateStmt.setString(7, originalUsername);

            int rowsAffected = updateStmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Error updating user: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Checks if a phone number is already in use by another user in the database.
     *
     * @param phone the phone number to check
     * @param currentUsername the username of the current user (to exclude from check)
     * @return true if the phone number is already taken by another user, 
     *         false if available or connection error occurs
     */
    public boolean isPhoneTaken(String phone, String currentUsername) {
        if (dbConn == null) {
            return false;
        }

        String query = "SELECT 1 FROM user WHERE phone = ? AND username != ?";
        try (PreparedStatement ps = dbConn.prepareStatement(query)) {
            ps.setString(1, phone);
            ps.setString(2, currentUsername);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            System.err.println("Error checking phone: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Checks if an email address is already in use by another user in the database.
     *
     * @param email the email address to check
     * @param currentUsername the username of the current user (to exclude from check)
     * @return true if the email address is already taken by another user, 
     *         false if available or connection error occurs
     */
    public boolean isEmailTaken(String email, String currentUsername) {
        if (dbConn == null) {
            return false;
        }

        String query = "SELECT 1 FROM user WHERE email = ? AND username != ?";
        try (PreparedStatement ps = dbConn.prepareStatement(query)) {
            ps.setString(1, email);
            ps.setString(2, currentUsername);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            System.err.println("Error checking email: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}
