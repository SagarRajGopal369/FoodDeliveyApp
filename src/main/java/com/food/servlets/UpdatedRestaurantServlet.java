package com.food.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.food.dao.RestaurantDAO;
import com.food.daoImpl.RestaurantDAOImpl;
import com.food.models.Restaurant;
import com.food.models.User;

@WebServlet("/UpdatedRestaurantServlet")
public class UpdatedRestaurantServlet extends HttpServlet {
	
	private RestaurantDAOImpl restaurantDAO;
	
	@Override
	public void init() throws ServletException {
		restaurantDAO = new RestaurantDAOImpl();
	}
	
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Retrieve updated restaurant details from the form parameters
        	int restaurantId = Integer.parseInt(request.getParameter("restaurantId"));
            String name = request.getParameter("name");
            String address = request.getParameter("address");
            int deliveryTime = Integer.parseInt(request.getParameter("DeliveryTime"));
            double rating = Double.parseDouble(request.getParameter("Rating"));
            String cuisineType = request.getParameter("cuisineType");
            String isActive = request.getParameter("isActive");
            int adminUserId = Integer.parseInt(request.getParameter("adminUserId"));
            String imagePath = request.getParameter("imagePath");

            
            HttpSession session = request.getSession();
            
            // Create a new Restaurant object with the updated details
            Restaurant updatedRestaurant = new Restaurant(restaurantId, name, address, rating, cuisineType, isActive,
                    deliveryTime, adminUserId, imagePath);

            // Update the restaurant in the database
            restaurantDAO.updateRestaurant(updatedRestaurant);

           
            // Redirect back to Admin_Home.jsp
            response.sendRedirect("Admin_Home.jsp");
        } catch (Exception e) {
            e.printStackTrace(); 
            
            // You might want to redirect to an error page or handle errors in another way
        }
    }
}
