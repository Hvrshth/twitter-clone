<%@ page import="java.util.*" %>
<%@ page import="java.sql.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>

<% 
if ("POST".equalsIgnoreCase(request.getMethod())) {
    String firstnm = request.getParameter("firstname");
    String lastnm = request.getParameter("lastname");
    String eml = request.getParameter("email");
    String pass = request.getParameter("password");

    if (firstnm != null && eml != null && pass != null) {
        //Establising a Database Connection
        String url = "jdbc:mysql://localhost:3306/twitter";
        String user = "root";
        String password = "root";

        Connection conn = null;
        PreparedStatement ps = null;

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);
            
            ps = conn.prepareStatement("INSERT INTO user (first_name, last_name, email_address, password) VALUES (?, ?, ?, ?)");
            ps.setString(1, firstnm);
            ps.setString(2, lastnm);
            ps.setString(3, eml);
            ps.setString(4, pass);

            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                session.setAttribute("signupMessage", "Your account has been successfully created!");
                response.sendRedirect("login.jsp");
            } else {
                request.setAttribute("errorMsg", "Something went wrong");
            }
        }

        catch(Exception e){
            out.println("Database error: " + e.getMessage());
        }

        finally{
            try{ if(ps != null) ps.close(); } catch(Exception e){}
            try{ if(conn != null) conn.close(); } catch(Exception e){}
        }
    }
}
%>


<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>X / Sign up</title>
        <link rel="icon" href="images/logo.png" type="image/png">
        <link rel="stylesheet" type="text/css" href="css/style.css">
    </head>

    <body>
        <h1>
            <img src="images/logo.png" alt="logo" style="height: 50px; vertical-align: middle;">
        </h1>

        <% if (request.getAttribute("errorMsg") != null) { %>
            <p style="color:green; justify-content:center; align-items:center; display:flex; text-align:center;"><%= request.getAttribute("errorMsg") %></p>
        <% } %>

        <div class="container">
            <form action="" method="post">
                <div>
                    <input type="text" placeholder="First Name" name="firstname" required> <br> <br>
                </div>
                <div>
                    <input type="text" placeholder="Last Name" name="lastname" required> <br> <br>
                </div>
                <div>
                    <input type="email" placeholder="Email address" name="email" required> <br> <br>
                </div>
                <div>
                    <input type="password" placeholder="Password" name="password" required> <br> <br>
                </div>
                
                <button type="submit">Sign up</button> <br>
                <div>
                    <p>Already have an account?</p>
                    <a href="login.jsp">Sign in</a>
                </div>
            </form>
        </div>

        <!-- <script src="js/register.js" async defer></script> -->
    </body>
</html>