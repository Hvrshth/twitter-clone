<%@ page import="java.util.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>

<% List<String> messages = (List<String>) request.getAttribute("messages"); %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Course</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
        }
        h1 {
            color: #FF9800;
        }
    </style>
</head>
<body>

    <h1>Messages</h1>
    <p>Here you can send and view your messages.</p>

    <form action="messages" method="post">
        <input type="text" name="receiver" placeholder="Send to" required>
        <br>
        <textarea name="message" rows="3" cols="40" placeholder="Type your message"></textarea>
        <br>
        <input type="submit" value="Send">
    </form>

    <hr>
    <h2>Messages</h2>
    <ul>
        <% for(String message: messages){ %>
        <li><%= message %></li>
        <% } %>
    </ul>
</body>
</html>
