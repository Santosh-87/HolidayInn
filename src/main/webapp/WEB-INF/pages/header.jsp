<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
</head>
<body>
	<!-- Header & Navigation -->
	<header>
		<div class="container header-container">
			<div class="logo">
				<img
					src="${pageContext.request.contextPath}/resources/images/system/Hotel Logo.png"
					alt="The Holiday Inn Logo">
				<h1>
					The <span>Holiday</span> Inn
				</h1>
			</div>
			<nav>
				<ul>
					<li><a href="home">Home</a></li>
					<li class="dropdown"><a href="rooms">Rooms</a>
						<div class="dropdown-content">
							<a href="#">Standard Room</a> <a href="#">Deluxe Room</a> <a
								href="#">Suite</a> <a href="#">Executive Suite</a>
						</div></li>
					<li class="dropdown"><a href="aboutus">About Us</a>
						<div class="dropdown-content">
							<a href="#">Our Story</a> <a href="#">Our Team</a> <a href="#">Testimonials</a>
						</div></li>
					<li><a href="contactus">Contact Us</a></li>
				</ul>
			</nav>

			<div class="user-actions">
				<div class="profile-image">
					<a href="profile">
						 <img
							src="${pageContext.request.contextPath}/resources/images/system/profileIcon.png"
							alt="Profile">
						 <span class="profile-text">Profile</span>
					</a>

				</div>
			</div>
		</div>
	</header>
</body>
</html>