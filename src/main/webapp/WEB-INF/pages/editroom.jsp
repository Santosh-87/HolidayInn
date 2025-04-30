<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
      <img src="${pageContext.request.contextPath}/resources/images/system/Hotel Logo.png" alt="The Holiday Inn Logo">
      <h1>The <span>Holiday</span> Inn</h1>
    </div>
    <nav>
      <ul class="nav-links">
        <a href="dashboard" class="nav-item"> <i>📊</i> <span>Dashboard</span> </a>
        <a href="editroom" class="nav-item"> <i>✏️</i> <span>Edit Bookings</span> </a>
        <a href="profile" class="nav-item"> <i>👤</i> <span>Profile</span> </a>
      </ul>
    </nav>
  </div>

  <!-- Main Content -->
  <div class="main-content">
    <div class="header">
      <h1 class="page-title">Room Management</h1>
      <div class="header-right">
        <form action="addRoom.jsp" method="get">
          <button type="submit" class="add-new-btn">Add New Room</button>
        </form>
      </div>
    </div>

    <!-- Room Management Section -->
    <div class="room-management">
      <div class="header-actions">
        <form action="searchRooms" method="get" class="search-container">
          <input type="text" name="searchQuery" placeholder="Search room...">
          <button type="submit">🔍</button>
        </form>
      </div>

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
            <tr>
              <td>1</td>
              <td>101</td>
              <td>economy_room.jpg</td>
              <td>Single</td>
              <td>Economy Room</td>
              <td>Single Bed</td>
              <td>Available</td>
              <td>10000</td>
              <td>
                <div class="action-buttons">
                  <button class="action-button edit-button">✏️ Edit</button>
                  <button class="action-button delete-button">🗑️ Delete</button>
                </div>
              </td>
            </tr>
            <tr>
              <td>2</td>
              <td>201</td>
              <td>standard_room.jpg</td>
              <td>Double</td>
              <td>Standard Room</td>
              <td>Double Bed</td>
              <td>Available</td>
              <td>15000</td>
              <td>
                <div class="action-buttons">
                  <button class="action-button edit-button">✏️ Edit</button>
                  <button class="action-button delete-button">🗑️ Delete</button>
                </div>
              </td>
            </tr>
            <tr>
              <td>3</td>
              <td>301</td>
              <td>heritage_deluxe_room.jpg</td>
              <td>Deluxe</td>
              <td>Durbar Deluxe Room</td>
              <td>King Bed</td>
              <td>Available</td>
              <td>19000</td>
              <td>
                <div class="action-buttons">
                  <button class="action-button edit-button">✏️ Edit</button>
                  <button class="action-button delete-button">🗑️ Delete</button>
                </div>
              </td>
            </tr>
            <tr>
              <td>4</td>
              <td>401</td>
              <td>business_suite.jpg</td>
              <td>Suite</td>
              <td>Business Suite</td>
              <td>Queen Bed</td>
              <td>Occupied</td>
              <td>24500</td>
              <td>
                <div class="action-buttons">
                  <button class="action-button edit-button">✏️ Edit</button>
                  <button class="action-button delete-button">🗑️ Delete</button>
                </div>
              </td>
            </tr>
            <tr>
              <td>5</td>
              <td>501</td>
              <td>presidential_suite.jpg</td>
              <td>Executive</td>
              <td>Presidential Suite</td>
              <td>Super King Bed</td>
              <td>Available</td>
              <td>50000</td>
              <td>
                <div class="action-buttons">
                  <button class="action-button edit-button">✏️ Edit</button>
                  <button class="action-button delete-button">🗑️ Delete</button>
                </div>
              </td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
  </div>
</div>
</body>
</html>