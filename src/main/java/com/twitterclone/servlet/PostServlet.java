// PostServlet.java

package com.twitterclone.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.twitterclone.dao.PostDAO;

public class PostServlet extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
        String username = (String)req.getSession().getAttribute("user");
        String postContent = req.getParameter("post");

        if(username == null){
            res.sendRedirect("login.jsp");
        }

        try {
            PostDAO dao = new PostDAO(getServletContext());
            dao.addPost(username, postContent);
            res.sendRedirect("timeline"); // Reload timeline after posting
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}