// SignupServlet.java

package com.twitterclone.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.twitterclone.dao.UserDAO;

public class SignupServlet extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        // Step 1: Get signup form data
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        // Validate input
        if (username == null || username.trim().isEmpty() || password == null || password.trim().isEmpty()) {
            req.setAttribute("errorMsg", "Fill out all the fields");
            req.getRequestDispatcher("signup.jsp").forward(req, res);
            return;
        }

        try {
            // Step 2: Create DAO object with ServletContext (to access DB config)
            UserDAO dao = new UserDAO(getServletContext());

            // Step 3: Insert credentials using DAO
            if (dao.registerUser(username, password)) {
                // Step 4a: If valid, store user info in session and redirect
                req.getSession().invalidate(); // Clear any existing session
                HttpSession session = req.getSession(true); // Create new session
                session.setAttribute("signupMessage", "Your account has been successfully created!");
                res.sendRedirect("login.jsp");
            } else {
                // Step 4b: If failed, send error back to signup.jsp
                req.setAttribute("errorMsg", "Registration failed");
                req.getRequestDispatcher("signup.jsp").forward(req, res);
            }

        } catch (Exception e) {
            // Step 5: Handle exceptions
            throw new ServletException(e);
        }
    }
}