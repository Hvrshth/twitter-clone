<%@ page language="java" contentType="text/html; charset=UTF-8" %>

<% 
    // Prevents browser caching
    response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate, max-age=0");
    response.setHeader("Pragma", "no-cache");
    response.setDateHeader("Expires", 0);

    // Check for valid session
    if (session.getAttribute("user") == null) {
        response.sendRedirect("login.jsp");
        return; // Stop further processing
    }
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>X / Home</title>
    <link rel="icon" href="images/logo.png" type="image/png">
    <link rel="stylesheet" type="text/css" href="css/home.css">
</head>

<body>
    
    <% 
        String uname = (String) session.getAttribute("user");
    %>

    <h1>Welcome: <%= uname%></h1>
    <header>
        <a href="home.jsp">
            <img src="images/logo.png" alt="logo" style="height: 50px; vertical-align: middle;">
        </a>
    </header>

    <div class="container">
        <nav>
            <a href="timeline" target="content-frame">Home</a>
            <a href="messages.jsp" target="content-frame">Messages</a>
            <a href="profile.jsp" target="content-frame">Profile</a>
            <a href="logout.jsp">Logout</a>
        </nav>
        <iframe name="content-frame" src="timeline"></iframe>
    </div>
</body>
</html>
