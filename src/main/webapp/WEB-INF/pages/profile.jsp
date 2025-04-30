<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>User Profile - The Holiday Inn</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/viewProfile.css">
</head>
<body>
	<!-- Main Content - Profile Page -->
	<main>
		<div class="container">
			<div class="profile-container">
				<!-- Go Back Button -->
				<a href="dashboard.jsp" class="go-back-btn">Back</a>
				<div class="profile-sidebar">
					<div class="profile-image">
						<img class="profile-image"
							src="${pageContext.request.contextPath}/resources/images/system/profileIcon.png"
							alt="Profile Picture">
					</div>
					<h2 class="profile-name">${user.fullName}</h2>
					<form action="logout" method="post">
						<button type="submit" class="sidebar-btn logout-btn">Logout</button>
					</form>

				</div>

				<div class="profile-details">
					<h3 class="section-title">
						Information <a href="editProfile" class="action-btn">Edit
							Information</a>
					</h3>
					<div class="info-container">
						<div class="info-item">
							<div class="info-label">Full Name</div>
							<div class="info-value">${user.fullName}</div>
						</div>
						<div class="info-item">
							<div class="info-label">Username</div>
							<div class="info-value">${user.userName}</div>
						</div>
						<div class="info-item">
							<div class="info-label">Email</div>
							<div class="info-value">${user.email}</div>
						</div>
						<div class="info-item">
							<div class="info-label">Phone</div>
							<div class="info-value">${user.phone}</div>
						</div>
						<div class="info-item">
							<div class="info-label">Gender</div>
							<div class="info-value">${user.gender}</div>
						</div>
						<div class="info-item">
							<div class="info-label">Date of Birth</div>
							<div class="info-value">${user.dob}</div>
						</div>
						<div class="info-item">
							<div class="info-label">Citizenship Number</div>
							<div class="info-value">${user.citizenshipNumber}</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</main>
</body>
</html>