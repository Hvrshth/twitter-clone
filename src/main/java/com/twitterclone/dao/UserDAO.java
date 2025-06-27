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

    // Validates email and password against DB
    public boolean validateUser(String email, String password) throws Exception {
        String query = "SELECT * FROM USER WHERE email_address = ? AND password = ?";
        
        try(Connection conn = DBUtil.getConnection(context);
        PreparedStatement ps = conn.prepareStatement(query)) {

        ps.setString(1, email);
        ps.setString(2, password);

        try(ResultSet rs = ps.executeQuery()) {
            return rs.next(); // true if user found
        }
        }
    }
}