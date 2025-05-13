package com.HolidayInn.service;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.HolidayInn.Model.RoomModel;
import com.HolidayInn.config.DbConfig;

/**
 * Service class for managing room operations including CRUD (Create, Read,
 * Update, Delete) and room data retrieval from the database. Provides methods
 * for retrieving all rooms, finding rooms by ID, adding new rooms, updating
 * existing rooms, and deleting rooms.
 * 
 * @author Santosh Lama 
 * LMU ID- 23048594
 */
public class RoomCrudService {

	private Connection dbConn;

	public RoomCrudService() {
		try {
			this.dbConn = DbConfig.getDbConnection();
		} catch (SQLException | ClassNotFoundException ex) {
			System.err.println("Database connection error: " + ex.getMessage());
			ex.printStackTrace();
		}
	}

	/**
	 * Retrieves all rooms from the database.
	 * 
	 * @return List of RoomModel objects containing all room data, or empty list if
	 *         no rooms found or error occurs
	 */
	public List<RoomModel> getAllRooms() {
		List<RoomModel> rooms = new ArrayList<>();

		try (PreparedStatement statement = dbConn.prepareStatement("SELECT * FROM room");
				ResultSet rs = statement.executeQuery()) {

			while (rs.next()) {
				RoomModel room = new RoomModel();
				room.setRoomId(rs.getInt("room_id"));
				room.setRoomNumber(rs.getInt("room_number"));
				room.setRoomImage(rs.getString("room_image"));
				room.setRoomType(rs.getString("room_type"));
				room.setRoomGrade(rs.getString("room_grade"));
				room.setBedType(rs.getString("bed_type"));
				room.setRoomStatus(rs.getString("room_status"));
				room.setPrice(rs.getInt("price"));
				rooms.add(room);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rooms;
	}

	/**
	 * Retrieves a single room by its ID.
	 * 
	 * @param roomId The ID of the room to retrieve
	 * @return RoomModel object containing the room data, or null if room not found
	 * @throws SQLException           If a database access error occurs
	 * @throws ClassNotFoundException If the database driver class is not found
	 */
	public RoomModel getRoomById(int roomId) throws SQLException {
		try (PreparedStatement statement = dbConn.prepareStatement("SELECT * FROM room WHERE room_id = ?")) {
			statement.setInt(1, roomId);
			ResultSet rs = statement.executeQuery();

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
			return null;
		}
	}

	/**
	 * Adds a new room to the database.
	 * 
	 * @param room The RoomModel object containing room data to be saved
	 * @throws RuntimeException If database operation fails
	 */
	public void saveRoom(RoomModel room) {
		String sql = "INSERT INTO room (room_number, room_image, room_type, room_grade, bed_type, room_status, price) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?)";

		try (PreparedStatement stmt = dbConn.prepareStatement(sql)) {
			stmt.setInt(1, room.getRoomNumber());
			stmt.setString(2, room.getRoomImage());
			stmt.setString(3, room.getRoomType());
			stmt.setString(4, room.getRoomGrade());
			stmt.setString(5, room.getBedType());
			stmt.setString(6, room.getRoomStatus());
			stmt.setInt(7, room.getPrice());

			stmt.executeUpdate();
		} catch (Exception e) {
			throw new RuntimeException("Failed to save room", e);
		}
	}

	/**
	 * Updates an existing room in the database.
	 * 
	 * @param room The RoomModel object containing updated room data
	 * @return true if update was successful, false otherwise
	 * @throws SQLException           If a database access error occurs
	 * @throws ClassNotFoundException If the database driver class is not found
	 */
	public boolean updateRoom(RoomModel room) throws SQLException {
		try (PreparedStatement ps = dbConn.prepareStatement(
				"UPDATE room SET room_number=?, room_image=?, room_type=?, room_grade=?, bed_type=?, room_status=?, price=? WHERE room_id=?")) {

			ps.setInt(1, room.getRoomNumber());
			ps.setString(2, room.getRoomImage());
			ps.setString(3, room.getRoomType());
			ps.setString(4, room.getRoomGrade());
			ps.setString(5, room.getBedType());
			ps.setString(6, room.getRoomStatus());
			ps.setInt(7, room.getPrice());
			ps.setInt(8, room.getRoomId());

			return ps.executeUpdate() > 0;
		}
	}

	/**
	 * Deletes a room from the database.
	 * 
	 * @param roomId The ID of the room to be deleted
	 * @return true if deletion was successful, false otherwise
	 * @throws SQLException           If a database access error occurs
	 * @throws ClassNotFoundException If the database driver class is not found
	 */

	public boolean deleteRoom(int roomId) throws SQLException {
		try (PreparedStatement ps = dbConn.prepareStatement("DELETE FROM room WHERE room_id=?")) {
			ps.setInt(1, roomId);
			return ps.executeUpdate() > 0;
		}
	}

	public List<RoomModel> searchRooms(String searchQuery) {
		String sql = "SELECT * FROM room WHERE " + "CAST(room_number AS CHAR) LIKE ? OR "
				+ "LOWER(room_grade) LIKE LOWER(?) OR "+ "LOWER(bed_type) LIKE LOWER(?) OR " 
				+ "LOWER(room_status) LIKE LOWER(?) OR " + "CAST(price AS CHAR) LIKE ?";

		List<RoomModel> rooms = new ArrayList<>();

		try (Connection conn = DbConfig.getDbConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
//			if (searchQuery == null) searchQuery = "";
			String pattern = "%" + searchQuery + "%";
			stmt.setString(1, pattern); // room_number
			stmt.setString(2, pattern); // room_grade
			stmt.setString(3, pattern); // bed_type
			stmt.setString(4, pattern); // room_status
			stmt.setString(5, pattern); // price

			try (ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					RoomModel room = new RoomModel();
					room.setRoomId(rs.getInt("room_id"));
					room.setRoomNumber(rs.getInt("room_number"));
					room.setRoomImage(rs.getString("room_image"));
					room.setRoomType(rs.getString("room_type"));
					room.setRoomGrade(rs.getString("room_grade"));
					room.setBedType(rs.getString("bed_type"));
					room.setRoomStatus(rs.getString("room_status"));
					room.setPrice(rs.getInt("price"));
					rooms.add(room);
				}
			}

			System.out.println("Found " + rooms.size() + " matching rooms");
			return rooms;
		} catch (Exception e) {
			System.err.println("Search error: " + e.getMessage());
			e.printStackTrace();
			return Collections.emptyList();
		}
	}

}