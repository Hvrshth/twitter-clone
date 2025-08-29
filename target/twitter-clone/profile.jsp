<!-- profile.jsp -->

<%@ page import="java.util.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Profile</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
        }
        h1 {
            color: green;
        }
    </style>
</head>
<body>
    <h1>Profile Page</h1>
    <%-- Success message after updating the password --%>
    <% String Message = (String) session.getAttribute("Message"); %>
    <% if (Message != null) { %>
        <p style="color: green; text-align: center;"><%= Message %></p>
        <% session.removeAttribute("Message"); %>
    <% } %>


    <br>
    <form action="updatepassword" method="post">
        <label>New Password:</label>
        <input type="password" name="newPassword" required>
        <button type="submit">Update Password</button>
    </form>
    <br>
    <form action="delete" method="post">
        <label>Delete Account:</label>
        <button type="submit">Delete Account</button>
    </form>
</body>
</html>
