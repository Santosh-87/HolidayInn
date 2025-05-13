<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>The Holiday Inn - Login</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css">
    <link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
    
</head>
<body>
    <div class="container">
        <div class="image-section">
            <div class="image-overlay"></div>
            <div class="shape"></div>
            <div class="logo">
                <img src="${pageContext.request.contextPath}/resources/images/system/Hotel Logo.png">
                <h1>The <span>Holiday</span> Inn</h1>
            </div>
        </div>
        
        <div class="form-section">
            <div class="shape-top"></div>        
            <div class="login-header">
                <h2>Welcome Back!</h2>
                <p>Please login to access your account</p>
            </div>
            <c:if test="${not empty error}">
			    <div class="error-message">
			        ${error}
			    </div>
			</c:if>
            <form action="login" method="post">
                
                <div class="form-group">
                    <label for="username">Username</label>
                    <input type="text" id="username" name= "username" class="form-control" placeholder="Enter your username">
                </div>
                
                <div class="form-group">
                    <label for="password">Password</label>
                    <input type="password" id="password" name= "password" class="form-control" placeholder="Enter your password">
                </div>
                
                <div class="form-options">
                    <div class="remember-me">
                        <input type="checkbox" id="remember">
                        <label for="remember">Remember me</label>
                    </div>
                    <a href="#" class="forgot-password">Forgot Password?</a>
                </div>
                
                <button type="submit" class="btn-login">Login</button>
                <a href="register" class="btn-register">Register</a>
            </form>
            
            <div class="login-footer">
                <p>Or you can join with</p>
                <div class="social-login">
						<a href="#"><i class="fab fa-facebook-f"></i></a>
						<a href="#"><i class="fab fa-twitter"></i></a>
						<a href="#"><i class="fab fa-instagram"></i></a>
						<a href="#"><i class="fab fa-linkedin-in"></i></a>
                </div>
            </div>
        </div>
    </div>
</body>
</html>