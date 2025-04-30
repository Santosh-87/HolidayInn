<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
			<div class="header">
				<h1 class="page-title">Dashboard</h1>
				<div class="header-right">
					<button class="add-new-btn">Add New</button>
				</div>
			</div>

			<!-- Stats Section -->
			<div class="stats-container">
				<div class="stat-card">
					<div class="stat-info">
						<h3>152</h3>
						<p>Total Bookings</p>
					</div>
					<div class="stat-icon">🗓️</div>
				</div>

				<div class="stat-card">
					<div class="stat-info">
						<h3>87</h3>
						<p>Current Guests</p>
					</div>
					<div class="stat-icon">👥</div>
				</div>

				<div class="stat-card">
					<div class="stat-info">
						<h3>35</h3>
						<p>Available Rooms</p>
					</div>
					<div class="stat-icon">🏨</div>
				</div>

				<div class="stat-card">
					<div class="stat-info">
						<h3>$24,500</h3>
						<p>Monthly Revenue</p>
					</div>
					<div class="stat-icon">💰</div>
				</div>

				<div class="stat-card">
					<div class="stat-info">
						<h3>89%</h3>
						<p>Occupancy Rate</p>
					</div>
					<div class="stat-icon">📈</div>
				</div>
			</div>

			<!-- Tables Section - Only Recent Bookings -->
			<div class="tables-container">
				<div class="table-section">
					<div class="table-header">
						<h2 class="table-title">Room Details</h2>
						<button class="view-all">View All</button>
					</div>
					<table>
						<thead>
							<tr>
								<th>Room ID</th>
								<th>Room Number</th>
								<th>Room Image</th>
								<th>Room Type</th>
								<th>Room Grade</th>
								<th>Bed Type</th>
								<th>Room Status</th>
								<th>Price</th>
							</tr>
						</thead>
						<tbody>
							<!-- Table content will be populated later -->
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</body>
</html>