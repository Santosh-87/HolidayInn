<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Hotel Management System - Room Management</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/editroom.css">
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
					<a href="dashboard" class="nav-item"> <i>📊</i> <span>Dashboard</span>
					</a>
					<a href="editroom" class="nav-item"> <i>✏️</i> <span>Edit
							Bookings</span>
					</a>
					<a href="profile" class="nav-item"> <i>👤</i> <span>Profile</span>
					</a>
				</ul>
			</nav>
		</div>

		<!-- Main Content -->
		<div class="main-content">
			<div class="header">
				<h1 class="page-title">Room Management</h1>
				<div class="header-right">
					<form action="addRoom" method="get">
						<button type="submit" class="add-new-btn">Add New Room</button>
					</form>
				</div>
			</div>

			<!-- Room Management Section -->
			<div class="room-management">
				<div class="header-actions">
					<form action="editroom" method="get" class="search-container">
						<input type="text" name="searchQuery" placeholder="Search room...">
						<button type="submit">🔍</button>
					</form>
				</div>

				<c:if test="${searchPerformed && searchEmpty}">
					<div class="no-results">
						<p>No rooms found matching your search criteria.</p>
					</div>
				</c:if>
				
				<!-- Rooms Table -->
				<div class="table-container">
					<table class="rooms-table">
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
								<th>Actions</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="room" items="${rooms}">
								<tr>
									<td>${room.roomId}</td>
									<td>${room.roomNumber}</td>
									<td>${room.roomImage}</td>
									<td>${room.roomType}</td>
									<td>${room.roomGrade}</td>
									<td>${room.bedType}</td>
									<td>${room.roomStatus}</td>
									<td>${room.price}</td>
									<td>
										<div class="action-buttons">
											<form action="updateRoom" method="get"
												style="display: inline;">
												<input type="hidden" name="id" value="${room.roomId}" />
												<button type="submit" class="action-button edit-button">✏️
													Edit</button>
											</form>
											<form action="deleteRoom" method="post"
												style="display: inline;" onsubmit="return confirmDelete()">
												<input type="hidden" name="id" value="${room.roomId}" />
												<button type="submit" class="action-button delete-button">🗑️
													Delete</button>
											</form>
										</div>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>

	<script>
		function confirmDelete() {
			return confirm('Are you sure you want to delete this room?');
		}
	</script>
</body>
</html>