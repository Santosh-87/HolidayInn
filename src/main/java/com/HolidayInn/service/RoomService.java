package com.HolidayInn.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.HolidayInn.Model.RoomModel;
import com.HolidayInn.config.DbConfig;

/**
 * RoomService is a Service layer for manipulating room details from database.
 * Handles database interactions for retrieving room information with optional
 * filtering and sorting capabilities. Responsibilities:
 * 
 * @author Santosh Lama LMU ID: 23048594
 */
public class RoomService {
	private Connection dbConn;

	public RoomService() {
		try {
			this.dbConn = DbConfig.getDbConnection();
		} catch (SQLException | ClassNotFoundException ex) {
			System.err.println("Database connection error: " + ex.getMessage());
			ex.printStackTrace();
		}
	}

	/**
	 * Retrieves all available rooms from the database without any filters.
	 * 
	 * @return List of all RoomModel objects representing available rooms
	 */
	public List<RoomModel> getAllRooms() {
		return getFilteredRooms(null, null);
	}

	/**
	 * Retrieves rooms filtered by grade and/or sorted by price. Builds dynamic SQL
	 * query based on provided parameters.
	 * 
	 * @param roomGrade the room grade/category to filter by (optional)
	 * @param priceSort the price sorting preference ("price-asc" or "price-desc")
	 *                  (optional)
	 * @return List of filtered/sorted RoomModel objects
	 */
	public List<RoomModel> getFilteredRooms(String roomGrade, String priceSort) {
		List<RoomModel> rooms = new ArrayList<>();
		StringBuilder query = new StringBuilder(
				"SELECT room_number, room_image, room_type, room_grade, bed_type, price FROM room WHERE 1=1");

		if (roomGrade != null && !roomGrade.isEmpty()) {
			query.append(" AND room_grade = ?");
		}

		if (priceSort != null && !priceSort.isEmpty()) {
			if (priceSort.equals("price-asc")) {
				query.append(" ORDER BY price ASC");
			} else if (priceSort.equals("price-desc")) {
				query.append(" ORDER BY price DESC");
			}
		}

		try (PreparedStatement ps = dbConn.prepareStatement(query.toString())) {
			int paramIndex = 1;
			if (roomGrade != null && !roomGrade.isEmpty()) {
				ps.setString(paramIndex++, roomGrade);
			}

			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					RoomModel room = new RoomModel();
					room.setRoomNumber(rs.getInt("room_number"));
					room.setRoomImage(rs.getString("room_image"));
					room.setRoomType(rs.getString("room_type"));
					room.setRoomGrade(rs.getString("room_grade"));
					room.setBedType(rs.getString("bed_type"));
					room.setPrice(rs.getInt("price"));
					rooms.add(room);
				}
			}
		} catch (SQLException e) {
			System.err.println("Error fetching rooms: " + e.getMessage());
			e.printStackTrace();
		}
		return rooms;
	}
}