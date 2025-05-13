package com.HolidayInn.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.HolidayInn.Model.BookingDetailsModel;
import com.HolidayInn.Model.RoomModel;
import com.HolidayInn.config.DbConfig;
/**
 * Service class for retrieving dashboard metrics and analytics.
 * Provides methods to fetch booking statistics, revenue data,
 * and operational insights from the database.
 * 
 * @author Santosh Lama
 * LMU ID- 23048594
 */
public class DashboardService {
	private Connection dbConn;

	/**
	 * Constructor initializes the database connection.
	 */
	public DashboardService() {
		try {
			this.dbConn = DbConfig.getDbConnection();
		} catch (SQLException | ClassNotFoundException ex) {
			System.err.println("Database connection error: " + ex.getMessage());
			ex.printStackTrace();
		}
	}

    /**
     * Retrieves the most recent bookings from the database.
     *
     * @param limit the maximum number of bookings to retrieve
     * @return List of BookingDetailsModel objects containing recent booking data
     */
	public List<BookingDetailsModel> getRecentBookings(int limit) {
		List<BookingDetailsModel> bookings = new ArrayList<>();
        StringBuilder query = new StringBuilder();
        query.append("SELECT b.booking_id, u.user_id, u.full_name, r.room_number, ");
        query.append("b.check_in_date, b.check_out_date, b.total_price ");
        query.append("FROM user_booking_room b ");
        query.append("JOIN user u ON b.user_id = u.user_id ");
        query.append("JOIN room r ON b.room_id = r.room_id ");
        query.append("ORDER BY b.check_in_date DESC LIMIT ?");

		try (PreparedStatement stmt = dbConn.prepareStatement(query.toString())) {
			stmt.setInt(1, limit);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				BookingDetailsModel booking = new BookingDetailsModel();
				booking.setBookingId(rs.getInt("booking_id"));
				booking.setUserId(rs.getString("user_id"));
				booking.setFullName(rs.getString("full_name"));
				booking.setRoomNumber(rs.getInt("room_number"));
				booking.setCheckInDate(rs.getDate("check_in_date"));
				booking.setCheckOutDate(rs.getDate("check_out_date"));
				booking.setTotalPrice(rs.getDouble("total_price"));
				bookings.add(booking);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bookings;
	}

    /**
     * Retrieves the most frequently booked room grade.
     *
     * @return the room grade with most bookings, or "N/A" if none found
     * @throws SQLException if a database access error occurs
     */
	public String getTopRatedRoom() throws SQLException {
	    String query = "SELECT r.room_grade, COUNT(*) as booking_count " +
	                  "FROM user_booking_room ubr " +
	                  "JOIN room r ON ubr.room_id = r.room_id " +
	                  "GROUP BY r.room_id " +
	                  "ORDER BY booking_count DESC " +
	                  "LIMIT 1";
	    
	    try (PreparedStatement stmt = dbConn.prepareStatement(query)) {
	        ResultSet rs = stmt.executeQuery();
	        if (rs.next()) {
	            return rs.getString("room_grade");
	        }
	    }
	    return "N/A";
	}

    /**
     * Calculates the average duration of guest stays.
     *
     * @return average stay duration in days, rounded to 1 decimal place
     * @throws SQLException if a database access error occurs
     */
	public double getAverageStayDuration() throws SQLException {

	    String query = "SELECT ROUND(AVG(DATEDIFF(check_out_date, check_in_date)), 1) AS avg_stay_days " +
	                 "FROM user_booking_room " +
	                 "WHERE check_out_date > check_in_date"; 
	    
	    try (PreparedStatement stmt = dbConn.prepareStatement(query)) {
	        ResultSet rs = stmt.executeQuery();
	        if (rs.next()) {
	            return rs.getDouble("avg_stay_days");
	        }
	    }
	    return 0.0;
	}
	
    /**
     * Calculates total revenue for the current month.
     *
     * @return sum of all booking revenues for current month
     * @throws SQLException if a database access error occurs
     */	
	public double getCurrentMonthRevenue() throws SQLException {
	    String query = "SELECT SUM(`total_price`) AS monthly_revenue " +
	                  "FROM user_booking_room " +
	                  "WHERE MONTH(check_in_date) = MONTH(CURRENT_DATE())";
	    
	    try (PreparedStatement stmt = dbConn.prepareStatement(query)) {
	        ResultSet rs = stmt.executeQuery();
	        if (rs.next()) {
	            return rs.getDouble("monthly_revenue");
	        }
	    }
	    return 0.0;
	}
	
    /**
     * Gets the busiest check-in day of the week with booking count
     * @return Map containing day_of_week and booking_count
     * @throws SQLException
     */
    public Map<String, Object> getBusiestCheckInDay() throws SQLException {
        Map<String, Object> result = new HashMap<>();
        result.put("day_of_week", "N/A");
        result.put("booking_count", 0);
        
        String query = "SELECT DAYNAME(check_in_date) as day_of_week, " +
                      "COUNT(*) as booking_count " +
                      "FROM user_booking_room " +
                      "GROUP BY day_of_week " +
                      "ORDER BY booking_count DESC " +
                      "LIMIT 1";
        
        try (PreparedStatement stmt = dbConn.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                result.put("day_of_week", rs.getString("day_of_week"));
                result.put("booking_count", rs.getInt("booking_count"));
            }
        }
        return result;
    }
    
    /**
     * Retrieves the most expensive room available in the hotel.
     * 
     * @return RoomModel containing details of the most expensive room
     * @throws SQLException if a database access error occurs
     */
    public RoomModel getMostExpensiveRoom() throws SQLException {
        String query = "SELECT * FROM room " +
                     "ORDER BY price DESC " +
                     "LIMIT 1";
        
        try (PreparedStatement stmt = dbConn.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                RoomModel room = new RoomModel();
                room.setRoomId(rs.getInt("room_id"));
                room.setRoomNumber(rs.getInt("room_number"));
                room.setRoomImage(rs.getString("room_image"));
                room.setRoomType(rs.getString("room_type"));
                room.setRoomGrade(rs.getString("room_grade"));
                room.setBedType(rs.getString("bed_type"));
                room.setRoomStatus(rs.getString("room_status"));
                room.setPrice(rs.getInt("price"));
                return room;
            }
        }
        return null;
    }
}
