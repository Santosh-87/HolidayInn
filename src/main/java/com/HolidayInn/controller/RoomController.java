package com.HolidayInn.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import com.HolidayInn.Model.RoomModel;
import com.HolidayInn.service.RoomService;

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
public class RoomController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final RoomService roomService = new RoomService();

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