<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Add New Room</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/editroomform.css">
</head>
<body>
	<div class="container">
		<!-- Main Content -->
		<div class="main-content">
			<div class="header">
				<h1 class="page-title">Add New Room</h1>
			</div>

			<!-- Form Error Display (top of form) -->
			<c:if test="${not empty formError}">
				<div class="form-error-message">${formError}</div>
			</c:if>

			<div class="form-container">
				<form action="addRoom" method="post" class="edit-form"
					enctype="multipart/form-data">
					<!-- Room Number -->
					<div class="form-group">
						<label for="roomNumber">Room Number:</label> <input type="number"
							id="roomNumber" name="roomNumber" value="${roomNumberValue}"
							required />
						<div class="error-message">
							<c:if test="${not empty roomNumberError}">${roomNumberError}</c:if>
						</div>
					</div>

					<!-- Room Image -->
					<div class="form-group">
						<label for="roomImage">Room Image:</label> <input type="file"
							id="roomImage" name="roomImage" accept="image/*" required />
						<div class="error-message">
							<c:if test="${not empty roomImageError}">${roomImageError}</c:if>
						</div>
					</div>

					<!-- Room Type -->
					<div class="form-group">
						<label for="roomType">Room Type:</label> <input type="text"
							id="roomType" name="roomType" value="${roomTypeValue}" required />
						<div class="error-message">
							<c:if test="${not empty roomTypeError}">${roomTypeError}</c:if>
						</div>
					</div>

					<!-- Room Grade -->
					<div class="form-group">
						<label for="roomGrade">Room Grade:</label> <input type="text"
							id="roomGrade" name="roomGrade" value="${roomGradeValue}"
							required />
						<div class="error-message">
							<c:if test="${not empty roomGradeError}">${roomGradeError}</c:if>
						</div>
					</div>

					<!-- Bed Type -->
					<div class="form-group">
						<label for="bedType">Bed Type:</label> <input type="text"
							id="bedType" name="bedType" value="${bedTypeValue}" required />
						<div class="error-message">
							<c:if test="${not empty bedTypeError}">${bedTypeError}</c:if>
						</div>
					</div>

					<!-- Room Status -->
					<div class="form-group">
						<label for="roomStatus">Room Status:</label> <input type="text"
							id="roomStatus" name="roomStatus" value="${roomStatusValue}"
							required />
						<div class="error-message">
							<c:if test="${not empty roomStatusError}">${roomStatusError}</c:if>
						</div>
					</div>

					<!-- Price -->
					<div class="form-group">
						<label for="price">Price:</label> <input type="number" id="price"
							name="price" value="${priceValue}" required />
						<div class="error-message">
							<c:if test="${not empty priceError}">${priceError}</c:if>
						</div>
					</div>

					<div class="form-actions">
						<button type="submit" class="btn-submit">Save Room</button>
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
	<c:if test="${roomAddSuccess}">
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