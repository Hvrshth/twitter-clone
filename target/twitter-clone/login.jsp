<!-- login.jsp -->
<%@ page contentType="text/html; charset=UTF-8" %>
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
        <img src="images/logo.png" alt="logo" style="height: 50px; vertical-align: middle;">
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
                <input type="text" name="email" placeholder="Email address" required>
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