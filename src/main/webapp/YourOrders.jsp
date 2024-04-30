<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.food.models.Order" %>
<%@ page import="com.food.models.User" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Your Orders</title>
    <link rel="stylesheet" type="text/css" href="yourOrders.css">
</head>
<body>
    <div class="container">
        <div class="header">
            <div class="header-content">
                <% List<Order> userOrders = (List<Order>) request.getAttribute("userOrders");
                	// Retrieve the user object from the session
                	User user = (User) session.getAttribute("user");
                	 String userRole = user.getRole();
                %>
                
                <% if (userOrders != null && !userOrders.isEmpty() && user != null) { %>
                    
                    <%
					        if ("Customer".equals(userRole)) {
					%>
								<h2>Your Orders</h2>
					            <button class="explore-button"><a href="home.jsp">Home</a></button>
					<%
					        } else if ("Admin".equals(userRole)) {
					%>
								<h2>Your Orders</h2>
					            <button class="explore-button"><a href="Admin_Home.jsp">Home</a></button>
					<%
					        }
					%>
					

                <% } else  { %>
                    
                     <%
					        if ("Customer".equals(userRole)) {
					%>
								<h2>No orders, no worries. Feast time!</h2>
					            <button class="explore-button"><a href="home.jsp">Home</a></button>
					<%
					        } else if ("Admin".equals(userRole)) {
					%>
								<h2>No orders, no worries. Feast time!</h2>
					            <button class="explore-button"><a href="Admin_Home.jsp">Home</a></button>
					<%
					        }
					%>
                <% } %>
            </div>
        </div>

        <div class="orders-container">
            <!-- Your dynamic order items go here -->
            <% if (userOrders != null && !userOrders.isEmpty()) { %>
                <% for (Order order : userOrders) { %>
                    <div class="order-card">
                        <!-- Display order details here -->
                        <p><strong>Order ID:</strong> <%= order.getOrderId() %></p>
                        <p><strong>Order Date:</strong> <%= order.getOrderDate() %></p>
                        <p><strong>Total Amount:</strong> <%= order.getTotalAmount() %></p>
                        <p><strong>Status:</strong> <%= order.getStatus() %></p>
                        <p><strong>Payment Method:</strong> <%= order.getPaymentMethod() %></p>
                        <!-- Add more details as needed -->
                    </div>
                <% } %>
            <% } %>
        </div>
    </div>
</body>
</html>
