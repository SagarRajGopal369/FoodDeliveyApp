package com.food.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mindrot.jbcrypt.BCrypt; // Import BCrypt

import com.food.dao.UserDAO;
import com.food.daoImpl.UserDAOImpl;
import com.food.models.User;

@WebServlet("/SignUpServlet")
public class SignUpServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private UserDAO userDAO;

    @Override
    public void init() {
        userDAO = new UserDAOImpl();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Retrieve user details from the signup form
        String username = request.getParameter("username");
        String plainPassword = request.getParameter("password");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String role = request.getParameter("role");

        // Hash the user's password using BCrypt
        String hashedPassword = BCrypt.hashpw(plainPassword, BCrypt.gensalt());

        // Check if the username already exists
        User existingUser = userDAO.getUserByUserName(username);

        if (existingUser == null) {
            // User does not exist, proceed with signup
            User newUser = new User(username, hashedPassword, email, phone, address, role);

            try {
                userDAO.addUser(newUser);
                response.sendRedirect("SignInPage.jsp");
            } catch (Exception e) {
                e.printStackTrace();
                response.sendRedirect("ErrorPage.jsp");
            }
        } else {
            // User already exists, handle accordingly
            request.setAttribute("userNameError", "User Name Already Existed");
            RequestDispatcher dispatcher = request.getRequestDispatcher("SignUp.jsp");
            dispatcher.forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
