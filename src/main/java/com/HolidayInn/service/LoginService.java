package com.HolidayInn.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.HolidayInn.Model.UserModel;
import com.HolidayInn.config.DbConfig;
import com.HolidayInn.util.PasswordUtil;
/**
 * Core authentication engine that verifies user credentials against database records.
 * Performs secure password validation using encryption and manages role-based access.
 * Handles all database interactions for login operations including connection management
 * and SQL query execution.
 * 
 * @author Santosh Lama 
 * LMU ID- 23048594
 */
public class LoginService {
	private Connection dbConn;
	private boolean isConnectionError = false;

	/**
	 * Constructor initializes the database connection. Sets the connection error
	 * flag if the connection fails.
	 */
	public LoginService() {
		try {
			dbConn = DbConfig.getDbConnection();
		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
			isConnectionError = true;
		}
	}

	/**
	 * Validates the user credentials against the database records.
	 *
	 * @param studentModel the StudentModel object containing user credentials
	 * @return true if the user credentials are valid, false otherwise; null if a
	 *         connection error occurs
	 */
	public Boolean loginUser(UserModel userModel) {
		if (isConnectionError) {
			System.out.println("Connection Error!");
			return null;
		}

		String query = "SELECT username, password FROM user WHERE username = ?";
		try (PreparedStatement stmt = dbConn.prepareStatement(query)) {
			stmt.setString(1, userModel.getUserName());
			ResultSet result = stmt.executeQuery();

			if (result.next()) {
				return validatePassword(result, userModel);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		return false;
	}

	/**
	 * Validates the password retrieved from the database.
	 *
	 * @param result the ResultSet containing the username and password from the database
	 * @param studentModel the StudentModel object containing user credentials
	 * @return true if the passwords match, false otherwise
	 * @throws SQLException if a database access error occurs
	 */
	private boolean validatePassword(ResultSet result, UserModel userModel) throws SQLException {
		String dbUsername = result.getString("username");
		String dbPassword = result.getString("password");

		return dbUsername.equals(userModel.getUserName())
				&& PasswordUtil.decrypt(dbPassword, dbUsername).equals(userModel.getPassword());
	}

	/**
	 * Retrieves the database role associated with the given user's username.
	 * 
	 * @param user the UserModel object containing the username to look up
	 * @return the role string if found; null if no role found, user doesn't exist,
	 *         or if a database connection error occurs
	 */
	public String getDbRole(UserModel user) {
		if (dbConn == null) {
			System.err.println("DB connection is null in LoginService.");
			return null;
		}

		String inputUsername = user.getUserName().trim();

		String query = "SELECT role FROM user WHERE username = ?";
		try (PreparedStatement stmt = dbConn.prepareStatement(query)) {
			stmt.setString(1, inputUsername); // Trim input just in case
			ResultSet result = stmt.executeQuery();

			if (result.next()) {
				return result.getString("role");
			}
		} catch (SQLException e) {
			System.err.println("SQL Exception during login: " + e.getMessage());
			e.printStackTrace();
		}

		return null;
	}
}
