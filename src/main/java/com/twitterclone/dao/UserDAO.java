//UserDAO.java

package com.twitterclone.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletContext;

import com.twitterclone.util.DBUtil;

public class UserDAO {
    private final ServletContext context;

    public UserDAO(ServletContext context){
        this.context = context;
    }

    // Validates username and password against DB
    public boolean validateUser(String username, String password) throws Exception {
        String query = "SELECT * FROM USER WHERE username = ? AND password = ?";
        
        try(Connection conn = DBUtil.getConnection(context);
        PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, username);
            ps.setString(2, password);

            try(ResultSet rs = ps.executeQuery()) {
                return rs.next(); // true if user found
            }
        }
    }

    public boolean registerUser(String username, String password) throws Exception {
        String query = "INSERT INTO user (username, password) VALUES (?, ?)";
        
        try(Connection conn = DBUtil.getConnection(context);
        PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, username);
            ps.setString(2, password);

           int rowsAffected = ps.executeUpdate();
           return rowsAffected > 0; // true if user was inserted
        }
    }
}