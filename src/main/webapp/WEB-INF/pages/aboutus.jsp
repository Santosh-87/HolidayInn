<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>About Us - The Holiday Inn</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/aboutus.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/footer.css">
    <style type="text/css">
	    .about-hero {
			background: linear-gradient(rgba(0, 0, 0, 0.6), rgba(0, 0, 0, 0.6)),
				url('${pageContext.request.contextPath}/resources/images/system/aboutUsBackground.webp') no-repeat center center/cover;
			height: 50vh;
			display: flex;
			align-items: center;
			justify-content: center;
			text-align: center;
			color: white;
		}
    </style>
</head>
<body>
	<jsp:include page="header.jsp" /> 
	
    <!-- Hero Section -->
    <section class="about-hero">
        <div class="about-hero-content">
            <h1>About Us</h1>
            <p>Learn more about The Holiday Inn and our commitment to providing exceptional hospitality experiences.</p>
        </div>
    </section>
    
    <!-- Our Story Section -->
    <section class="about-section">
        <div class="container">
            <div class="our-story">
                <div class="story-text">
                    <h3>Our Story</h3>
                    <p style="text-align: justify;">Since 1995, The Holiday Inn has been a beacon of luxury and comfort in the hospitality industry. What began as a small family-owned hotel has grown into a renowned establishment known for its exceptional service and unparalleled guest experiences.</p>
                    <p style="text-align: justify;">Founded by the Thompson family with a vision to create a home away from home for travelers, our hotel has evolved while staying true to our core values of warmth, quality, and attention to detail. With each passing year, we've refined our services and expanded our offerings to meet the changing needs of our guests.</p>
                    <p style="text-align: justify;">Today, The Holiday Inn stands as a symbol of excellence in hospitality, welcoming guests from around the world and creating memorable experiences that last a lifetime.</p>
                </div>
                <div class="story-image">
                    <img src="${pageContext.request.contextPath}/resources/images/system/aboutUsHotelPhoto.jpg" alt="The Holiday Inn History">
                </div>
            </div>
        </div>
    </section>
    
    <!-- Mission & Values Section -->
    <section class="about-section">
        <div class="container">
            <div class="section-heading">
                <h2>Our Mission & Values</h2>
                <p>Guided by our commitment to excellence, we strive to create exceptional experiences that exceed expectations.</p>
            </div>
            
            <div class="mission-values">
                <div class="value-card">
                    <div class="value-icon icon-quality"></div>
                    <h3>Excellence</h3>
                    <p>We are committed to maintaining the highest standards in all aspects of our service, from accommodations to dining and beyond.</p>
                </div>
                
                <div class="value-card">
                    <div class="value-icon icon-service"></div>
                    <h3>Personalized Service</h3>
                    <p>We believe in treating each guest as a unique individual, tailoring our services to meet their specific needs and preferences.</p>
                </div>
                
                <div class="value-card">
                    <div class="value-icon icon-sustainability"></div>
                    <h3>Sustainability</h3>
                    <p>We are committed to responsible practices that minimize our environmental impact while enhancing the guest experience.</p>
                </div>
            </div>
        </div>
    </section>
    
    <!-- Team Section -->
    <section class="about-section">
        <div class="container">
            <div class="section-heading">
                <h2>Meet Our Team</h2>
                <p>The dedicated professionals behind our exceptional service.</p>
            </div>
            
            <div class="our-team-grid">
                <!-- Team Member 1 -->
                <div class="team-member">
                    <div class="member-img">
                        <img src="${pageContext.request.contextPath}/resources/images/system/Santosh Lama.jpg" alt="Santosh Lama">
                    </div>
                    <h3>Santosh Lama</h3>
                    <p>Chief Executive Officer</p>
                    <div class="social-links">
                        <a href="#" class="social-link"><span class="icon-facebook"></span></a>
                        <a href="#" class="social-link"><span class="icon-twitter"></span></a>
                        <a href="#" class="social-link"><span class="icon-linkedin"></span></a>
                    </div>
                </div>
                
                <!-- Team Member 2 -->
                <div class="team-member">
                    <div class="member-img">
                        <img src="${pageContext.request.contextPath}/resources/images/system/Hansi Flick.jpg" alt="Hansi Flick">
                    </div>
                    <h3>Hansi Flick</h3>
                    <p>General Manager</p>
                    <div class="social-links">
                        <a href="#" class="social-link"><span class="icon-facebook"></span></a>
                        <a href="#" class="social-link"><span class="icon-twitter"></span></a>
                        <a href="#" class="social-link"><span class="icon-linkedin"></span></a>
                    </div>
                </div>
                
                <!-- Team Member 3 -->
                <div class="team-member">
                    <div class="member-img">
                        <img src="${pageContext.request.contextPath}/resources/images/system/tom cruise.jpg" alt="Tom Cruise">
                    </div>
                    <h3>Tom Cruise</h3>
                    <p>Spa & Wellness Director</p>
                    <div class="social-links">
                        <a href="#" class="social-link"><span class="icon-facebook"></span></a>
                        <a href="#" class="social-link"><span class="icon-twitter"></span></a>
                        <a href="#" class="social-link"><span class="icon-linkedin"></span></a>
                    </div>
                </div>
                
                <!-- Team Member 4 -->
                <div class="team-member">
                    <div class="member-img">
                        <img src="${pageContext.request.contextPath}/resources/images/system/sirish dalli.jpeg" alt="Sirish Dalli">
                    </div>
                    <h3>Sirish Dalli</h3>
                    <p>Head of Guest Services</p>
                    <div class="social-links">
                        <a href="#" class="social-link"><span class="icon-facebook"></span></a>
                        <a href="#" class="social-link"><span class="icon-twitter"></span></a>
                        <a href="#" class="social-link"><span class="icon-linkedin"></span></a>
                    </div>
                </div>
            </div>
        </div>
    </section>
    

    
    <!-- Stats Section -->
    <section class="about-section">
        <div class="container">
            <div class="stats-container">
                <div class="stat">
                    <div class="stat-number">250+</div>
                    <p>Luxury Rooms</p>
                </div>
                
                <div class="stat">
                    <div class="stat-number">30+</div>
                    <p>Years of Excellence</p>
                </div>
                
                <div class="stat">
                    <div class="stat-number">15k+</div>
                    <p>Happy Guests Yearly</p>
                </div>
                
                <div class="stat">
                    <div class="stat-number">100+</div>
                    <p>Team Members</p>
                </div>
            </div>
        </div>
    </section>
    
	<jsp:include page="footer.jsp" />
</body>
</html>