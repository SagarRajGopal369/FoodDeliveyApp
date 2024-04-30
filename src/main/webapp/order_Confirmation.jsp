<%@page import="com.food.models.Order"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Order Confirmation</title>
		<link rel="stylesheet" href="order-confirmation.css">
	</head>
<body>
	<h1> Order Confirmation</h1>
	<%
	 Order order=(Order) session.getAttribute("order");
	if(order!=null){
	%>
	
	<div class="order-details">
	
	<p> Thank you for your order!</p>
	
	<p>Order Id:<%=order.getOrderId()%></p>
	
	<p>Total Amount: <%= String.format("%.2f", order.getTotalAmount()) %></p>
	
	<p>Status:<%=order.getStatus()%></p>
	
	<p>Payment Method:<%=order.getPaymentMethod()%></p>
	
	<a href="home.jsp" class="home-button">Home</a>
	
	<%} %>
	</div>
</body>
</html>