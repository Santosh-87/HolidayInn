package com.HolidayInn.controller;

import jakarta.servlet.ServletException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Enumeration;

import com.HolidayInn.Model.UserModel;
import com.HolidayInn.service.ProfileService;
import com.HolidayInn.util.ValidationUtil;

/**
 * Servlet controller handling user profile editing operations. Manages both the display
 * of the edit profile form (GET) and processing of profile updates (POST). Validates
 * user input and ensures data integrity before updating the profile in the database.
 * 
 * @author Santosh Lama 
 * LMU ID- 23048594
 */

@WebServlet(asyncSupported = true, urlPatterns = { "/editProfile" })
public class EditProfileController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final ProfileService profileService = new ProfileService();

    /**
     * Handles GET requests for the edit profile page. Loads the current user's data
     * and pre-populates the edit form with existing values.
     *
     * @param request the HttpServletRequest object containing client request data
     * @param response the HttpServletResponse object for sending responses
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs during request processing
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // Get current user from session
        String username = (String) request.getSession().getAttribute("username");
        if (username == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
        
        // Load user data
        UserModel user = profileService.getUserDetails(username);
        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/profile");
            return;
        }
        // Debugging - print image path to console
        System.out.println("User image path: " + user.getImagePath());
        
        // Pre-populate form values
        request.setAttribute("fullNameValue", user.getFullName());
        request.setAttribute("genderValue", user.getGender());
        request.setAttribute("dobValue", user.getDob());
        request.setAttribute("citizenshipNumberValue", user.getCitizenshipNumber());
        request.setAttribute("phoneNumberValue", user.getPhone());
        request.setAttribute("emailValue", user.getEmail());
        request.setAttribute("user", user);
        
        
        request.getRequestDispatcher("/WEB-INF/pages/editProfile.jsp").forward(request, response);
    }

    /**
     * Handles POST requests for profile updates. Validates form data and processes
     * the profile update if validation passes.
     *
     * @param request the HttpServletRequest object containing form submission data
     * @param response the HttpServletResponse object for sending responses
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs during request processing
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // Get current username from session
        String currentUsername = (String) request.getSession().getAttribute("username");
        if (currentUsername == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
        
        validateProfileForm(request, response, currentUsername);
    }

    /**
     * Validates all form fields for profile updates. Checks for required fields,
     * proper formatting, and uniqueness of phone/email. Sets error messages
     * in the request attributes if validation fails.
     *
     * @param request the HttpServletRequest object containing form data to validate
     * @param response the HttpServletResponse object for sending responses
     * @param currentUsername the username of the currently logged-in user
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs during request processing
     */
    private void validateProfileForm(HttpServletRequest request, HttpServletResponse response, 
            String currentUsername) throws ServletException, IOException {
        
        // Extract form parameters
        String fullName = request.getParameter("fullName");
        String gender = request.getParameter("gender");
        String dob = request.getParameter("dob");
        String citizenshipNumber = request.getParameter("citizenshipNumber");
        String phoneNumber = request.getParameter("phoneNumber");
        String email = request.getParameter("email");
        
        // Get current user data
        UserModel currentUser = profileService.getUserDetails(currentUsername);
        if (currentUser == null) {
            response.sendRedirect(request.getContextPath() + "/profile");
            return;
        }

        // Validate fields
        boolean hasErrors = false;
        
        if (ValidationUtil.isNullOrEmpty(fullName)) {
            request.setAttribute("fullNameError", "Full name is required.");
            hasErrors = true;
        } else if (!ValidationUtil.isValidFullName(fullName)) {
            request.setAttribute("fullNameError", "Full name must contain only letters.");
            hasErrors = true;
        }

        if (ValidationUtil.isNullOrEmpty(gender)) {
            request.setAttribute("genderError", "Please select a gender.");
            hasErrors = true;
        }

        if (ValidationUtil.isNullOrEmpty(dob)) {
            request.setAttribute("dobError", "Date of birth is required.");
            hasErrors = true;
        } else {
            try {
                LocalDate birthDate = LocalDate.parse(dob);
                if (birthDate.isAfter(LocalDate.now())) {
                    request.setAttribute("dobError", "Date of birth cannot be in the future.");
                    hasErrors = true;
                }
            } catch (DateTimeException e) {
                request.setAttribute("dobError", "Invalid date format. Please use yyyy-MM-dd.");
                hasErrors = true;
            }
        }

        if (ValidationUtil.isNullOrEmpty(citizenshipNumber)) {
            request.setAttribute("citizenshipNumberError", "Citizenship number is required.");
            hasErrors = true;
        } else if (!ValidationUtil.isValidCitizenshipNumber(citizenshipNumber)) {
            request.setAttribute("citizenshipNumberError", "Citizenship number must be exactly 14 digits.");
            hasErrors = true;
        }

        if (ValidationUtil.isNullOrEmpty(phoneNumber)) {
            request.setAttribute("phoneError", "Phone number is required.");
            hasErrors = true;
        } else if (!ValidationUtil.isValidPhoneNumber(phoneNumber)) {
            request.setAttribute("phoneError", "Phone number must start with '98' and be 10 digits long.");
            hasErrors = true;
        } else if (!phoneNumber.equals(currentUser.getPhone()) && 
                profileService.isPhoneTaken(phoneNumber, currentUsername)) {
            request.setAttribute("phoneError", "This phone number is already registered.");
            hasErrors = true;
        }

        if (ValidationUtil.isNullOrEmpty(email)) {
            request.setAttribute("emailError", "Email is required.");
            hasErrors = true;
        } else if (!ValidationUtil.isValidEmail(email)) {
            request.setAttribute("emailError", "Invalid email format.");
            hasErrors = true;
        } else if (!email.equals(currentUser.getEmail()) && 
                profileService.isEmailTaken(email, currentUsername)) {
            request.setAttribute("emailError", "This email is already registered.");
            hasErrors = true;
        }

        if (hasErrors) {
            // Repopulate form fields with submitted values
            request.setAttribute("fullNameValue", fullName);
            request.setAttribute("genderValue", gender);
            request.setAttribute("dobValue", dob);
            request.setAttribute("citizenshipNumberValue", citizenshipNumber);
            request.setAttribute("phoneNumberValue", phoneNumber);
            request.setAttribute("emailValue", email);
            
            request.getRequestDispatcher("/WEB-INF/pages/editProfile.jsp").forward(request, response);
            return;
        }

        // Update user profile
        currentUser.setFullName(fullName);
        currentUser.setGender(gender);
        currentUser.setDob(dob);
        currentUser.setCitizenshipNumber(citizenshipNumber);
        currentUser.setPhone(phoneNumber);
        currentUser.setEmail(email);

        boolean success = profileService.updateUser(currentUser, currentUsername);
        if (success) {
            // Set attributes for success modal
            request.setAttribute("profileUpdateSuccess", true);
            request.setAttribute("successMessage", "Profile details updated successfully");
            
            // Forward to the same page to show modal
            request.getRequestDispatcher("/WEB-INF/pages/editProfile.jsp").forward(request, response);
        } else {
            request.setAttribute("formError", "Something went wrong while updating. Please try again.");
            request.getRequestDispatcher("/WEB-INF/pages/editProfile.jsp").forward(request, response);
        }
    }
}