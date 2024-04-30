package com.food.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.food.daoImpl.RestaurantDAOImpl;
import com.food.models.Restaurant;

@WebServlet("/EditRestaurantServlet")
public class EditRestaurantServlet extends HttpServlet {
	
	 private static final long serialVersionUID = 1L;
	 
	 private RestaurantDAOImpl restDAO;
	 
	 @Override
	public void init() throws ServletException {
		 restDAO = new RestaurantDAOImpl();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		   int restaurantId = Integer.parseInt(req.getParameter("restaurantId"));
	        
	        // Fetch restaurant details from the database based on the ID
		    
	     Restaurant restaurant = restDAO.getRestaurant(restaurantId);

	        // Forward to the edit form, passing restaurant details as attributes
	        req.setAttribute("restaurant", restaurant);
	        RequestDispatcher rd = req.getRequestDispatcher("EditRestaurantForm.jsp");
	       rd.forward(req, resp);	
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
