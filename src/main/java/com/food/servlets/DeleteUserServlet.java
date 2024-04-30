package com.food.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.food.dao.UserDAO;
import com.food.daoImpl.UserDAOImpl;
import com.food.models.User;

@WebServlet("/DeleteUserServlet")
public class DeleteUserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	HttpSession session = request.getSession();
    	
        // Retrieve user ID from the request
        int userId = Integer.parseInt(request.getParameter("userId"));
        
        session.removeAttribute("user");

        // Delete user from the database
        UserDAO userDAO = new UserDAOImpl();
        userDAO.deleteUser(userId);

        // Redirect to userDeleted.jsp after deletion
        response.sendRedirect("userDeleted.jsp");
    }
}
