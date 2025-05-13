package com.HolidayInn.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import com.HolidayInn.Model.RoomModel;
import com.HolidayInn.service.RoomCrudService;

/**
 * Controller for displaying and handling the room update form.
 * Retrieves room details for editing and displays the update form.
 * 
 * @author Santosh Lama 
 * LMU ID- 23048594
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/editroom" })

/**
 * Displays the room management interface with optional search filtering.
 * Shows either all rooms or filtered results based on search query.
 *
 * @param request the HttpServletRequest containing optional searchQuery parameter
 * @param response the HttpServletResponse for rendering the view
 * @throws ServletException if a servlet error occurs
 * @throws IOException if an I/O error occurs
 */
public class RoomCrudController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private RoomCrudService roomService= new RoomCrudService();

	/**
	 * Handles GET requests for room management interface. Retrieves all rooms or 
	 * filtered results based on search query. Sets search status attributes and
	 * forwards to roomCRUD.jsp view.
	 *
	 * @param request  HttpServletRequest containing optional searchQuery parameter
	 * @param response HttpServletResponse for rendering the view
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs during processing
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {

	    String searchQuery = request.getParameter("searchQuery");
	    List<RoomModel> rooms;
	    
	    if (searchQuery == null || searchQuery.trim().isEmpty()) {
	        rooms = roomService.getAllRooms();
	        request.setAttribute("searchPerformed", false);
	    } else {
	        rooms = roomService.searchRooms(searchQuery);
	        request.setAttribute("searchPerformed", true);
	        request.setAttribute("searchEmpty", rooms.isEmpty());
	    }
	    
	    request.setAttribute("rooms", rooms);
	    request.setAttribute("searchQuery", searchQuery);
	    request.getRequestDispatcher("WEB-INF/pages/roomCRUD.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}