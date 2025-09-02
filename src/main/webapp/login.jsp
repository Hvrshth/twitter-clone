<!-- login.jsp -->
<%@ page contentType="text/html; charset=UTF-8" %>
<% 
    // Prevents browser caching
    response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate, max-age=0");
    response.setHeader("Pragma", "no-cache");
    response.setDateHeader("Expires", 0);

    // Check if valid session exists already
    if (session.getAttribute("user") != null) {
        response.sendRedirect("home.jsp");
        return; // Stop further processing
    }
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>X / Login</title>
    <link rel="icon" href="images/logo.png" type="image/png">
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
    <h1>
        <a href="login.jsp">
            <img src="images/logo.png" alt="logo" style="height: 50px; vertical-align: middle;">
        </a>
    </h1>

    <%-- Success message after signup --%>
    <% String signupMessage = (String) session.getAttribute("signupMessage"); %>
    <% if (signupMessage != null) { %>
        <p style="color: green; text-align: center;"><%= signupMessage %></p>
        <% session.removeAttribute("signupMessage"); %>
    <% } %>

    <%-- Error message on failed login --%>
    <% if (request.getAttribute("errorMsg") != null) { %>
        <p style="color: red; text-align: center;"><%= request.getAttribute("errorMsg") %></p>
    <% } %>

    <div class="container">
        <form action="login" method="post">
            <div>
                <input type="text" name="username" placeholder="Username" required>
            </div>
            <div>
                <input type="password" name="password" placeholder="Password" required><br><br>
            </div>
            <button type="submit">Login</button><br>
            <div>
                <p>Don't have an account?</p>
                <a href="signup.jsp">Sign up</a>
            </div>
        </form>
    </div>
</body>
</html>