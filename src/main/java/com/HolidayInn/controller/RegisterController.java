package com.HolidayInn.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Enumeration;

import com.HolidayInn.Model.UserModel;
import com.HolidayInn.service.RegisterService;
import com.HolidayInn.util.ImageUtil;
import com.HolidayInn.util.ValidationUtil;

/**
 * It is a servlet class implementation which Handles user registration for the
 * HolidayInn application. Processes form data, validates user input, checks for
 * duplicates, uploads profile images, and creates a new user if all validations
 * pass.
 * 
 * @author Santosh Lama
 * LMU ID- 23048594
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/Register" })
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
		maxFileSize = 1024 * 1024 * 10, // 10MB
		maxRequestSize = 1024 * 1024 * 50) // 50MB
public class RegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final ImageUtil imageUtil = new ImageUtil();
	private final RegisterService registerService = new RegisterService();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegisterController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("WEB-INF/pages/register.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		validateRegistrationForm(request, response);
	}

	public void validateRegistrationForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Extract all parameters
		String fullName = request.getParameter("fullName");
		String userName = request.getParameter("userName");
		String gender = request.getParameter("gender");
		String dob = request.getParameter("dob");
		String citizenshipNumber = request.getParameter("citizenshipNumber");
		String phoneNumber = request.getParameter("phoneNumber");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		Part profileImagePart = request.getPart("profilePath");
		String confirmPassword = request.getParameter("confirmPassword");

		// Validate required fields
		if (ValidationUtil.isNullOrEmpty(fullName)) {
			request.setAttribute("fullNameError", "Full name is required.");
		} else if (!ValidationUtil.isValidFullName(fullName)) {
			request.setAttribute("fullNameError", "Full name must not contain numbers or special characters.");
		}

		if (ValidationUtil.isNullOrEmpty(userName)) {
			request.setAttribute("userNameError", "User name is required.");
		} else if (!ValidationUtil.isAlphanumericStartingWithLetter(userName)) {
			request.setAttribute("userNameError", "User name should start with a letter and have numbers as well.");
		} else if (registerService.isUsernameTaken(userName)) {
			request.setAttribute("userNameError", "This username is already taken.");
		}

		// Date of Birth validation
		if (ValidationUtil.isNullOrEmpty(dob)) {
			request.setAttribute("dobError", "Date of birth is required.");
		} else {
			try {
				LocalDate birthDate = LocalDate.parse(dob);
				if (birthDate.isAfter(LocalDate.now())) {
					request.setAttribute("dobError", "Date of birth cannot be in the future.");
				}
			} catch (DateTimeException e) {
				request.setAttribute("dobError", "Invalid date format. Please use yyyy-MM-dd.");
			}
		}

		if (ValidationUtil.isNullOrEmpty(gender)) {
			request.setAttribute("genderError", "Please select a gender.");
		}

		// Citizenship number validation
		if (ValidationUtil.isNullOrEmpty(citizenshipNumber)) {
			request.setAttribute("citizenshipNumberError", "Citizenship number is required.");
		} else if (!ValidationUtil.isValidCitizenshipNumber(citizenshipNumber)) {
			request.setAttribute("citizenshipNumberError", "Citizenship number must be exactly 14 digits.");
		} else if (registerService.isCitizenshipNumberTaken(citizenshipNumber)) {
			request.setAttribute("citizenshipNumberError", "This citizenship number is already taken.");
		}

		// Phone number validation
		if (ValidationUtil.isNullOrEmpty(phoneNumber)) {
			request.setAttribute("phoneError", "Phone number is required.");
		} else if (!ValidationUtil.isValidPhoneNumber(phoneNumber)) {
			request.setAttribute("phoneError", "Phone number must start with '98' and be 10 digits long.");
		} else if (registerService.isPhoneTaken(phoneNumber)) {
			request.setAttribute("phoneError", "This phone number is already registered.");
		}

		// Email validation
		if (ValidationUtil.isNullOrEmpty(email)) {
			request.setAttribute("emailError", "Email is required.");
		} else if (!ValidationUtil.isValidEmail(email)) {
			request.setAttribute("emailError", "Invalid email format.");
		} else if (registerService.isEmailTaken(email)) {
			request.setAttribute("emailError", "This email is already registered.");
		}

		// Validate image if it was uploaded

		// Validate profile image
		// Initialize image path
		String profileImagePath = null;

		// Validate profile image - moved to top of validations
		if (profileImagePart == null || profileImagePart.getSize() == 0) {
			request.setAttribute("profilePathError", "Profile image is required.");
		} else if (!ValidationUtil.isValidImageExtension(profileImagePart)) {
			request.setAttribute("profilePathError", "Only JPG, JPEG, PNG, or GIF images are allowed.");
		} else {
			// Upload the image if validation passes
			String saveFolder = "profile_images";
			if (imageUtil.uploadImage(profileImagePart, request.getServletContext().getRealPath("/"), saveFolder)) {
				profileImagePath = imageUtil.getImageNameFromPart(profileImagePart);
			} else {
				request.setAttribute("profilePathError", "Failed to upload the image. Please try again.");
			}
		}
		// Password validation
		if (ValidationUtil.isNullOrEmpty(password)) {
			request.setAttribute("passwordError", "Password is required.");
		} else if (!ValidationUtil.isValidPassword(password)) {
			request.setAttribute("passwordError",
					"Password must be at least 8 characters long, with 1 uppercase letter, 1 number, and 1 symbol.");
		}

		// Password confirmation
		if (ValidationUtil.isNullOrEmpty(confirmPassword)) {
			request.setAttribute("confirmPasswordError", "Please retype the password.");
		} else if (!ValidationUtil.doPasswordsMatch(password, confirmPassword)) {
			request.setAttribute("confirmPasswordError", "Passwords do not match.");
		}

		// Check for any errors using Enumeration
		boolean hasErrors = false;
		Enumeration<String> attributeNames = request.getAttributeNames();
		while (attributeNames.hasMoreElements()) {
			String attrName = attributeNames.nextElement();
			if (attrName.endsWith("Error")) {
				hasErrors = true;
				break;
			}
		}

		if (hasErrors) {
			// Repopulate form fields
			request.setAttribute("fullNameValue", fullName);
			request.setAttribute("userNameValue", userName);
			request.setAttribute("genderValue", gender);
			request.setAttribute("dobValue", dob);
			request.setAttribute("citizenshipNumberValue", citizenshipNumber);
			request.setAttribute("phoneNumberValue", phoneNumber);
			request.setAttribute("emailValue", email);

			request.getRequestDispatcher("WEB-INF/pages/register.jsp").forward(request, response);
			return;
		}

		UserModel user = new UserModel(fullName, userName, gender, dob, citizenshipNumber, phoneNumber, email,
				profileImagePath, password);

		boolean success = registerService.addUser(user);
		if (success) {
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		} else {
			request.setAttribute("formError", "Something went wrong while registering. Please try again.");
			request.getRequestDispatcher("WEB-INF/pages/register.jsp").forward(request, response);
			return;
		}

	}

}
