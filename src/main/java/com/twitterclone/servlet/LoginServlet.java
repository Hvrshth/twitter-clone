// LoginServlet.java

package com.twitterclone.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.twitterclone.dao.UserDAO;

public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        // Step 1: Get login form data
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        // Validate input
        if (email == null || email.trim().isEmpty() || password == null || password.trim().isEmpty()) {
            req.setAttribute("errorMsg", "Email and password are required");
            req.getRequestDispatcher("login.jsp").forward(req, res);
            return;
        }

        try {
            // Step 2: Create DAO object with ServletContext (to access DB config)
            UserDAO dao = new UserDAO(getServletContext());

            // Step 3: Check credentials using DAO
            if (dao.validateUser(email, password)) {
                // Step 4a: If valid, store user info in session and redirect
                req.getSession().invalidate(); // Invalidate existing session
                req.getSession(true).setAttribute("user", email); // Create new session
                res.sendRedirect("home.jsp");
            } else {
                // Step 4b: If invalid, send error back to login.jsp
                req.setAttribute("errorMsg", "Invalid email or password");
                req.getRequestDispatcher("login.jsp").forward(req, res);
            }

        } catch (Exception e) {
            // Step 5: Handle exceptions
            throw new ServletException(e);
        }
    }
}
