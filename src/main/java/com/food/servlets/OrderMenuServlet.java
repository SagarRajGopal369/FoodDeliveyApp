package com.food.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.food.daoImpl.MenuDAOImpl;
import com.food.models.Menu;
import com.food.models.User;

//OrderMenuServlet.java

//Import statements

@WebServlet("/OrderMenuServlet")
public class OrderMenuServlet extends HttpServlet {
 private static final long serialVersionUID = 1L;

 protected void doGet(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
     // Retrieve restaurantId from the request parameter
     int restaurantId = Integer.parseInt(request.getParameter("restaurantId"));

     // Use the restaurantId to fetch menus from the database
    MenuDAOImpl menu = new MenuDAOImpl();
    List<Menu> menus = menu.getAllMenusByRestaurant(restaurantId);
      //Set menus in the request attribute
     request.setAttribute("menus", menus);
     
     HttpSession session = request.getSession();
 	User user=(User)session.getAttribute("user");
     
     if (user != null)
     {
      	 System.out.println("inside order menuservlet");
         String role = user.getRole();
         if ("Customer".equals(role)) 
         {
        	 RequestDispatcher dispatcher = request.getRequestDispatcher("menuDisplay.jsp");
             dispatcher.forward(request, response);
         } 
         else if ("Admin".equals(role))
         {
        	 System.out.println("admin inside order menuservlet");
        	 RequestDispatcher dispatcher = request.getRequestDispatcher("adminMenuDisplay.jsp");
             dispatcher.forward(request, response);
         } 
     } 
     else if(user == null)
     {
    	 System.out.println("else  part inside order menuservlet");
//         RequestDispatcher dispatcher = request.getRequestDispatcher("SignUp.jsp");
//         dispatcher.forward(request, response);
    	 
    	 RequestDispatcher dispatcher = request.getRequestDispatcher("menuDisplay.jsp");
         dispatcher.forward(request, response);
       }
    
 }
}
