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
import com.HolidayInn.service.EditProfileService;
import com.HolidayInn.util.ValidationUtil;

@WebServlet(asyncSupported = true, urlPatterns = { "/editProfile" })
public class editProfileController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final EditProfileService editProfileService = new EditProfileService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // Get current user from session
        String username = (String) request.getSession().getAttribute("username");
        if (username == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
        
        // Load user data
        UserModel user = editProfileService.getUserByUsername(username);
        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/profile");
            return;
        }
        
        // Pre-populate form values
        request.setAttribute("fullNameValue", user.getFullName());
        request.setAttribute("genderValue", user.getGender());
        request.setAttribute("dobValue", user.getDob());
        request.setAttribute("citizenshipNumberValue", user.getCitizenshipNumber());
        request.setAttribute("phoneNumberValue", user.getPhone());
        request.setAttribute("emailValue", user.getEmail());
        
        request.getRequestDispatcher("/WEB-INF/pages/editProfile.jsp").forward(request, response);
    }

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
        UserModel currentUser = editProfileService.getUserByUsername(currentUsername);
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
                editProfileService.isPhoneTaken(phoneNumber, currentUsername)) {
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
                editProfileService.isEmailTaken(email, currentUsername)) {
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

        boolean success = editProfileService.updateUser(currentUser, currentUsername);
        if (success) {
            response.sendRedirect(request.getContextPath() + "/profile");
        } else {
            request.setAttribute("formError", "Something went wrong while updating. Please try again.");
            request.getRequestDispatcher("/WEB-INF/pages/editProfile.jsp").forward(request, response);
        }
    }
}