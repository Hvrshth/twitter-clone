// MessageDAO.java

package com.twitterclone.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import com.twitterclone.util.DBUtil;

public class MessageDAO {
    private final ServletContext context;

    public MessageDAO(ServletContext context){
        this.context = context;
    }

    public boolean sendMessage(String sender, String receiver, String message) throws Exception{
        String query = "INSERT INTO Messages(sender, receiver, message) VALUES (? , ?, ?)";

        try(Connection conn = DBUtil.getConnection(context); 
        PreparedStatement ps = conn.prepareStatement(query)){
                
                ps.setString(1, sender);
                ps.setString(2, receiver);
                ps.setString(3, message);
                
                int rowsAffected = ps.executeUpdate();
                return rowsAffected > 0;
            }
    }

    public List<String> getMessages(String receiver) throws Exception{
        List<String> messages = new ArrayList<>();
        String query = "SELECT CONCAT(sender, ': ', message) AS message FROM messages WHERE receiver = ?";

        try(Connection conn = DBUtil.getConnection(context); 
        PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, receiver);

            try(ResultSet rs = ps.executeQuery()){
                while(rs.next()){
                    messages.add(rs.getString("message"));
                }
            }   
        }             
        return messages;
    }
}
