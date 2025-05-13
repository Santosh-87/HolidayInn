package com.HolidayInn.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.util.Enumeration;

import com.HolidayInn.Model.RoomModel;
import com.HolidayInn.service.RoomCrudService;
import com.HolidayInn.util.ImageUtil;
import com.HolidayInn.util.ValidationUtil;

/**
 * Controller for handling all room addition operations including:
 * Displaying the room creation form, processing form submissions,
 * Validating inputs, handling image uploads and saving room data 
 * to database.
 * 
 * @author Santosh Lama 
 * LMU ID- 23048594
 */

@WebServlet(asyncSupported = true, urlPatterns = { "/addRoom" })
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
maxFileSize = 1024 * 1024 * 10, // 10MB
maxRequestSize = 1024 * 1024 * 50) // 50MB
public class AddRoomController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final ImageUtil imageUtil = new ImageUtil();
	private static final RoomCrudService roomService = new RoomCrudService();


	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddRoomController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Displays the room addition form. Forwards to the JSP page containing the room
	 * creation form.
	 *
	 * @param request  the HttpServletRequest object
	 * @param response the HttpServletResponse object
	 * @throws ServletException if a servlet error occurs
	 * @throws IOException      if an I/O error occurs
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("/WEB-INF/pages/addroomform.jsp").forward(request, response);
	}

    /**
     * Processes room creation form submissions.
     * Validates input, handles image upload, and saves room data.
     *
     * @param request the HttpServletRequest containing form data
     * @param response the HttpServletResponse for sending responses
     * @throws ServletException if a servlet error occurs
     * @throws IOException if an I/O error occurs
     */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Extract all parameters
		String roomNumber = request.getParameter("roomNumber");
		String roomType = request.getParameter("roomType");
		String roomGrade = request.getParameter("roomGrade");
		String bedType = request.getParameter("bedType");
		String roomStatus = request.getParameter("roomStatus");
		String price = request.getParameter("price");
		Part roomImagePart = request.getPart("roomImage");


		// Handle room image upload
		String roomImagePath = null;
		if (roomImagePart == null || roomImagePart.getSize() == 0) {
			request.setAttribute("roomImageError", "Room image is required.");
		} else if (!ValidationUtil.isValidImageExtension(roomImagePart)) {
			request.setAttribute("roomImageError", "Only JPG, JPEG, PNG, or GIF images are allowed.");
		} else {
			String saveFolder = "room_images";
			if (imageUtil.uploadImage(roomImagePart, request.getServletContext().getRealPath("/"), saveFolder)) {
				roomImagePath = imageUtil.getImageNameFromPart(roomImagePart);
			} else {
				request.setAttribute("roomImageError", "Failed to upload the image. Please try again.");
			}
		}

		// Check for any errors
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
			request.setAttribute("roomNumberValue", roomNumber);
			request.setAttribute("roomTypeValue", roomType);
			request.setAttribute("roomGradeValue", roomGrade);
			request.setAttribute("bedTypeValue", bedType);
			request.setAttribute("roomStatusValue", roomStatus);
			request.setAttribute("priceValue", price);

			request.getRequestDispatcher("/WEB-INF/pages/addRoomForm.jsp").forward(request, response);
			return;
		}

		try {
			RoomModel room = new RoomModel();
			room.setRoomNumber(Integer.parseInt(roomNumber));
			room.setRoomImage(roomImagePath);
			room.setRoomType(roomType);
			room.setRoomGrade(roomGrade);
			room.setBedType(bedType);
			room.setRoomStatus(roomStatus);
			room.setPrice(Integer.parseInt(price));

			roomService.saveRoom(room);

		    // Set attributes for success modal
		    request.setAttribute("roomAddSuccess", true);
		    request.setAttribute("successMessage", "Room added successfully");
		    
		    request.getRequestDispatcher("/WEB-INF/pages/addroomform.jsp").forward(request, response);
		} catch (Exception e) {
			request.setAttribute("formError", "Something went wrong while saving the room details. Please try again.");
			request.getRequestDispatcher("/WEB-INF/pages/addroomform.jsp").forward(request, response);
		}
	}

}
