// TimelineServlet.java

package com.twitterclone.servlet;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.twitterclone.dao.PostDAO;

public class TimelineServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException{
        try{
            PostDAO dao = new PostDAO(getServletContext());
            List<String> posts = dao.getPosts();
            req.setAttribute("posts", posts);
            req.getRequestDispatcher("timeline.jsp").forward(req, res);
        } catch(Exception e){
            throw new ServletException(e);
        }
    }
}