<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Contact Us - Holiday Inn</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/contactus.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/header.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/footer.css">
</head>

<jsp:include page="header.jsp" />
<body>


	<div class="container">
		<div class="contact-section">
			<div class="contact-info-box">
				<h1>Contact Us</h1>
				<p>Feel free to use the form or drop us an email. Old-fashioned
					phone calls work too.</p>

				<div class="contact-item">
					<div class="icon">📞</div>
					<div class="contact-details">+977 9762874152, 01-4534465</div>
				</div>

				<div class="contact-item">
					<div class="icon">✉️</div>
					<div class="contact-details">info@holidayinn.com</div>
				</div>

				<div class="contact-item">
					<div class="icon">📍</div>
					<div class="contact-details">
						Budhanilkantha-10<br>Kathmandu, Nepal
					</div>
				</div>
			</div>

			<div class="contact-form">
				<form>
					<div class="form-row">
						<div class="form-group">
							<label for="firstName">First Name</label> <input type="text"
								id="firstName" placeholder="First">
						</div>

						<div class="form-group">
							<label for="lastName">Last Name</label> <input type="text"
								id="lastName" placeholder="Last">
						</div>
					</div>

					<div class="form-group">
						<label for="email">Email</label> <input type="email" id="email"
							placeholder="example@email.com">
					</div>

					<div class="form-group">
						<label for="phone">Phone (optional)</label> <input type="tel"
							id="phone" placeholder="xxx-xxx-xxxx">
					</div>

					<div class="form-group">
						<label for="message">Message</label>
						<textarea id="message" placeholder="Type your message ..."></textarea>
					</div>

					<button type="submit">Submit</button>
				</form>
			</div>
		</div>

		<div class="features">
			<h2 class="features-title">We’re Here for You.</h2>

			<div class="features-container">
				<div class="feature-box">
					<div class="feature-icon">📞</div>
					<h3 class="feature-title">Always Reachable</h3>
					<p>Need assistance with a booking or have a question? Our team
						is just a call or message away—24/7 support, always ready to help.</p>
				</div>

				<div class="feature-box">
					<div class="feature-icon">🤝</div>
					<h3 class="feature-title">Guest-Centered Service</h3>
					<p>Your comfort is our priority. Whether it’s feedback,
						inquiries, or special requests, we listen and respond with care.</p>
				</div>

				<div class="feature-box">
					<div class="feature-icon">📧</div>
					<h3 class="feature-title">Easy Communication</h3>
					<p>Reach out through email, phone, or our online form. We
						believe in clear, open, and friendly communication—always.</p>
				</div>
			</div>

			<a href="contactus" class="learn-more">Contact Us Now →</a>
		</div>
	</div>

</body>
<jsp:include page="footer.jsp" />
</html>