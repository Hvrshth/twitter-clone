<%@ page contentType="text/html; charset=UTF-8" %>
<%
request.getSession().invalidate();
response.sendRedirect("login.jsp");
%>