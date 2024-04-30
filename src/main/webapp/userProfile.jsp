<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.food.models.User" %>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Profile</title>
    <link rel="stylesheet" href="userProfile.css">
</head>
<body>
    <div class="user-card">
        <h2>User Profile</h2>
        <% 
            // Retrieve user object from session
            User user = (User)session.getAttribute("user");

            // Check if the user object is not null
            if (user!=null) {
        %>
        <p><b>Username:</b> <%= user.getUsername() %></p>
        <p><b>Email:</b> <%= user.getEmail() %></p>
        <p><b>Phone Number:</b> <%= user.getPhoneNo() %></p>
        <p><b>Address:</b> <%= user.getAddress() %></p>
        <p><b>Role:</b> <%= user.getRole() %></p>
        <!-- Add other user details as needed -->

        <div class="buttons">
            <a href="editProfile.jsp">
                <button class="edit-button">Edit</button>
            </a>
            <a href="home.jsp">
                <button class="home-button">Home</button>
            </a>
            
        </div>

        <% } else { %>
        <p>User details not found.</p>
        <% } %>
    </div>
</body>
</html>
