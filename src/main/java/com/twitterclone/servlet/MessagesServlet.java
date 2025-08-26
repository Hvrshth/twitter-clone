// MessageServlet.java

package com.twitterclone.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.twitterclone.dao.MessageDAO;

public class MessagesServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException{
        try{
            String receiver = (String)req.getSession().getAttribute("user");
            MessageDAO dao = new MessageDAO(getServletContext());
            List<String> messages = dao.getMessages(receiver);
            req.setAttribute("messages", messages);
            req.getRequestDispatcher("messages.jsp").forward(req, res);
        } catch(Exception e){
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String sender = (String) req.getSession().getAttribute("user");
        String receiver = req.getParameter("receiver");
        String message = req.getParameter("message");

        try {
            MessageDAO dao = new MessageDAO(getServletContext());
            dao.sendMessage(sender, receiver, message);
            res.sendRedirect("messages"); // reload messages
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}