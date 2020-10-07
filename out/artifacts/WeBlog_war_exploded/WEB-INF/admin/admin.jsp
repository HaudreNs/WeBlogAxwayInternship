<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@ page import="Helpers.Database" %>
<%@ page import="Models.User" %>
<%
    String id = request.getParameter("id");
    Database.openConnection();
    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;

    User user = (User) request.getAttribute("user");
    if(!user.isAdmin())
    {
        response.sendRedirect("/");
    }

%>
<html>
<body>

<form action="/WEB-INF/admin/admin.jsp" method="post">
    <h1>Retrieve data from database in jsp</h1>
    <table border="1">
        <tr>
            <td>id</td>
            <td>Name</td>
            <td>Age</td>
            <td>Town</td>
            <td>Motto</td>
            <td>update</td>
        </tr>
        <%
            try{
                connection = Database.getConnection();
                statement=connection.createStatement();
                String sql ="select * from user";
                resultSet = statement.executeQuery(sql);
                while(resultSet.next()){
        %>
        <tr>
            <td><%=resultSet.getString("id") %></td>
            <td><%=resultSet.getString("fullName") %></td>
            <td><%=resultSet.getString("age") %></td>
            <td><%=resultSet.getString("town") %></td>
            <td><%=resultSet.getString("motto") %></td>
            <td><a href="../../update-profile?id=<%=resultSet.getString("id")%>">update</a></td>
        </tr>
        <%
                }
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        %>
    </table>
</body>
</html>