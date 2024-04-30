package com.food.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.food.dao.UserDAO;
import com.food.daoImpl.UserDAOImpl;
import com.food.models.User;
import org.mindrot.jbcrypt.BCrypt;

@WebServlet("/UpdateProfileServlet")
public class UpdateProfileServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve user details from the form
        int userId = Integer.parseInt(request.getParameter("userId"));
        String username = request.getParameter("username");
        String plainPassword = request.getParameter("password"); // Note: Plain text password
        String email = request.getParameter("email");
        String phoneNo = request.getParameter("phoneNo");
        String address = request.getParameter("address");
        String role = request.getParameter("role");

        // Hash the plain text password using BCrypt
        String hashedPassword = BCrypt.hashpw(plainPassword, BCrypt.gensalt());

        // Create a User object with the edited details
        User editedUser = new User(userId, username, hashedPassword, email, phoneNo, address, role);

        // Update user details in the database
        UserDAO userDAO = new UserDAOImpl();
        userDAO.updateUser(editedUser);

        // Redirect to the user profile page after updating
        response.sendRedirect("SignInPage.jsp");
    }
}
