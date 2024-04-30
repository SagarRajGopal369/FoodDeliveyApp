<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.food.models.Menu" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit Menu</title>
    <link rel="stylesheet" href="editMenu.css">
</head>
<body>
    <b><span style=' color: black; margin-left: 520px; '>Admin Name: <%= session.getAttribute("name") %></span></b>
    <h2>Edit Menu</h2>
        <!-- Retrieve the Menu object from the request -->
        <% Menu menu = (Menu) request.getAttribute("menu"); %>

        <!-- Check if the menu object is not null -->
        <% if(menu != null) { %>
            <form action="UpdateMenuServlet" method="post">
                <!-- Display the current details of the menu -->
                <label for="itemName">Item Name:</label>
                <input type="text" id="itemName" name="itemName" value="<%= menu.getItemName() %>" required><br>

                <label for="description">Description:</label>
                <textarea id="description" name="description" required><%= menu.getDescription() %></textarea><br>

                <label for="price">Price:</label>
                <input type="text" id="price" name="price" value="<%= menu.getPrice() %>" required><br>

                <label for="rating">Rating:</label>
                <input type="text" id="rating" name="rating" value="<%= menu.getRating() %>" step="0.1" required><br>

                <label for="isAvailable">Is Available:</label>
                <select id="isAvailable" name="isAvailable" required>
                    <option value="Available" <%= menu.getIsAvailable().equals("true") ? "selected" : "" %>>Available</option>
                    <option value="Not Available" <%= menu.getIsAvailable().equals("false") ? "selected" : "" %>>Not Available</option>
                </select><br>

                <!-- Text input for image path -->
                <label for="image">Image Path:</label>
                <input type="text" id="image" name="image" value="<%= menu.getImagepath() %>" required><br>

                <!-- Add other fields as needed -->

                <!-- Add a hidden field to store the menuId -->
                <input type="hidden" name="menuId" value="<%= menu.getMenuId() %>">

                <!-- Submit button -->
                <button type="submit">Submit</button>
            </form>
        <% } else { %>
            <p>Error: Menu not found for editing.</p>
        <% } %>
    
</body>
</html>
