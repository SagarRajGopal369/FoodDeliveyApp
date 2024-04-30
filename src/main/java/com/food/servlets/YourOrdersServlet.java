package com.food.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.food.dao.OrderDAO;
import com.food.daoImpl.OrderDAOImpl;
import com.food.models.Order;
import com.food.models.User;

@WebServlet("/YourOrdersServlet")
public class YourOrdersServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        System.out.println("inside YourOderServlet");
        // Check if the user is logged in
        if (user == null)
        {
            // Redirect to SignInPage.jsp if the user is not logged in
            response.sendRedirect("SignInPage.jsp");
            return;
        }

        // User is logged in, fetch orders
        OrderDAO orderDAO = new OrderDAOImpl();
        List<Order> userOrders = orderDAO.getALLOrdersByUser(user.getUserId());

        // Set the list of orders in the request attribute
        request.setAttribute("userOrders", userOrders);

        // Forward the request to YourOrders.jsp
        request.getRequestDispatcher("YourOrders.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
