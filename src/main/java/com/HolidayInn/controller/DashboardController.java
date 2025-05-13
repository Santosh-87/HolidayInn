package com.HolidayInn.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.HolidayInn.Model.BookingDetailsModel;
import com.HolidayInn.Model.RoomModel;
import com.HolidayInn.service.DashboardService;

/**
 * Controller for handling dashboard operations and displaying key metrics.
 * Retrieves and presents business insights including recent bookings, revenue data,
 * and operational statistics for administrative users.
 * 
 * @author Santosh Lama
 * LMU ID- 23048594
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/dashboard" })
public class DashboardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final DashboardService dashboardService = new DashboardService();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DashboardController() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * Handles HTTP GET requests for dashboard data.
     * Retrieves and displays key metrics including recent bookings, top rated room,
     * average stay duration, current year revenue, and busiest check-in day.
     *
     * @param request the HttpServletRequest object
     * @param response the HttpServletResponse object
     * @throws ServletException if a servlet error occurs
     * @throws IOException if an I/O error occurs
     */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<BookingDetailsModel> recentBookings = dashboardService.getRecentBookings(5);
        request.setAttribute("recentBookings", recentBookings);
        try {
			String topRatedRoom = dashboardService.getTopRatedRoom();
			double avgStayDuration = dashboardService.getAverageStayDuration();
			double currentMonthRevenue = dashboardService.getCurrentMonthRevenue();
	        Map<String, Object> busiestDay = dashboardService.getBusiestCheckInDay();
	        RoomModel mostExpensiveRoom = dashboardService.getMostExpensiveRoom();
	        
	        request.setAttribute("topRatedRoom", topRatedRoom);
	        request.setAttribute("avgStayDuration", avgStayDuration);
	        request.setAttribute("currentMonthRevenue", currentMonthRevenue);
	        request.setAttribute("busiestDay", busiestDay);
	        request.setAttribute("mostExpensiveRoom", mostExpensiveRoom);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.getRequestDispatcher("WEB-INF/pages/dashboard.jsp").forward(request, response);
	}

}
