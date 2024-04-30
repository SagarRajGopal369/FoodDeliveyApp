package com.food.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.food.dao.UserDAO;
import com.food.daoImpl.UserDAOImpl;
import com.food.models.User;

@WebServlet("/AdminSignInServlet")
public class AdminSignInServlet extends HttpServlet {
	 private static final long serialVersionUID = 1L;
	 
	   private UserDAO userDAO;
	    
	    @Override
	    public void init(){
	    	userDAO = new UserDAOImpl();
	    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		
		 // Retrieve user details from the sign-in form
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Check if the user exists and retrieve their details
        User user = userDAO.getUserByUserName(username);
        
        String name = user.getUsername();
   	    String role = user.getRole();
        if (user != null && user.getPassword().equals(password) && role.equalsIgnoreCase("Admin") )
        {
        		request.getSession().setAttribute("name", name);
              	response.sendRedirect("AddRestaurantForm.jsp");
        } 
        else
        {
        	 // If the sign-in fails, set an attribute to indicate the failure.
            request.setAttribute("signInError", "Invalid details");
            // Redirect back to the sign-in page
            RequestDispatcher dispatcher = request.getRequestDispatcher("SignInPage.jsp");
            dispatcher.forward(request, response);
        }
    }
}


