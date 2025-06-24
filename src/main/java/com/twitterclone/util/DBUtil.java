package com.twitterclone.util;

import java.sql.Connection;
import java.sql.DriverManager; 

import javax.servlet.ServletContext;

public class DBUtil {
    public static Connection getConnection(ServletContext context) throws Exception{

        // gets DB connection info from servlet context
        String url = context.getInitParameter("dbUrl"); // fetches initialization parameters defined in the web app’s deployment descriptor web.xml
        String usr = context.getInitParameter("dbUser");
        String pass = context.getInitParameter("dbPassword");

        Class.forName("com.mysql.cj.jdbc.Driver"); // loads JDBC driver
        return DriverManager.getConnection(url, usr, pass); // gets and returns a DB connection
    }
}
