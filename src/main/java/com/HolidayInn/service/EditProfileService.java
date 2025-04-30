package com.HolidayInn.service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.HolidayInn.Model.UserModel;
import com.HolidayInn.config.DbConfig;
/**
 * Provides services for managing user profile data including retrieval,
 * updates, and validation of unique fields. Handles all database
 * operations related to user profile modifications with proper
 * error handling and connection management.
 * 
 * @author Santosh Lama 
 * LMU ID- 23048594
 */
public class EditProfileService {
    private Connection dbConn;

    public EditProfileService() {
        try {
            this.dbConn = DbConfig.getDbConnection();
        } catch (SQLException | ClassNotFoundException ex) {
            System.err.println("Database connection error: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public UserModel getUserByUsername(String username) {
        if (dbConn == null) {
            return null;
        }

        String query = "SELECT * FROM user WHERE username = ?";
        try (PreparedStatement ps = dbConn.prepareStatement(query)) {
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                UserModel user = new UserModel();
                user.setFullName(rs.getString("full_name"));
                user.setUserName(rs.getString("username"));
                user.setGender(rs.getString("gender"));
                user.setDob(rs.getDate("dob").toString());
                user.setCitizenshipNumber(rs.getString("citizenship_number"));
                user.setPhone(rs.getString("phone"));
                user.setEmail(rs.getString("email"));
                return user;
            }
        } catch (SQLException e) {
            System.err.println("Error fetching user: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

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