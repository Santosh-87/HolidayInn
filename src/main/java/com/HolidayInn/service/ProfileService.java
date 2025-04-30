package com.HolidayInn.service;

import java.sql.Connection;
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
}
