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

@WebServlet("/DeleteMenuServlet")
public class DeleteMenuServlet extends HttpServlet {
	
	private MenuDAOImpl menuDAO;
	
	@Override
	public void init() throws ServletException {
		menuDAO = new MenuDAOImpl();
		
	}

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve the menuId parameter
        int menuId = Integer.parseInt(request.getParameter("menuId"));

        
    
        Menu menu = menuDAO.getMenu(menuId);
        
//        System.out.println(menu);
//        System.out.println(menu.getRestaurantId()+" inside delete menu servelt");
        int restaurantId = menu.getRestaurantId();
        
        //delete the menu
         menuDAO.deleteMenu(menuId);
        
        List<Menu> menus = menuDAO.getAllMenusByRestaurant(restaurantId);
        //Set menus in the request attribute
        request.setAttribute("menus", menus);
        
        
     // Go to AdminMenuDisplay page of same Restaurant  ****
        RequestDispatcher dispatcher = request.getRequestDispatcher("adminMenuDisplay.jsp");
        dispatcher.forward(request, response);
    }
}
