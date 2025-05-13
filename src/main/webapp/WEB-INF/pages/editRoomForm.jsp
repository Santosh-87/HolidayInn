<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Edit Room</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/editroomform.css">
</head>
<body>
	<div class="container">
		<div class="main-content">
			<div class="header">
				<h1 class="page-title">Edit Room</h1>
			</div>

			<div class="form-container">
				<form action="updateRoom" method="post" class="edit-form">
					<input type="hidden" name="roomId" value="${room.roomId}" />

					<div class="form-group">
						<label for="roomNumber">Room Number:</label> <input type="number"
							id="roomNumber" name="roomNumber" value="${room.roomNumber}"
							required />
					</div>

					<div class="form-group">
						<label for="roomImage">Room Image URL:</label> <input type="text"
							id="roomImage" name="roomImage" value="${room.roomImage}"
							required />
					</div>

					<div class="form-group">
						<label for="roomType">Room Type:</label> <input type="text"
							id="roomType" name="roomType" value="${room.roomType}" required />
					</div>

					<div class="form-group">
						<label for="roomGrade">Room Grade:</label> <input type="text"
							id="roomGrade" name="roomGrade" value="${room.roomGrade}"
							required />
					</div>

					<div class="form-group">
						<label for="bedType">Bed Type:</label> <input type="text"
							id="bedType" name="bedType" value="${room.bedType}" required />
					</div>

					<div class="form-group">
						<label for="roomStatus">Room Status:</label> <input type="text"
							id="roomStatus" name="roomStatus" value="${room.roomStatus}"
							required />
					</div>

					<div class="form-group">
						<label for="price">Price:</label> <input type="number" id="price"
							name="price" value="${room.price}" required />
					</div>

					<div class="form-group">
						<button type="submit" class="btn-submit">Update Room</button>
						<a href="editroom" class="btn-cancel">Cancel</a>
					</div>
				</form>
			</div>
		</div>
	</div>
	<!-- Success Modal -->
	<div id="successModal" class="modal">
		<div class="modal-content">
			<div class="success-icon">✓</div>
			<h3>Success!</h3>
			<p>${successMessage}</p>
		</div>
	</div>

	<!-- JavaScript to handle the success modal and redirect -->
	<c:if test="${roomUpdateSuccess}">
		<script>
			document
					.addEventListener(
							'DOMContentLoaded',
							function() {
								// Show the success modal
								var modal = document
										.getElementById('successModal');
								modal.style.display = "block";

								// Redirect after a delay
								setTimeout(
										function() {
											window.location.href = "${pageContext.request.contextPath}/editroom";
										}, 2000); // 2 seconds delay
							});
		</script>
	</c:if>
</body>
</html>