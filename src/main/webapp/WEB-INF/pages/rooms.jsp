<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Hotel Rooms - The Holiday Inn</title>
<link rel="stylesheet"
    href="${pageContext.request.contextPath}/css/rooms.css">
</head>
<body>
    <jsp:include page="header.jsp" />
    <!-- Main Content -->
    <div class="rooms-hero">
        <div class="container">
            <h1>Luxurious Accommodations</h1>
            <p>Experience comfort and elegance with our thoughtfully designed
                rooms and suites.</p>
        </div>

        <!-- Search Bar with Room Type Filter -->
        <div class="search-container">
            <form class="search-form" action="${pageContext.request.contextPath}/rooms" method="get">
                <div class="search-row">
                    <select class="filter-select" name="roomGrade">
                        <option value="">All Room Types</option>
                        <option value="Economy Room" ${param.roomGrade == 'Economy Room' ? 'selected' : ''}>Economy Room</option>
                        <option value="Standard Room" ${param.roomGrade == 'Standard Room' ? 'selected' : ''}>Standard Room</option>
                        <option value="Durbar Deluxe Room" ${param.roomGrade == 'Durbar Deluxe Room' ? 'selected' : ''}>Durbar Deluxe Room</option>
                        <option value="Business Suite" ${param.roomGrade == 'Business Suite' ? 'selected' : ''}>Business Suite</option>
                        <option value="Presidential Suite" ${param.roomGrade == 'Presidential Suite' ? 'selected' : ''}>Presidential Suite</option>
                    </select>
                    <span class="filter-label">Sort by:</span>
                    <select class="filter-select" name="priceSort">
                        <option value="">Default Order</option>
                        <option value="price-asc" ${param.priceSort == 'price-asc' ? 'selected' : ''}>Price: Low to High</option>
                        <option value="price-desc" ${param.priceSort == 'price-desc' ? 'selected' : ''}>Price: High to Low</option>
                    </select>
                    <button type="submit" class="search-btn">Search</button>
                    <c:if test="${not empty param.roomGrade or not empty param.priceSort}">
                        <a href="${pageContext.request.contextPath}/rooms" class="reset-btn">Reset</a>
                    </c:if>
                </div>
            </form>
        </div>
    </div>

    <div class="container">
        <c:forEach items="${rooms}" var="room" varStatus="loop">
            <div class="room-card ${loop.index % 2 == 1 ? 'reverse' : ''}">
                <div class="room-image">
                    <img src="${pageContext.request.contextPath}/resources/images/room_images/${room.roomImage}" alt="${room.roomGrade}">
                </div>
                <div class="room-details">
                    <h2 class="room-title">${room.roomGrade}</h2>
                    <p class="room-description">${room.roomType}</p>
                    <div class="room-features">
                        <span class="feature">${room.bedType}</span>
                        <span class="feature">Free Wi-Fi</span>
                        <span class="feature">Air Conditioning</span>
                        <c:if test="${room.price > 150}">
                            <span class="feature">Smart TV</span>
                        </c:if>
                        <c:if test="${room.price > 200}">
                            <span class="feature">Sitting Area</span>
                        </c:if>
                    </div>
                    <p class="room-price">From Rs ${room.price} per night</p>
                    <div class="room-actions">
                        <a href="#details-${room.roomNumber}" class="btn">Book Now</a>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
    <jsp:include page="footer.jsp" />
</body>
</html>