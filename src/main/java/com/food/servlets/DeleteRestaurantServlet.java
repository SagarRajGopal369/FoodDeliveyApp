package com.food.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.food.dao.RestaurantDAO;
import com.food.daoImpl.RestaurantDAOImpl;

@WebServlet("/DeleteRestaurantServlet")
public class DeleteRestaurantServlet extends HttpServlet {
    private RestaurantDAO restaurantDAO;

    @Override
    public void init() throws ServletException {
        restaurantDAO = new RestaurantDAOImpl();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Extract restaurant ID from the request parameter
            int restaurantId = Integer.parseInt(request.getParameter("restaurantId"));
            
            restaurantDAO.deleteMenu(restaurantId);
            // Delete the restaurant based on ID
            restaurantDAO.deleteRestaurant(restaurantId);
            

            // Redirect back to Admin_Home.jsp after successful deletion
            response.sendRedirect("Admin_Home.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            // Handle exceptions or redirect to an error page if needed
        }
    }
}