package com.HolidayInn.util;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
/**
 * SessionUtil provides helper methods to manage user session data.
 * It simplifies setting, getting, removing, and invalidating session attributes
 * in HttpServletRequest, promoting cleaner and reusable code.
 * 
 * @author Santosh Lama 
 * LMU ID- 23048594
 * 
 */

public class SessionUtil {
    /**
     * Sets an attribute in the session.
     *
     * @param request the HttpServletRequest from which the session is obtained
     * @param key     the key under which the attribute is stored
     * @param value   the value of the attribute to store in the session
     */
    public static void setAttribute(HttpServletRequest request, String key, Object value) {
        HttpSession session = request.getSession();
        session.setAttribute(key, value);
    }

    /**
     * Retrieves an attribute from the session.
     *
     * @param request the HttpServletRequest from which the session is obtained
     * @param key     the key of the attribute to retrieve
     * @return the attribute value, or null if the attribute does not exist or the session is invalid
     */
    public static Object getAttribute(HttpServletRequest request, String key) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            return session.getAttribute(key);
        }
        return null;
    }

    /**
     * Removes an attribute from the session.
     *
     * @param request the HttpServletRequest from which the session is obtained
     * @param key     the key of the attribute to remove
     */
    public static void removeAttribute(HttpServletRequest request, String key) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.removeAttribute(key);
        }
    }

    /**
     * Invalidates the current session.
     *
     * @param request the HttpServletRequest from which the session is obtained
     */
    public static void invalidateSession(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
    }
}
