<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.food.models.Menu" %>
<%@ page import="com.food.models.User" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Menu</title>
    <link rel="stylesheet" href="adminMenu.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body>
   <div class="container" id="container">
        <!-- Menu -->
        <div id="menu" class="menu">
           <div class="brand-logo">
                <img src="logo.png" alt="no image">
           </div>
           		<div class="menu-item">
                <a href="Admin_Home.jsp"><ion-icon name="home"></ion-icon>Home</a>
                <a href="userProfile.jsp"><ion-icon name="person-sharp"></ion-icon> Profile</a>
                <a href="cart.jsp"><ion-icon name="cart"></ion-icon> Cart</a>
                <a href="checkout.jsp"><ion-icon name="wallet"></ion-icon>Checkout</a>
                <a href="#"><ion-icon name="settings-sharp"></ion-icon> Settings</a>
                <a href="#"><ion-icon name="chatbox-ellipses-sharp"></ion-icon>Contact</a>
                 <a href="YourOrdersServlet">Your Orders</a>
          	 </div>
         </div>
        
        <!-- Content -->
        <div id="food-container">
          
            <!-- Food display -->
           <div id="food-item" class="food-item">
            	 <div class="homeImg">
	                    <img src="IMAGES/pizza1image.jpg" alt="image not found" style="width: 1150px; height: 250px;border-radius: 30px;" >
	               </div>
               <div id="biriyani" class="biriyani">
                    
					<div style="padding: 30px;">
                    <!-- Java code for dynamic content generation -->
                    <%
                    List<Menu> menus = (List<Menu>) request.getAttribute("menus");
                    if(menus!=null &&  !menus.isEmpty()){
                   	%>
                   		<p id="Caregory-name">TAP Your Favourite Menu</p>
               	   <%	
                    for (Menu menu : menus) {
                    %>
                    <div id="item-card">
                        <img src="<%= menu.getImagepath() %>" alt="">
                        <div id="restaurant-content">
                            <h3><b><p id="name"> <%= menu.getItemName() %></p></b></h3>
                            
                            
                            <b><p>&#8377;<%= menu.getPrice() %></p></b>
                            <p id="description"> <%= menu.getDescription() %></p>
                            
                              <!-- Edit button -->
      			  			<a href="EditMenuServlet?menuId=<%= menu.getMenuId() %>">
      			  			  <button class="edit-btn">Edit</button></a>
                            <!-- Add to Cart Button-->
							<form action="AddToCartServlet" method="post">
							    <input type="hidden" name="menuId" value="<%= menu.getMenuId() %>">
							    <input type="hidden" name="quantity" value="1"> <!-- You can have a quantity input if needed -->
							    <button type="submit" class="add-button">Add to Cart</button>
							</form>
							
							 <!-- Delete button -->
        					<a href="DeleteMenuServlet?menuId=<%= menu.getMenuId() %>" class="delete-button">
								<button class="delete-btn">Delete</button>
							</a>
								
								
								
                        </div>
                    </div>
                    <% } // end for loop
               	  		 } else {
                     %>
                     	<p id="Caregory-name">No menu today, but stay tuned! Exciting flavors on the way. Come back soon! </p>
                     	<span style='font-size:20px;'>&#128522;</span>
                     	<span style='font-size:30px;'>&#128522;</span>
                     	<span style='font-size:50px;'>&#128522;</span>
                     <%} %>		
                    <!-- End Java code -->
                    </div>
                </div>
            </div>
        </div>
        
    </div>
      <script type="module" src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.esm.js"></script>
<script nomodule src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.js"></script>
</body>
</html>
