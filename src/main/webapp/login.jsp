<%@ page import="java.util.*" %>
<%@ page import="java.sql.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>

<% 
if ("POST".equalsIgnoreCase(request.getMethod())) {
    String eml = request.getParameter("email");
    String pass = request.getParameter("password");

    if (eml != null && pass != null) {
        //Establising a Database Connection
        String url = "jdbc:mysql://localhost:3306/twitter";
        String user = "root";
        String password = "root";

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);
            
            ps = conn.prepareStatement("SELECT * FROM USER WHERE email_address = ? AND password = ?");
            ps.setString(1, eml);
            ps.setString(2, pass);
            rs = ps.executeQuery();

            if(rs.next()){
                session.setAttribute("user", eml);
                response.sendRedirect("home.jsp");
            }
            else{
                request.setAttribute("errorMsg", "Invalid email or password");
            }
        }

        catch(Exception e){
            out.println("Database error: " + e.getMessage());
        }

        finally{
            try{ if(rs != null) rs.close(); } catch(Exception e){}
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
        <title>X / login</title>
        <link rel="icon" href="images/logo.png" type="image/png">
        <link rel="stylesheet" type="text/css" href="css/style.css">
    </head>

    <body>
        <h1>
            <img src="images/logo.png" alt="logo" style="height: 50px; vertical-align: middle;">
        </h1>
        
        <% 
            String signupMessage = (String) session.getAttribute("signupMessage");
            if (signupMessage != null) {
                out.println("<p style='color: green; justify-content:center; align-items:center; display:flex; text-align:center;'>" + signupMessage + "</p>");
                session.removeAttribute("signupMessage"); // Clear the message after displaying
            }
        %>

        <% if (request.getAttribute("errorMsg") != null) { %>
            <p style="color:red; justify-content:center; align-items:center; display:flex; text-align:center;"><%= request.getAttribute("errorMsg") %></p>
        <% } %>

        <div class="container">
            <form action="" method="post">
                <div>
                    <input type="text" placeholder="Email address" name="email" required>
                </div>
                <div>
                    <input type="password" placeholder="Password" name="password" required> <br> <br>
                </div>
                
                <button type="submit">Login</button> <br>
                <div>
                    <p>Don't have an account?</p>
                    <a href="signup.jsp">Sign up</a>
                </div>
            </form>
        </div>

        <!-- <script src="js/login.js" async defer></script> -->
    </body>
</html>