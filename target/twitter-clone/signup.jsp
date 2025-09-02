<!-- signup.jsp -->
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>X / Sign up</title>
    <link rel="icon" href="images/logo.png" type="image/png">
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
    <h1>
        <a href="login.jsp">
            <img src="images/logo.png" alt="logo" style="height: 50px; vertical-align: middle;">
        </a>
    </h1>

    <%-- Error message on failed login --%>
    <% if (request.getAttribute("errorMsg") != null) { %>
        <p style="color: red; text-align: center;"><%= request.getAttribute("errorMsg") %></p>
    <% } %>

    <div class="container">
        <form action="signup" method="post">
           <div>
                <input type="text" name="username" placeholder="Username" required>
            </div>
            <div>
                <input type="password" name="password" placeholder="Password" required> <br> <br>
            </div>
            
            <button type="submit">Sign up</button> <br>
            <div>
                <p>Already have an account?</p>
                <a href="login.jsp">Sign in</a>
            </div>
        </form>
    </div>
</body>
</html>