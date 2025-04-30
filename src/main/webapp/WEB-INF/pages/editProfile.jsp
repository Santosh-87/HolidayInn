<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Edit Profile - The Holiday Inn</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/editProfile.css">
</head>
<body>
	<!-- Main Content - Edit Profile Page -->
	<main>
		<div class="container">
			<div class="profile-container">
				<div class="profile-sidebar">
					<div class="profile-image">
						<img class="profile-image"
							src="${pageContext.request.contextPath}/resources/images/system/profileIcon.png"
							alt="Profile Picture">
					</div>
					<h2 class="profile-name">${user.fullName}</h2>
					<a href="profile" class="sidebar-btn view-btn">View Profile</a> <a
						href="logout" class="sidebar-btn logout-btn">Logout</a>
				</div>

				<div class="profile-details">
					<h3 class="section-title">Edit Profile Information</h3>
					<form action="editProfile" method="post">
						<div class="edit-container">
							<div class="edit-item">
								<label class="edit-label">Full Name</label> <input type="text"
									class="edit-input" name="fullName" value="${fullNameValue}">
								<div class="error-message">
									<c:if test="${not empty fullNameError}">${fullNameError}</c:if>
								</div>
							</div>
							<div class="edit-item">
								<label class="edit-label">Email</label> <input type="email"
									class="edit-input" name="email" value="${emailValue}">
								<div class="error-message">
									<c:if test="${not empty emailError}">${emailError}</c:if>
								</div>
							</div>
							<div class="edit-item">
								<label class="edit-label">Phone</label> <input type="tel"
									class="edit-input" name="phoneNumber"
									value="${phoneNumberValue}">
								<div class="error-message">
									<c:if test="${not empty phoneError}">${phoneError}</c:if>
								</div>
							</div>
							<div class="edit-item">
								<label class="edit-label">Gender</label> <select
									class="edit-input" name="gender">
									<option value="">Please Select</option>
									<option value="Male" ${genderValue == 'Male' ? 'selected' : ''}>Male</option>
									<option value="Female"
										${genderValue == 'Female' ? 'selected' : ''}>Female</option>
									<option value="Other"
										${genderValue == 'Other' ? 'selected' : ''}>Other</option>
								</select>
								<div class="error-message">
									<c:if test="${not empty genderError}">${genderError}</c:if>
								</div>
							</div>
							<div class="edit-item">
								<label class="edit-label">Date of Birth</label> <input
									type="date" class="edit-input" name="dob" value="${dobValue}">
								<div class="error-message">
									<c:if test="${not empty dobError}">${dobError}</c:if>
								</div>
							</div>
							<div class="edit-item">
								<label class="edit-label">Citizenship Number</label> <input
									type="text" class="edit-input" name="citizenshipNumber"
									value="${citizenshipNumberValue}">
								<div class="error-message">
									<c:if test="${not empty citizenshipNumberError}">${citizenshipNumberError}</c:if>
								</div>
							</div>
							<div class="buttons-container">
								<a href="profile" class="cancel-btn">Cancel</a>
								<button type="submit" class="update-btn">Update Profile</button>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</main>
</body>
</html>