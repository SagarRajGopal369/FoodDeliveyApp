package com.food.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.food.dao.UserDAO;
import com.food.daoImpl.UserDAOImpl;
import com.food.models.User;
import org.mindrot.jbcrypt.BCrypt; // Import BCrypt

@WebServlet("/SignInServlet")
public class SignInServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private UserDAO userDAO;

    @Override
    public void init() {
        userDAO = new UserDAOImpl();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Retrieve user details from the sign-in form
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = userDAO.getUserByUserName(username);

        // Check if the user exists and verify their password using BCrypt
        if (user != null && BCrypt.checkpw(password, user.getPassword())) {
            String name = user.getUsername();
            request.getSession().setAttribute("name", name);
            request.getSession().setAttribute("user", user);

            String role = user.getRole();
            if ("Customer".equals(role)) {
                response.sendRedirect("home.jsp");
            } else if ("Admin".equals(role)) {
                response.sendRedirect("Admin_Home.jsp");
            } else {
                response.sendRedirect("#");
                System.out.println("customer view");
            }
        } else {
            // If the sign-in fails, set an attribute to indicate the failure.
            request.setAttribute("signInError", "Invalid details");
            // Redirect back to the sign-in page
            RequestDispatcher dispatcher = request.getRequestDispatcher("SignInPage.jsp");
            dispatcher.forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
