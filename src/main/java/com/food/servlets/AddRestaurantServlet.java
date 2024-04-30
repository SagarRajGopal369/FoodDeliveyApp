package com.food.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.food.daoImpl.RestaurantDAOImpl;
import com.food.models.Restaurant;

@WebServlet("/AddRestaurantServlet")
public class AddRestaurantServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            				throws ServletException, IOException {
        // Retrieve form data
        String name = request.getParameter("username");
        String address = request.getParameter("address");
        int deliveryTime = Integer.parseInt(request.getParameter("DeliveryTime"));
        double rating = Double.parseDouble(request.getParameter("Rating"));
        String cuisineType = request.getParameter("CuisineType");
        String isActive = request.getParameter("IsActive");
        int adminUserId = Integer.parseInt(request.getParameter("AdminUserID"));
        String imagePath = request.getParameter("ImagePath");

        // Create a Restaurant object
        Restaurant restaurant = new Restaurant();
        restaurant.setName(name);
        restaurant.setAddress(address);
        restaurant.setDeliveryTime(deliveryTime);
        restaurant.setRating(rating);
        restaurant.setCuisineType(cuisineType);
        restaurant.setIsActive(isActive);
        restaurant.setAdminUserId(adminUserId);
        restaurant.setImagePath(imagePath);

        // Use your RestaurantDAO to add the restaurant to the database
        RestaurantDAOImpl restaurantDAOImpl = new RestaurantDAOImpl();
        restaurantDAOImpl.addRestaurant(restaurant);

        // Redirect back to the home page or wherever you want after adding the restaurant
        response.sendRedirect("Admin_Home.jsp");
    }
}
