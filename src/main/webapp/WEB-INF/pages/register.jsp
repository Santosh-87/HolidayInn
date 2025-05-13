<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>User Registration</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/register.css">
</head>
<body>
	<div class="container">
		<div class="left-panel">
			<div class="profile-circle">
				<img class="profile-image"
					src="${pageContext.request.contextPath}/resources/images/system/profileIcon.png"
					alt="Profile Picture">
			</div>
			<h1>User Registration</h1>
			<p>Please fill in your details to create an account</p>
		</div>

		<div class="right-panel">
			<h2>Registration Form</h2>
			<form id="registrationForm" method="post" action="register"
				enctype="multipart/form-data">
				<!-- Name -->
				<div class="form-row">
					<div class="form-group">
						<label for="fullName">Full Name</label> <input type="text"
							id="fullName" name="fullName" value="${fullNameValue}">
						<div class="error-message" id="firstNameError">
							<c:if test="${not empty fullNameError}">${fullNameError}</c:if>
						</div>
					</div>
					<div class="form-group">
						<label for="userName">User Name</label> <input type="text"
							id="userName" name="userName" value="${userNameValue}">
						<div class="error-message" id="lastNameError">
							<c:if test="${not empty userNameError}">${userNameError}</c:if>
						</div>
					</div>
				</div>

				<!-- Gender & DOB -->
				<div class="form-row">
					<div class="form-group">
						<label for="gender">Gender</label> <select id="gender"
							name="gender">
							<option value="">Please Select</option>
							<option value="Male" ${genderValue == 'Male' ? 'selected' : ''}>Male</option>
							<option value="Female"
								${genderValue == 'Female' ? 'selected' : ''}>Female</option>
							<option value="Other" ${genderValue == 'Other' ? 'selected' : ''}>Other</option>
						</select>
						<div class="error-message" id="genderError">
							<c:if test="${not empty genderError}">${genderError}</c:if>
						</div>
					</div>
					<div class="form-group">
						<label for="dob">Date of Birth</label> <input type="date" id="dob"
							name="dob" value="${dobValue}">
						<div class="error-message" id="dobError">
							<c:if test="${not empty dobError}">${dobError}</c:if>
						</div>
					</div>
				</div>

				<!-- Citizenship & Phone -->
				<div class="form-row">
					<div class="form-group">
						<label for="citizenshipNumber">Citizenship Number</label> <input
							type="text" id="citizenshipNumber" name="citizenshipNumber"
							value="${citizenshipNumberValue}">
						<div class="error-message" id="citizenshipNumberError">
							<c:if test="${not empty citizenshipNumberError}">${citizenshipNumberError}</c:if>
						</div>
					</div>
					<div class="form-group">
						<label for="phoneNumber">Phone</label> <input type="text"
							id="phoneNumber" name="phoneNumber" value="${phoneNumberValue}">
						<div class="error-message" id="phoneError">
							<c:if test="${not empty phoneError}">${phoneError}</c:if>
						</div>
					</div>
				</div>

				<!-- Email and User Picture-->
				<div class="form-row">
					<div class="form-group">
						<label for="email">Email</label> <input type="email" id="email"
							name="email" value="${emailValue}">
						<div class="error-message" id="emailError">
							<c:if test="${not empty emailError}">${emailError}</c:if>
						</div>
					</div>
					<div class="form-group">

						<label for="profilePath">Profile Picture</label> <input
							type="file" id="profilePath" name="profilePath" accept="image/*">
						<div class="error-message" id="profilePathError">
							<c:if test="${not empty profilePathError}">${profilePathError}</c:if>
						</div>

					</div>
				</div>

				<!-- Password -->
				<div class="form-row">
					<div class="form-group">
						<label for="password">Password</label> <input type="password"
							id="password" name="password">
						<div class="error-message" id="passwordError">
							<c:if test="${not empty passwordError}">${passwordError}</c:if>
						</div>
					</div>
					<div class="form-group">
						<label for="confirmPassword">Confirm Password</label> <input
							type="password" id="confirmPassword" name="confirmPassword">
						<div class="error-message" id="confirmPasswordError">
							<c:if test="${not empty confirmPasswordError}">${confirmPasswordError}</c:if>
						</div>
					</div>
				</div>

				<button type="submit">Register</button>
			</form>
		</div>
	</div>
	
	<!-- Success Modal -->
	<div id="successModal" class="modal">
		<div class="modal-content">
			<div class="success-icon">✓</div>
			<h3>Registration Successful!</h3>
			<p>${successMessage}</p>
		</div>
	</div>

	<!-- JavaScript to handle the success modal and redirect -->
	<c:if test="${registrationSuccess}">
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
											window.location.href = "${pageContext.request.contextPath}/login";
										}, 2000); // 2 seconds delay
							});
		</script>
	</c:if>

</body>
</html>