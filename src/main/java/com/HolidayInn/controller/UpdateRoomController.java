package com.HolidayInn.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.HolidayInn.Model.RoomModel;
import com.HolidayInn.service.RoomCrudService;

/**
 * Controller for handling both display and processing of room updates.
 * Manages displaying the room edit form with current room details (GET)
 * Processes room update submissions (POST)
 * Provides success/error feedback and maintains data consistency.
 * 
 * @author Santosh Lama
 * LMU ID- 23048594
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/updateRoom" })
public class UpdateRoomController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private RoomCrudService roomService = new RoomCrudService();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateRoomController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * Displays the room edit form with current room details. Retrieves room data by
	 * ID and forwards to the edit form JSP.
	 *
	 * @param request  the HttpServletRequest containing the room ID parameter
	 * @param response the HttpServletResponse for sending responses
	 * @throws ServletException if a servlet error occurs
	 * @throws IOException      if an I/O error occurs
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int roomId = Integer.parseInt(request.getParameter("id"));
		try {
			RoomModel room = roomService.getRoomById(roomId);
			request.setAttribute("room", room);
			request.getRequestDispatcher("WEB-INF/pages/editRoomForm.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect("editroom"); // fallback
		}
	}

    /**
     * Handles POST requests for updating room information.
     * Processes form submission, updates room data, and redirects to room management page.
     * 
     * @param request the HttpServletRequest containing room update parameters
     * @param response the HttpServletResponse for redirecting after update
     * @throws ServletException if a servlet error occurs
     * @throws IOException if an I/O error occurs
     */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			RoomModel room = new RoomModel();
			room.setRoomId(Integer.parseInt(request.getParameter("roomId")));
			room.setRoomNumber(Integer.parseInt(request.getParameter("roomNumber")));
			room.setRoomImage(request.getParameter("roomImage"));
			room.setRoomType(request.getParameter("roomType"));
			room.setRoomGrade(request.getParameter("roomGrade"));
			room.setBedType(request.getParameter("bedType"));
			room.setRoomStatus(request.getParameter("roomStatus"));
			room.setPrice(Integer.parseInt(request.getParameter("price")));

			roomService.updateRoom(room);
		    // Set attributes for success modal
		    request.setAttribute("roomUpdateSuccess", true);
		    request.setAttribute("successMessage", "Room updated successfully");
		    request.getRequestDispatcher("/WEB-INF/pages/editRoomForm.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		    request.getRequestDispatcher("/WEB-INF/pages/editRoomForm.jsp").forward(request, response);
		}
		
	}

}
