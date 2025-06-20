package com.twitterclone.dao;

import java.sql.Connection;
import java.sql.DriverManager; 

import javax.servlet.ServletContext;

public class DBUtil {

    //Java method designed to return a database connection by using configuration details (like DB URL, username, and password) 
    //provided by the web application through the ServletContext

    //ServletContext is an object provided by the Java Servlet API.
    //It represents the web application environment shared by all servlets.
    //It's used to access application-wide configuration data, like init parameters, files, and resources.

    public static Connection getConnection(ServletContext context) throws Exception{

        // Get DB connection info from servlet context
        String url = context.getInitParameter("dbUrl"); //fetches initialization parameters defined in the web app’s deployment descriptor (web.xml)
        String usr = context.getInitParameter("dbUser");
        String pass = context.getInitParameter("dbPassword");

        // Loads JDBC driver
        Class.forName("com.mysql.cj.jdbc.Driver");

        // Gets and return a DB connection
        return DriverManager.getConnection(url, usr, pass);
    }
}
