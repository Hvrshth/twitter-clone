// UpdatePassword.java
package com.twitterclone.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.twitterclone.dao.UserDAO;

public class UpdatePasswordServlet extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
        String username = (String)req.getSession().getAttribute("user");
        String password = req.getParameter("newPassword");

        if(username == null){
            res.sendRedirect("login.jsp");
            return; // stop execution
        }

        try {
            UserDAO dao = new UserDAO(getServletContext());
            dao.updatePassword(username, password);
            HttpSession session = req.getSession(true);
            session.setAttribute("Message", "Your password has been updated successfully!");
            res.sendRedirect("profile.jsp"); // Redirect to profile after updating the password
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
