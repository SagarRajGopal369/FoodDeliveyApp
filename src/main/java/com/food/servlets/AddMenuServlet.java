package com.food.servlets;



import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.food.dao.MenuDAO;
import com.food.daoImpl.MenuDAOImpl;
import com.food.models.Menu;

@WebServlet("/AddMenuServlet")
public class AddMenuServlet extends HttpServlet {
    private MenuDAO menuDAO;

    public void init() throws ServletException {
        // Initialize the MenuDAO instance in the init() method
        menuDAO = new MenuDAOImpl();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve data from the form
        int restaurantId = Integer.parseInt(request.getParameter("restaurantId"));
        String itemName = request.getParameter("itemName");
        String description = request.getParameter("description");
        double price = Double.parseDouble(request.getParameter("price"));
        double rating = Double.parseDouble(request.getParameter("rating"));
        String isAvailable = request.getParameter("isAvailable");
        String imagepath = request.getParameter("imagepath");

        // Create a Menu object using setters
       Menu newMenu = new Menu();
        newMenu.setRestaurantId(restaurantId);
        newMenu.setItemName(itemName);
        newMenu.setDescription(description);
        newMenu.setPrice(price);
        newMenu.setRating(rating);
        newMenu.setAvailable(isAvailable);
        newMenu.setImagepath(imagepath);

        // Call the method in MenuDAOImpl to add the menu
        menuDAO.addMenu(newMenu);

        // Redirect to Admin_Home.jsp after adding the menu
        response.sendRedirect("Admin_Home.jsp");
    }
}
