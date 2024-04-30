<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.food.models.Restaurant" %>
<%@ page import="com.food.dao.RestaurantDAO" %>
<%@ page import="com.food.daoImpl.RestaurantDAOImpl" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Home</title>
    <link rel="stylesheet" href="admin_Home.css"> <!-- Link the custom styles -->
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
            <!-- Header -->
            <div id="header" class="header">
           		  <h2>Hii <%= session.getAttribute("name") != null ? session.getAttribute("name") : "" %><br>welcome to Tap Foods</h2>
                <div class="util">
                   <a href="AddRestaurantForm.jsp"> <i class=""> Add Restaurant</i></a>
                    <a href="addMenu.jsp"><i class="">Add Menu</i></a>
                    <a href="SignInPage.jsp"><i class="fa-solid fa-right-to-bracket"> Login</i></a>
                    <a href="LogOutServlet"><i class="fa-solid fa-right-from-bracket"> Logout</i></a>
                </div>
            </div> 
            <!-- Food display -->
            <div id="food-item" class="food-item">
            
	             <div class="homeImg">
	                    <img src="IMAGES/pizza1image.jpg" alt="image not found" style="width: 1150px; height: 250px;border-radius: 30px;" >
	                </div>
                <div id="biriyani" class="biriyani">
                    <p id="Caregory-name" class="category-name">Restaurants</p>

                    <!-- Java code for dynamic content generation -->
                    <%
                        RestaurantDAO restaurantDAO = new RestaurantDAOImpl();
                        List<Restaurant> restaurants = restaurantDAO.getAllRestaurants();

                        for (Restaurant restaurant : restaurants) {
                    %>
                     <div class="restaurant-item">
                        <img src="<%= restaurant.getImagePath() %>" alt="" class="restaurant-image">
                        <div class="restaurant-item-content">
                            <h3><b><p class="name"> <%= restaurant.getName() %></p></b></h3>
                            <i class="fa fa-star" id="rating"></i>  <b><%= restaurant.getRating() %></b>
                            <b><span style="padding-left: 10px;"><span style='font-size:15px;'>🕒<span><%= restaurant.getDeliveryTime() %>mins</span></b>
                            <p class="description"> <%= restaurant.getIsActive() %>,   <%= restaurant.getCuisineType() %></p>
                            <p class="description"> <%= restaurant.getAddress() %></p>

                            <!--  Edit  button  -->
                            <a href="EditRestaurantServlet?restaurantId=<%= restaurant.getRestaurantId() %>">
                                <button class="edit-btn">Edit</button>
                            </a>
                            
                             <!-- Update the Order Now button with a link to the servlet -->
                            <a href="OrderMenuServlet?restaurantId=<%= restaurant.getRestaurantId() %>">
                                <button class="order-button">View Menu</button>
                            </a>
                            
                            <!--  Delete button  -->
                            <a href="DeleteRestaurantServlet?restaurantId=<%= restaurant.getRestaurantId() %>">
							    <button class="delete-btn">Delete</button>
							</a>


                           
                        </div>
                    </div>
                    <% } // end for loop %>
                    <!-- End Java code -->
                </div>
            </div>
        </div>
    </div>
    <script type="module" src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.esm.js"></script>
    <script nomodule src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.js"></script>
</body>
</html>
