package com.HolidayInn.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import com.HolidayInn.Model.RoomModel;
import com.HolidayInn.service.RoomListingService;

/**
 * Servlet controller for handling room-related operations. Manages requests for
 * viewing and filtering available rooms by grade and price sorting. Supports both
 * GET and POST requests, forwarding results to the rooms view page.
 * 
 *  Uses RoomService for business logic operations
 *  
 * @author Santosh Lama 
 * LMU ID- 23048594
 * 
 */

@WebServlet(asyncSupported = true, urlPatterns = { "/rooms" })
public class RoomListingController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final RoomListingService roomService = new RoomListingService();

    /**
     * Handles GET requests for room listings. Retrieves rooms either filtered by grade/price 
     * or all available rooms if no filters specified. Forwards results to rooms.jsp view.
     *
     * @param request  HttpServletRequest containing optional roomGrade and priceSort parameters
     * @param response HttpServletResponse for rendering the view
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs during processing
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String roomGrade = request.getParameter("roomGrade");
        String priceSort = request.getParameter("priceSort");
        
        List<RoomModel> rooms;
        
        if (roomGrade != null && !roomGrade.isEmpty() || priceSort != null && !priceSort.isEmpty()) {
            // If search or filter parameters are present, fetch filtered data
            rooms = roomService.getFilteredRooms(roomGrade, priceSort);
        } else {
            // Otherwise fetch all rooms
            rooms = roomService.getAllRooms();
        }
        
        request.setAttribute("rooms", rooms);
        request.getRequestDispatcher("WEB-INF/pages/rooms.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}