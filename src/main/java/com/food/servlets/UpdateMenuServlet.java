package com.food.servlets;

import com.food.dao.MenuDAO;
import com.food.daoImpl.MenuDAOImpl;
import com.food.models.Menu;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet("/UpdateMenuServlet")
public class UpdateMenuServlet extends HttpServlet {
	
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve data from the form
        int menuId = Integer.parseInt(request.getParameter("menuId"));
        String itemName = request.getParameter("itemName");
        String description = request.getParameter("description");
        double price = Double.parseDouble(request.getParameter("price"));
        double rating = Double.parseDouble(request.getParameter("rating"));
        String isAvailable = request.getParameter("isAvailable");
        String imagepath = request.getParameter("image");

        // Retrieve the restaurantId attribute set in EditMenuServlet
//        Object restaurantId = request.getAttribute("restaurantId");
//        int rstId = Integer.parseInt("restaurantId") ;
        
        HttpSession session = request.getSession();
        Integer restaurantId=(Integer)session.getAttribute("restaurantId");
        System.out.println(restaurantId+" inside update menuservlet");

         //Create a Menu object with the updated details
        Menu updatedMenu = new Menu(menuId, restaurantId, itemName, description, price, rating, isAvailable, imagepath);
        System.out.println(updatedMenu.getIsAvailable()+" inside updateservelt");
        // Update the menu in the database
        MenuDAO menuDAO = new MenuDAOImpl();
        menuDAO.updateMenu(updatedMenu);
        
        
        
         List<Menu> menus = menuDAO.getAllMenusByRestaurant(restaurantId);
         
//         System.out.println(menus+" inside updateservlet");
//         for (Menu menu : menus) {
//			System.out.println(menu.getItemName());
//		}
          //Set menus in the request attribute
          request.setAttribute("menus", menus);
         
        //remove restaurant id from session
          session.removeAttribute("restaurantId");

          // Go to AdminMenuDisplay page of same Restaurant 
          RequestDispatcher dispatcher = request.getRequestDispatcher("adminMenuDisplay.jsp");
          dispatcher.forward(request, response);
    }

   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	doPost(req, resp);
   }
}
