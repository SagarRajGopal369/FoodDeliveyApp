<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
     <link rel="stylesheet" href="signUp.css">
    <title>Restaurant</title>
</head>
<body>
	<b><span style='text-align: center; color: white;'>Admin Name: <%= session.getAttribute("name") %></span></b>
    <h2>Add Restaurant</h2>

    <form action="AddRestaurantServlet" method="post">
        <label for="username">Name</label>
        <input type="text" id="username" name="username" required><br>
        
         <label for="address">Address</label>
        <textarea id="address" name="address" required></textarea><br>

        <label for="DeliveryTime">DeliveryTime</label>
        <input type="number" id="DeliveryTime" name="DeliveryTime" required><br>
        
        <label for="phone">Rating</label>
        <input type="text" id="Rating" name="Rating" required><br>

        <label for="CuisineType">CuisineType</label>
        <input type="text" id="CuisineType" name="CuisineType" required><br>

        <label for="IsActive">IsActive</label>
        <select id="IsActive" name="IsActive">
            <option value="Open">Open</option>
            <option value="Close">Close</option>
        </select><br>
        
        
       <label for="AdminUserID">AdminUserID</label>
        <input type="number" id="AdminUserID" name="AdminUserID" required><br>
        
      
        <label for="ImagePath">ImagePath</label>
        <textarea id="ImagePath" name="ImagePath" required></textarea><br>

        <input type="submit" value="Add Restaurant">
        
     	
    </form>
     

</body>
</html>
