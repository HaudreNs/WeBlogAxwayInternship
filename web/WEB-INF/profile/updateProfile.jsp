<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@ page import="Helpers.Database" %>
<%
    String id = request.getParameter("id");
    Database db = new Database();
    db.openConnection();
    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;
%>
<%
    try{
        connection = db.getConnection();
        statement=connection.createStatement();
        String sql ="select * from user where id="+id;
        resultSet = statement.executeQuery(sql);
        while(resultSet.next()){
%>
<!DOCTYPE html>
<html>
<body>
<h1>Personal Profile Details</h1>
<form method="post" action="/update-profile">
    <input type="hidden" name="id" value="<%=resultSet.getString("id") %>">
    <br>
    Name:<br>
    <input type="text" name="fullName" value="<%=resultSet.getString("fullName") %>">
    <br>
    Age:<br>
    <input type="text" name="age" value="<%=resultSet.getInt("age") %>">
    <br>
    Town:<br>
    <input type="text" name="town" value="<%=resultSet.getString("town") %>">
    <br>
    Motto:<br>
    <input type="text" name="motto" value="<%=resultSet.getString("motto") %>">
    <br><br>
    <input type="submit" value="submit">
</form>
<%
        }
        connection.close();
    } catch (Exception e) {
        e.printStackTrace();
    }
%>
</body>
</html>