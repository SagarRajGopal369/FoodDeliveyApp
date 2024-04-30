package com.food.servlets;


import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.food.daoImpl.MenuDAOImpl;
import com.food.models.Menu ;


@WebServlet("/EditMenuServlet")
public class EditMenuServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    private MenuDAOImpl menuDAO;
    
    @Override
    public void init() throws ServletException {
    	menuDAO = new MenuDAOImpl();
    }
    

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Get menuId parameter from the request
        int menuId = Integer.parseInt(request.getParameter("menuId"));
       // int restaurantId = Integer.parseInt(request.getParameter("restaurantId"));


        // Call the method in MenuDAOImpl to get the Menu object by menuId
        Menu menu = menuDAO.getMenu(menuId);
        int restaurantId = menu.getRestaurantId();
        System.out.println(restaurantId+" inside editMenuservlet");
        // Set the retrieved Menu object as an attribute in the request
        HttpSession session = request.getSession();
        session.setAttribute("restaurantId", restaurantId);
        request.setAttribute("menu", menu);
       
        // Forward the request to the editMenu.jsp page
        RequestDispatcher dispatcher = request.getRequestDispatcher("editMenu.jsp");
        dispatcher.forward(request, response);
    }
}
