package com.food.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.food.dao.MenuDAO;
import com.food.daoImpl.MenuDAOImpl;
import com.food.daoImpl.OrderItemDAOImpl;
import com.food.models.Cart;
import com.food.models.CartItem;
import com.food.models.Menu;
import com.food.models.Order;
import com.food.models.OrderItem;

@WebServlet("/AddToCartServlet")
public class AddToCartServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	 int menuId = Integer.parseInt(request.getParameter("menuId"));
         int quantity = Integer.parseInt(request.getParameter("quantity"));

         // Use a MenuDAO to get the menu details
         MenuDAO menuDAO = new MenuDAOImpl();
         Menu menu = menuDAO.getMenu(menuId);

         // Create a CartItem object with menu details
         CartItem cartItem = new CartItem(menuId, menu.getRestaurantId(), menu.getItemName(), menu.getPrice(), quantity);
         cartItem.setImagePath(menu.getImagepath()); // Set imagePath from menu

         // Retrieve or create a user's cart
         HttpSession session = request.getSession();
         Cart cart = (Cart) session.getAttribute("cart");
         if (cart == null) {
             cart = new Cart();
             session.setAttribute("cart", cart);
         }
      // i added rest into the session object so that we can use that in the cart.jsp page for redirecting ...
         session.setAttribute("restaurantId", menu.getRestaurantId()); 
         

         // Add the item to the cart
         cart.addItem(cartItem);

         // Redirect back to the menu display page or wherever you want after adding the item to the cart
        response.sendRedirect("cart.jsp");
    }
}
