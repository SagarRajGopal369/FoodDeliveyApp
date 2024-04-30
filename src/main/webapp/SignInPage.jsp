<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <link rel="stylesheet" href="signUp.css">
    <title>Sign In Page</title>
</head>
<body>
    <h2>Sign In</h2>

    <%-- Display error message if sign-in fails --%>
    <% String signInError = (String)request.getAttribute("signInError");
       if (signInError != null && !signInError.isEmpty()) { %>
        <p style="color: red; text-align: center;"><%= signInError %></p>
    <% } %>

    <form action="SignInServlet" method="post">
        <label for="username">UserName</label>
        <input type="text" id="username" name="username" required><br>

        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required><br>

        <input type="submit" value="Sign In">

        <span>Don't Have an Account <a href="SignUp.jsp">Sign Up</a></span>
    </form>

</body>
</html>
