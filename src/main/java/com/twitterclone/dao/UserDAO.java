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

    // Validates username and password
    public boolean validateUser(String username, String password) throws Exception {
        String query = "SELECT * FROM users WHERE username = ? AND password = ?";
        
        try(Connection conn = DBUtil.getConnection(context);
        PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, username);
            ps.setString(2, password);

            try(ResultSet rs = ps.executeQuery()) {
                return rs.next(); // true if user found
            }
        }
    }

    // Registers new user
    public boolean registerUser(String username, String password) throws Exception {
        String query = "INSERT INTO users (username, password) VALUES (?, ?)";
        
        try(Connection conn = DBUtil.getConnection(context);
        PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, username);
            ps.setString(2, password);

           int rowsAffected = ps.executeUpdate();
           return rowsAffected > 0; // true if user was inserted
        }
    }

    // Updates password
    public boolean updatePassword(String username, String password) throws Exception {
        String query = "UPDATE users SET password = ? WHERE username = ?";
        try(Connection conn = DBUtil.getConnection(context);
        PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, password);
            ps.setString(2, username);

           int rowsAffected = ps.executeUpdate();
           return rowsAffected > 0; // true if password was updated
        }
    }

    // Deletes account
    public boolean deleteUser(String username) throws Exception {
        String query = "DELETE FROM users WHERE username = ?";
        try(Connection conn = DBUtil.getConnection(context);
        PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, username);

           int rowsAffected = ps.executeUpdate();
           return rowsAffected > 0; // true if account was deleted
        }
    }
}