//PostDAO.java

package com.twitterclone.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import com.twitterclone.util.DBUtil;

public class PostDAO {
    private final ServletContext context;

    public PostDAO(ServletContext context){
        this.context = context;
    }

    public boolean addPost(String username, String content) throws Exception{
        String query = "INSERT INTO POSTS(username, content, posted_at) VALUES (? , ?, NOW())";

        try(Connection conn = DBUtil.getConnection(context); 
        PreparedStatement ps = conn.prepareStatement(query)){
                
                ps.setString(1, username);
                ps.setString(2, content);
                
                int rowsAffected = ps.executeUpdate();
                return rowsAffected > 0;
            }
    }

    public List<String> getPosts() throws Exception{
        List<String> posts = new ArrayList<>();
        String query = "SELECT CONCAT(username, ': ', content) AS post FROM posts ORDER BY posted_at DESC";

        try(Connection conn = DBUtil.getConnection(context); 
        PreparedStatement ps = conn.prepareStatement(query);
        ResultSet rs = ps.executeQuery()){
            while(rs.next()){
                posts.add(rs.getString("post"));
            }                
        }
        return posts;
    }
}
