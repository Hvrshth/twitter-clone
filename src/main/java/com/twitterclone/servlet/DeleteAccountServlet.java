// DeleteAccount.java
package com.twitterclone.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.twitterclone.dao.UserDAO;

public class DeleteAccountServlet extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
        String username = (String)req.getSession().getAttribute("user");

        if(username == null){
            res.sendRedirect("login.jsp");
            return; // stop execution
        }

        try {
            UserDAO dao = new UserDAO(getServletContext());
            dao.deleteUser(username);
            
            req.getSession().invalidate(); // clear session
            res.getWriter().print("<script>top.location='login.jsp'</script>"); // Redirect to login after deleting the account
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
