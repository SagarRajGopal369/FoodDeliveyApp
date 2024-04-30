<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.food.models.User" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit Profile</title>
    <link rel="stylesheet" href="editProfile.css">
</head>
<body>
    <div class="edit-form-container">
        <h2>Edit Profile</h2>
        <form action="UpdateProfileServlet" method="post">
            <% 
                // Retrieve user object from session
                User user = (User)session.getAttribute("user");

                // Check if the user object is not null
                if (user != null) {
            %>
            <!-- Add a hidden input field to send the userId -->
            <input type="hidden" name="userId" value="<%= user.getUserId() %>">

            <label for="username">Username:</label>
            <input type="text" id="username" name="username" value="<%= user.getUsername() %>" required>

            <label for="password">Password:</label>
            <input type="password" id="password" name="password" value="<%= user.getPassword() %>" required>

            <label for="email">Email:</label>
            <input type="email" id="email" name="email" value="<%= user.getEmail() %>" required>

            <label for="phoneNo">Phone Number:</label>
            <input type="text" id="phoneNo" name="phoneNo" value="<%= user.getPhoneNo() %>" required>

            <label for="address">Address:</label>
            <input type="text" id="address" name="address" value="<%= user.getAddress() %>" required>
			
			
         	<label for="role">Role:</label>
			<select id="role" name="role" required>
			    <option value="Customer" <%= user.getRole().equals("Customer") ? "selected" : "" %>>Customer</option>
			    <option value="Admin" <%= user.getRole().equals("Admin") ? "selected" : "" %>>Admin</option>
			    <option value="DeliveryAgent" <%= user.getRole().equals("DeliveryAgent") ? "selected" : "" %>>DeliveryAgent</option>
			</select>


            <input type="submit" value="Submit">
            <% } else { %>
            <p>User details not found.</p>
            <% } %>
        </form>
    </div>
</body>
</html>
