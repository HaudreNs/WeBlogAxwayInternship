<%@ page import="Models.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    User user = (User) request.getAttribute("user");
%>
<html>
<head>
    <title>Home</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

    <!-- jQuery library -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

    <!-- Latest compiled JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <style>
        a {
            color: black;
        }
    </style>
</head>
<body>
<nav class="navbar">
    <div class="container-fluid" style="border-bottom-style: groove;">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="myNavbar">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="/">WeBlog</a>
        </div>
        <div class="collapse navbar-collapse" id="myNavbar">
            <ul class="nav navbar-nav">
                <% if (null != user) { %>
                <li><a href="drama">Drama</a></li>
                <li><a href="friends">Friends</a></li>

                <%
                    int userID = (Integer) session.getAttribute("id");
                %>
                <li><a href="/update-profile?id=<%=+userID%>">Profile</a></li>

                <% if (user.isAdmin()) { %>
                <li><a href="admin">Admin</a></li>
                <% } %>
                <% } %>

            </ul>
            <% if (null == user) { %>
            <ul class="nav navbar-nav">
                <li><a href="drama" style="">Demo Drama</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="register"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>
                <li><a href="login"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
                <% } else { %>
                <ul class="nav navbar-nav navbar-right">
                <li><a href="logout"><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
                </ul>
                <% } %>
            </ul>
        </div>
    </div>
</nav>

</body>
</html>
