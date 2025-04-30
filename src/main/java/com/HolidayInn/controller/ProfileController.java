package com.HolidayInn.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import com.HolidayInn.Model.UserModel;
import com.HolidayInn.service.ProfileService;
/**
 * Handles authenticated user profile requests. Validates sessions,
 * retrieves profile data via ProfileService, and renders the view.
 * Securely redirects unauthorized access attempts.
 */

@WebServlet(asyncSupported = true, urlPatterns = { "/profile" })
public class ProfileController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final ProfileService profileService = new ProfileService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Get user from session
		String username = (String) request.getSession().getAttribute("username");

		// If user is not in session (not logged in), redirect to login
		if (username == null) {
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}
		UserModel user = profileService.getUserDetails(username);

		if (user != null) {
			// Forward to view profile page with user data
			request.setAttribute("user", user);
		}
		request.getRequestDispatcher("/WEB-INF/pages/profile.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}