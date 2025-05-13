package com.HolidayInn.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.HolidayInn.service.RoomCrudService;

/**
 * Servlet controller handling room deletion operations. Processes POST requests
 * to delete a room from the system and redirects back to the room management page.
 * 
 * @author Santosh Lama 
 * LMU ID- 23048594
 */
@WebServlet("/deleteRoom")
public class DeleteRoomController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private RoomCrudService roomService = new RoomCrudService();

    /**
     * Handles POST requests for deleting a room. Extracts the room ID from request
     * parameters, attempts deletion via service layer, and redirects to room management
     * page regardless of operation success.
     *
     * @param request the HttpServletRequest containing the room ID parameter
     * @param response the HttpServletResponse for redirecting after deletion
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs during request processing
     */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int roomId = Integer.parseInt(request.getParameter("id"));
		try {
			roomService.deleteRoom(roomId);
		} catch (Exception e) {
			e.printStackTrace(); // Ideally, handle and log properly
		}
		response.sendRedirect("editroom"); // Refresh the page
	}
}
