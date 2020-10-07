<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="Helpers.Database" %>

<%
    try {
        Database.openConnection();
    } catch (ClassNotFoundException | SQLException e) {
        e.printStackTrace();
    }
%>
<%
    String id = request.getParameter("id");
    String fullName = request.getParameter("fullName");
    String age = request.getParameter("age");
    String town = request.getParameter("town");
    String motto = request.getParameter("motto");
    if (id != null) {
        Connection con = null;
        PreparedStatement ps = null;
        int personID = Integer.parseInt(id);
        try {

            try {
                con = Database.getConnection();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            String sql = "Update user set id=?,fullName=?,age=?,town=?,motto=? where id=" + id;
            ps = con.prepareStatement(sql);
            ps.setString(1, id);
            ps.setString(2, fullName);
            ps.setString(3, age);
            ps.setString(4, town);
            ps.setString(5, motto);
            int i = ps.executeUpdate();
            if (i > 0) {
//                out.print("Record Updated Successfully");
//                request.getRequestDispatcher("index.jsp");
                response.sendRedirect("/index.jsp");
            } else {
                out.print("There is a problem in updating Record.");
            }
        } catch (SQLException sql) {
            request.setAttribute("error", sql);
            out.println(sql);
        }
    }
%>