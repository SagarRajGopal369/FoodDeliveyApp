<!-- Your JSP file -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>CheckOut</title>
		<link rel="stylesheet" href="checkout.css"> 
	</head>
<body>
	<div class="card">
		<div class="card-inner">
			<div class="card-face card-front">
				<h1>Just last TAP, <%= session.getAttribute("name") != null ? session.getAttribute("name") : "" %></h1>
			</div>
			<div class="card-face card-back">
				<h3>Check Out</h3>
	
				<form action="CheckOutServlet" method="post">
					<div class="address">
						<label for="address">Delivery Address </label>
						<textarea id="address" name="address" required></textarea>
					</div>
		
					<div class="payment">
						<label>Payment Method</label>
						<select name="paymentMethod">
							<option value="UPI">UPI</option>
							<option value="Cash On Delivery">Cash On Delivery</option>
							<option value="Debit Card">Debit Card</option>
							<option value="Credit Card">Credit Card</option>
						</select>
					</div>
		
					<div class="place-order-button">
					    <input type="submit" value="Place Order">
					</div>
				</form>
				
				<a href="home.jsp" class="home-button">Home</a>
			</div>
		</div>
	</div>
</body>
</html>
