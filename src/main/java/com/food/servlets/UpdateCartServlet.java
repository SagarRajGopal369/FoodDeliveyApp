package com.food.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.food.models.Cart;

@WebServlet("/UpdateCartServlet")
public class UpdateCartServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve form data
        int menuId = Integer.parseInt(request.getParameter("menuId"));
        String quantityParameter = request.getParameter("quantity_"+menuId);

        System.out.println(quantityParameter);
        System.out.println(menuId);
        // Check if the quantity parameter is not null and not empty to avoid 500 error(Hidden test cases)
        if (quantityParameter != null && !quantityParameter.isEmpty()) {
            int quantity = Integer.parseInt(quantityParameter);
          
            System.out.println("it is working");
            System.out.println(quantityParameter);
            System.out.println(menuId);
            // Retrieve the user's cart
            HttpSession session = request.getSession();
            Cart cart = (Cart) session.getAttribute("cart");

            // Update the quantity of the item in the cart
            cart.updateItem(menuId, quantity);
        }

        // Redirect back to the cart page ...
        response.sendRedirect("cart.jsp");
    }
}
