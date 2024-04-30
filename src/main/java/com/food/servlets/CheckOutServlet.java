package com.food.servlets;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.food.dao.OrderDAO;
import com.food.daoImpl.OrderDAOImpl;
import com.food.models.Cart;
import com.food.models.Order;
import com.food.models.OrderIdGenerator; // Import the OrderIdGenerator
import com.food.models.User;

@WebServlet("/CheckOutServlet")
public class CheckOutServlet extends HttpServlet {
    
    private OrderDAO orderDAO;

    @Override
    public void init() throws ServletException {
        orderDAO = new OrderDAOImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        
        User user = (User) session.getAttribute("user");
        Cart cart = (Cart) session.getAttribute("cart");

         if (user == null)
        {
            resp.sendRedirect("SignInPage.jsp");
        } 
        else if (cart == null || cart.getItems().isEmpty() || session.getAttribute("totalAmount") == null)
        {
        	resp.sendRedirect("home.jsp");
        } 
        else if (cart != null && user != null && !cart.getItems().isEmpty()) 
        {
            double totalAmount = (double) session.getAttribute("totalAmount");

            String paymentMethod = req.getParameter("paymentMethod");

            Order order = new Order();
            // Set the orderId using the generated value
            order.setOrderId(OrderIdGenerator.generateOrderId(user.getUserId())); 
            order.setUserId(user.getUserId());
            order.setRestaurantId((int) session.getAttribute("restaurantId"));

            java.sql.Date orderDate = new java.sql.Date(new Date().getTime());
            order.setOrderDate(orderDate);
            order.setPaymentMethod(paymentMethod);
            order.setStatus("pending");
            order.setTotalAmount(totalAmount);

            // Save the order in the database
            orderDAO.addOrder(order);

            // Clear the cart and redirect to the order confirmation page
            session.removeAttribute("totalAmount");
            session.removeAttribute("cart");
            session.setAttribute("order", order);
            resp.sendRedirect("order_Confirmation.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
