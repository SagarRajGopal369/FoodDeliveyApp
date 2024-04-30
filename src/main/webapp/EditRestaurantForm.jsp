<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.food.models.Restaurant" %>
<%@ page import="com.food.dao.RestaurantDAO" %>
<%@ page import="com.food.daoImpl.RestaurantDAOImpl" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit Restaurant</title>
    <link rel="stylesheet" href="signUp.css">
</head>
<body>
  <b><span style='text-align: center; color: white;'>Admin Name: <%= session.getAttribute("name") %></span></b>
    <h2>Edit Restaurant</h2>

    <form action="UpdatedRestaurantServlet" method="post">
        <%-- Retrieve the restaurant details from the request attribute --%>
        <%
            Restaurant restaurant = (Restaurant) request.getAttribute("restaurant");
        %>

        <%-- Display input fields pre-populated with existing details --%>
        
        <label for="name">Name</label>
        <input type="text" id="name" name="name"value="<%= restaurant.getName() %>" required><br>
        
   		 <label for="address">Address</label>
        <textarea id="address" name="address" value="<%= restaurant.getAddress() %>" required></textarea><br>
   		
		  <label for="DeliveryTime">DeliveryTime</label>
        <input type="number" id="DeliveryTime" name="DeliveryTime" value="<%= restaurant.getDeliveryTime() %>" required><br>
       
	
		<label for="phone">Rating</label>
        <input type="text" id="Rating" name="Rating"  value="<%= restaurant.getRating() %>" required><br>
		
		
		 <label for="cuisineType">CuisineType</label>
        <input type="text" id="cuisineType" name="cuisineType"  value="<%= restaurant.getCuisineType() %>" required><br>
		
        
		
		 <label for="isActive">Is Active:</label>
		<select id="isActive" name="isActive" required>
		    <option value="Open" <%= "Open".equals(restaurant.getIsActive()) ? "selected" : "" %>>Open</option>
		    <option value="Close" <%= "Close".equals(restaurant.getIsActive()) ? "selected" : "" %>>Close</option>
		</select><br>

		
		  <label for="adminUserId">Admin User ID:</label>
        <input type="number" id="adminUserId" name="adminUserId" value="<%= restaurant.getAdminUserId() %>" required><br>
			
			
		  <label for="imagePath">Image Path:</label>
        <input type="text" id="imagePath" name="imagePath" value="<%= restaurant.getImagePath() %>" required><br>

		

        <input type="hidden" name="restaurantId" value="<%= restaurant.getRestaurantId() %>">
        <input type="submit" value="Submit">
    </form>
</body>
</html>