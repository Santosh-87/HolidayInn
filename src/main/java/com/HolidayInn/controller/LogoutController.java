package com.HolidayInn.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.HolidayInn.util.CookieUtil;
import com.HolidayInn.util.SessionUtil;

/**
 * The LoginController class handles user logout by clearing session data and cookies, then redirecting to login.
 * Security-focused cleanup ensures no residual user data remains after logout.
 * 
 * @author Santosh Lama
 * LMU ID- 23048591
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/logout" })
public class LogoutController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogoutController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * Handles user logout by performing security cleanup. Invalidates the current session
	 * to remove all session attributes and deletes the role cookie. Always redirects to
	 * login page after completing logout operations.
	 *
	 * @param request  HttpServletRequest containing the current session
	 * @param response HttpServletResponse for cookie deletion and redirect
	 * @throws ServletException if a servlet error occurs during processing
	 * @throws IOException if an I/O error occurs during redirect
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		CookieUtil.deleteCookie(response, "role");
		SessionUtil.invalidateSession(request);
		response.sendRedirect(request.getContextPath()+"/login");
	}

}
