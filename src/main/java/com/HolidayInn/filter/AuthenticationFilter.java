package com.HolidayInn.filter;

import jakarta.servlet.Filter;

import jakarta.servlet.FilterChain;

import jakarta.servlet.FilterConfig;

import jakarta.servlet.ServletException;

import jakarta.servlet.ServletRequest;

import jakarta.servlet.ServletResponse;

import jakarta.servlet.annotation.WebFilter;

import jakarta.servlet.http.HttpServletRequest;

import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import com.HolidayInn.util.CookieUtil;

import com.HolidayInn.util.SessionUtil;

/**
 * Authentication filter that controls access to application resources based on user roles.
 * Filters all incoming requests to enforce proper authentication and authorization.
 * 
 * <p>Routes users based on their role (admin/customer/unauthenticated) and prevents
 * unauthorized access to protected resources. Handles static resources separately.
 * 
 * @author Santosh Lama 
 * LMU ID- 23048594
 */
@WebFilter(asyncSupported = true, urlPatterns = "/*")

public class AuthenticationFilter implements Filter {

	private static final String LOGIN = "/login";
	private static final String LOGOUT = "/logout";
	private static final String REGISTER = "/register";
	private static final String HOME = "/home";
	private static final String ROOT = "/";
	private static final String CRUD = "/editroom";
	private static final String DASHBOARD = "/dashboard";
	private static final String ADD = "/addRoom";
	private static final String UPDATEFORM = "/EditRoomForm";
	private static final String UPDATE = "/updateRoom";
	private static final String DELETE = "/deleteRoom";
	private static final String ABOUT = "/aboutus";
	private static final String CONTACT = "/contactus";
	private static final String PROFILE = "/profile";
	private static final String ROOMS = "/rooms";
	private static final String EDITPROFILE = "/editProfile";

	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

    /**
     * Main filter method that processes each request to enforce authentication rules.
     * Routes requests based on user role and authentication status.
     *
     * @param request the ServletRequest object containing client request
     * @param response the ServletResponse object for sending responses
     * @param chain the FilterChain for invoking the next filter in the chain
     * @throws IOException if an I/O error occurs during request processing
     * @throws ServletException if a servlet-specific error occurs
     */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)

			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		String uri = req.getRequestURI();

		// Allow static resources

		if (uri.endsWith(".png") || uri.endsWith(".jpg") || uri.endsWith(".css") || uri.endsWith(".js") || uri.endsWith(".webp")) {
			chain.doFilter(request, response);
			return;
		}
		boolean isLoggedIn = SessionUtil.getAttribute(req, "username") != null;
		String userRole = CookieUtil.getCookie(req, "role") != null
				? CookieUtil.getCookie(req, "role").getValue()
				: null;
		if ("admin".equals(userRole)) {
			if (uri.endsWith(LOGIN) || uri.endsWith(REGISTER)) {
				res.sendRedirect(req.getContextPath() + DASHBOARD);
			} else if (uri.endsWith(DASHBOARD)  || uri.endsWith(CRUD) || uri.endsWith(ADD) || uri.endsWith(UPDATEFORM) ||  uri.endsWith(UPDATE) || uri.endsWith(DELETE) || uri.endsWith(LOGOUT) ||  uri.endsWith(HOME) || uri.endsWith(ROOT) || uri.endsWith(PROFILE) || uri.endsWith(EDITPROFILE)) {
				chain.doFilter(request, response);
			} else {
				res.sendRedirect(req.getContextPath() + DASHBOARD);
			}
			
		} else if ("customer".equals(userRole)) {
			if (uri.endsWith(LOGIN) || uri.endsWith(REGISTER)) {
				res.sendRedirect(req.getContextPath() + HOME);
			} else if (uri.endsWith(HOME) || uri.endsWith(ROOT) || uri.endsWith(ABOUT)
					|| uri.endsWith(CONTACT) || uri.endsWith(PROFILE) || uri.endsWith(ROOMS)
					|| uri.endsWith(EDITPROFILE) || uri.endsWith(LOGOUT) ) {

				chain.doFilter(request, response);

			} else if (uri.endsWith(DASHBOARD) || uri.endsWith(UPDATE)) {
				res.sendRedirect(req.getContextPath() + HOME);
			} else {
				res.sendRedirect(req.getContextPath() + HOME);
			}

		} else {
			// Not logged in
			if (uri.endsWith(LOGIN) || uri.endsWith(REGISTER) || uri.endsWith(HOME) || uri.endsWith(ROOT)) {
				chain.doFilter(request, response);
			} else {
				res.sendRedirect(req.getContextPath() + LOGIN);
			}
		}

	}

}