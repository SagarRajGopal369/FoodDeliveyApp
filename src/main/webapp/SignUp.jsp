<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
     <link rel="stylesheet" href="signUp.css">
    <title>Signup Page</title>
</head>
<body>

    <h2>Signup Page</h2>
    
    <%--Display error message if user name is already  exist --%>
   <% String userNameError = (String)request.getAttribute("userNameError");
       if (userNameError != null && !userNameError.isEmpty()) { %>
        <p style="color: red;text-align: center; font-size:20px;"><%= userNameError %></p>
    <% } %>
    <form action="SignUpServlet" method="post">
        <label for="username">Username:</label>
        <input type="text" id="username" name="username" required><br>

        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required><br>

        <label for="email">Email:</label>
        <input type="email" id="email" name="email" required><br>

        <label for="phone">Phone:</label>
        <input type="tel" id="phone" name="phone" required><br>

        <label for="address">Address:</label>
        <textarea id="address" name="address" required></textarea><br>

        <label for="role">Role:</label>
        <select id="role" name="role">
            <option value="customer">Customer</option>
            <option value="admin">Admin</option>
            <option value="DeliveryAgent">DeliveryAgent</option>
        </select><br>

        <input type="submit" value="Signup">
        
        <span>Already Have a Account <a href="SignInPage.jsp">Login</a></span>
     	
    </form>
     

</body>
</html>
