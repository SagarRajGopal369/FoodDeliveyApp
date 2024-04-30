package com.food.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.food.models.Cart;

@WebServlet("/RemoveFromCartServlet")
public class RemoveFromCartServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve form data
        int menuId = Integer.parseInt(request.getParameter("menuId"));

        // Retrieve the user's cart
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");

        // Remove the item from the cart
        cart.removeItem(menuId);

        // Redirect back to the cart page or wherever you want after removing the item from the cart
        response.sendRedirect("cart.jsp");
    }
}
