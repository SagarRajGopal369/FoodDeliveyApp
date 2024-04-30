<!-- cartDisplay.jsp -->

<%@page import="com.mysql.cj.jdbc.interceptors.ResultSetScannerInterceptor"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.HashMap" %>
<%@ page import="com.food.models.Cart" %>
<%@ page import="com.food.models.CartItem" %>
<%@ page import="com.food.models.User" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Home</title>
    <link rel="stylesheet" href="cart3.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <script type="text/javascript" src="quantityUpdate.js"></script>
    
</head>
<body>
    <div class="container" id="container">
        <!-- Menu -->
         <div id="menu" class="menu">
           <div class="brand-logo">
                <img src="logo.png" alt="no image">
           </div>
          <% 
           		User user=(User)session.getAttribute("user");
           		if(user!=null && "Admin".equals(user.getRole()))
           		{
           
           %>
           <div class="menu-item">
                <a href="Admin_Home.jsp"><ion-icon name="home"></ion-icon>Home</a>
                <a href="userProfile.jsp"><ion-icon name="person-sharp"></ion-icon> Profile</a>
                <a href="cart.jsp"><ion-icon name="cart"></ion-icon> Cart</a>
                <a href="checkout.jsp"><ion-icon name="wallet"></ion-icon>Checkout</a>
                <a href="#"><ion-icon name="settings-sharp"></ion-icon> Settings</a>
                <a href="#"><ion-icon name="chatbox-ellipses-sharp"></ion-icon>Contact</a>
                 <a href="YourOrdersServlet">Your Orders</a>
           </div>
           
         <%
        	} else{
		 %>
			 	 <<div class="menu-item">
	                <a href="home.jsp"><ion-icon name="home"></ion-icon>Home</a>
	                <a href="userProfile.jsp"><ion-icon name="person-sharp"></ion-icon> Profile</a>
	                <a href="cart.jsp"><ion-icon name="cart"></ion-icon> Cart</a>
	                <a href="checkout.jsp"><ion-icon name="wallet"></ion-icon>Checkout</a>
	                <a href="#"><ion-icon name="settings-sharp"></ion-icon> Settings</a>
	                <a href="#"><ion-icon name="chatbox-ellipses-sharp"></ion-icon>Contact</a>
	                 <a href="YourOrdersServlet">Your Orders</a>
           		</div>
			 	
		 <%
        	}
		%>
        </div>
        
        <!-- Content -->
        <div id="food-container">
          
            <!-- Food display -->
            <div id="food-item" class="food-item">
            
            <div class="container2">
      

        <%
            // Retrieve the user's cart
            Cart cart = (Cart) session.getAttribute("cart");

            // Display cart items and calculate total amount
            double totalAmount = 0.0;

            if (cart != null && !cart.getItems().isEmpty()) {
                HashMap<Integer, CartItem> cartItems = cart.getItems();
		%>
			<h2>Yumminess unlocked! <span style='font-size:50px;'>&#128523;</span></h2>	
		<% 		
                for (CartItem cartItem : cartItems.values()) {
                    totalAmount += (cartItem.getPrice() * cartItem.getQuantity());
        %>			  
                    <div class="card">
                        <img src="<%= cartItem.getImagePath() %>" alt="<%= cartItem.getItemName() %>">
                        
                        <div class="card-details">
                            <p><strong><%= cartItem.getItemName() %></strong></p>
                            <p> &#8377; <%= cartItem.getPrice() %></p>
                            <p>Qty: <%= cartItem.getQuantity() %></p>
                        </div>
                        
                        <form action="UpdateCartServlet" method="post">
                        
                            	<input type="hidden" name="menuId" value="<%=cartItem.getItemId() %>">
			    
							    <!-- Update the name attribute to include the itemId -->
							    <div class="quantity-input">
							        <button type="button" class="quantity-btn" onclick="updateQuantity('<%= cartItem.getItemId() %>', 'decrease')">-</button>
							        <!-- Update the name attribute here -->
							        <input type="number" name="quantity_<%= cartItem.getItemId() %>" value="<%= cartItem.getQuantity() %>" readonly class="quantity-field">
							        <button type="button" class="quantity-btn" onclick="updateQuantity('<%= cartItem.getItemId() %>', 'increase')">+</button>
							    </div>
							    
	                       	   <div class="icon1">
	                    			<button type="submit" class="update-btn"><ion-icon name="create"></ion-icon></button>
	               			   </div>
               			   
                        </form>
                        
                        <form action="RemoveFromCartServlet" method="post">
                        
                            <input type="hidden" name="menuId" value="<%=cartItem.getItemId() %>">
                           <div class="icon2">
                    		 <button type="submit" class="remove-btn"><ion-icon name="trash-sharp"></ion-icon></button>
               			   </div>
               			   
                        </form>
                        
                    </div>
        <%
                }
            } else {
        %>
                <p>Your cart is empty.... <span id="cart-emoji">&#128546;</span> </p>
        <%
            }
        %>

       <p>Total Amount: <%= String.format("%.2f", totalAmount) %></p>


        <!-- Add More and Proceed to Checkout buttons -->
        <div class="cart-buttons">
            
           <%
           		if(session.getAttribute("cart")!=null){
           %>
           <a href="OrderMenuServlet?restaurantId=<%= session.getAttribute("restaurantId")%>">
                <button type="submit" class="add-more-btn"><i class="fa fa-cart-plus"></i> Add More</button></a>
             <%
            }
            %>
            
            <%--Proceed to Checkout Button --%>
            <%
	            if(session.getAttribute("cart")!=null){
	            session.setAttribute("totalAmount", totalAmount);
            %>
     			<a href="checkout.jsp?TotalAmount= <%= String.format("%.2f", totalAmount) %>">
                <button type="submit" class="checkout-btn"><i class="fa fa-credit-card"></i> Proceed to Checkout</button>
            	</a>
            
            <%
            }
            %>
       		</div>
   		</div>
       </div>     
     </div>
   </div>
    <script type="module" src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.esm.js"></script>
<script nomodule src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.js"></script>
</body>
</html>
