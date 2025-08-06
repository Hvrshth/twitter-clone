<%@ page import="java.util.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Timeline</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
        }
        h1 {
            color: #2196F3;
        }
    </style>
</head>
<body>
    <h1>What's happening?</h1>
    <p>Here you can publish your posts or view posts by other users</p>
    <form action="post" method="post">
        <textarea id="post" name="post" rows="4" cols="50"></textarea>
        <br>
        <input type="submit" value="Post">
    </form>
</body>
</html>
