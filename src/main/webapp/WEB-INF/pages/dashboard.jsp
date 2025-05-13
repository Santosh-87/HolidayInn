<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Hotel Management System - Admin Dashboard</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/dashboard.css">
</head>
<body>
	<div class="container">
		<!-- Sidebar -->
		<div class="sidebar">
			<div class="logo">
				<img
					src="${pageContext.request.contextPath}/resources/images/system/Hotel Logo.png"
					alt="The Holiday Inn Logo">
				<h1>
					The <span>Holiday</span> Inn
				</h1>
			</div>
			<nav>
				<ul class="nav-links">
					<a href="dashboard" class="nav-item active"> <i>📊</i> <span>Dashboard</span>
					</a>
					<a href="editroom" class="nav-item"> <i>✏️</i> <span>Edit
							Room Details</span>
					</a>
					<a href="profile" class="nav-item"> <i>👤</i> <span>Profile</span>
					</a>
				</ul>
			</nav>
		</div>

		<!-- Main Content -->
		<div class="main-content">
			<!-- Stats Section -->
			<div class="stats-container">
				<!-- <div class="stat-card">
					<div class="stat-info">
						<h3>152</h3>
						<p>Total Bookings</p>
					</div>
					<div class="stat-icon">🗓️</div>
				</div>-->

				<div class="stat-card">
					<div class="stat-info">
						<h3>${busiestDay.day_of_week}</h3>
						<p>Busiest Check-In Day (${busiestDay.booking_count})</p>
					</div>
					<div class="stat-icon">📅</div>
				</div>

				<div class="stat-card">
					<div class="stat-info">
						<h3>${topRatedRoom}</h3>
						<p>Top Rated Room</p>
					</div>
					<div class="stat-icon">🏨</div>
				</div>

				<div class="stat-card">
					<div class="stat-info">
						<h3>${avgStayDuration} days</h3>
						<p>Average Customer Stay</p>
					</div>
					<div class="stat-icon">👥</div>
				</div>

				<div class="stat-card">
					<div class="stat-info">
						<h3>Rs ${currentMonthRevenue}</h3>
						<p>Revenue this month</p>
					</div>
					<div class="stat-icon">💰</div>
				</div>

				<div class="stat-card">
					<div class="stat-info">
						<h3>${mostExpensiveRoom.roomGrade}</h3>
						<p>Most expensive room</p>
					</div>
					<div class="stat-icon">📈</div>
				</div>
			</div>

			<!-- Tables Section - Only Recent Bookings -->
			<div class="tables-container">
				<div class="table-section">
					<div class="table-header">
						<h2 class="table-title">Recent Bookings</h2>
					</div>
					<table>
						<thead>
							<tr>
								<th>Booking ID</th>
								<th>Customer ID</th>
								<th>Customer Name</th>
								<th>Room Number</th>
								<th>Check-In Date</th>
								<th>Check-Out Date</th>
								<th>Total Price</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="booking" items="${recentBookings}">
								<tr>
									<td>${booking.bookingId}</td>
									<td>${booking.userId}</td>
									<td>${booking.fullName}</td>
									<td>${booking.roomNumber}</td>
									<td>${booking.checkInDate}</td>
									<td>${booking.checkOutDate}</td>
									<td>${booking.totalPrice}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</body>
</html>