<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>The Holiday Inn - Luxury Hotel & Resort</title>
	<link rel="stylesheet" type="text/css"
		href="${pageContext.request.contextPath}/css/home.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/footer.css">
	<style type="text/css">
	.hero {
		background: linear-gradient(rgba(0, 0, 0, 0.5), rgba(0, 0, 0, 0.5)),
			url('${pageContext.request.contextPath}/resources/images/system/homeBackground.jpg')
			no-repeat center center/cover;
		height: 80vh;
		display: flex;
		align-items: center;
		justify-content: center;
		text-align: center;
		color: white;
		padding: 2rem;
	}
	</style>
</head>
<body>
	<jsp:include page="header.jsp" />
	<!-- Hero Section -->
	<section class="hero">
		<div class="hero-content">
			<h2>Experience Luxury & Comfort</h2>
			<p>Welcome to The Holiday Inn, where exceptional service meets
				luxurious accommodations. Your perfect getaway awaits.</p>
			<a href="#" class="hero-btn">Book Now</a>
		</div>
	</section>

	<!-- Services Section -->
	<section class="services">
		<div class="container">
			<div class="section-title">
				<h3>Our Services</h3>
				<h2>What We Offer</h2>
			</div>

			<div class="services-grid">
				<div class="service-card">
					<div class="service-icon icon-accommodation"></div>
					<h3>Luxurious Accommodation</h3>
					<p>Experience comfort in our elegantly designed rooms and
						suites with modern amenities and stunning views.</p>
				</div>

				<div class="service-card">
					<div class="service-icon icon-spa"></div>
					<h3>Spa & Wellness</h3>
					<p>Rejuvenate your mind and body with our premium spa
						treatments, sauna, and wellness packages.</p>
				</div>

				<div class="service-card">
					<div class="service-icon icon-dining"></div>
					<h3>Fine Dining</h3>
					<p>Indulge in exquisite culinary experiences at our restaurants
						serving international and local cuisines.</p>
				</div>

				<div class="service-card">
					<div class="service-icon icon-events"></div>
					<h3>Events & Meetings</h3>
					<p>Host memorable events in our versatile venues equipped with
						state-of-the-art facilities.</p>
				</div>
			</div>
		</div>
	</section>


	<!-- Call to Action -->
	<section class="cta">
		<div class="container">
			<h2>Special Offer for New Guests</h2>
			<p>Book your stay now and get 15% off on your first visit. Enjoy
				complimentary breakfast and spa access with every booking made this
				month.</p>
			<a href="#" class="cta-btn">Book Now</a>
		</div>
	</section>
	<jsp:include page="footer.jsp" />
</body>
</html>