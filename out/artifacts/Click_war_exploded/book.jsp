<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Map" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Booking</title>
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

    <!-- jQuery library -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

    <!-- Latest compiled JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

    <style>
        .form-control {
            margin: 10px 0px;
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
            <a class="navbar-brand" href="\">Click</a>
        </div>
        <div class="collapse navbar-collapse" id="myNavbar">
            <ul class="nav navbar-nav">
                <% if (null != session.getAttribute("isLogged")) { %>
                <li><a href="Book">Book a trip</a></li>
                <li><a href="Admin">Admin</a></li>
                <% } %>

            </ul>
            <ul class="nav navbar-nav navbar-right">
                <% if (null == session.getAttribute("isLogged")) { %>
                    <li><a href="Logout"><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
                <% } else { %>
                    <li><a href="register.jsp"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>
                    <li><a href="Login"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
                <% } %>
            </ul>
        </div>
    </div>
</nav>
<% if (request.getAttribute("errors") != null) { %>
<b>Some errors occurred:</b>
<% for (Map.Entry<String, String> entry : ((HashMap<String, String>) request.getAttribute("errors")).entrySet()) {%>
<div class="alert alert-danger">
    <% out.print(entry.getValue()); %>
</div>
<% } %>
<% } %>
<div class="container">
    <div class="panel panel-default">
        <form action="Book" method="post">

            <div class="panel-heading">
                <h2>Booking</h2>
            </div>
            <div class="panel-body">
                <form class="form-horizontal">
                    <div class="form-group">
                        <label class="control-label col-sm-2" for="date">Date:</label>
                        <div class="col-sm-10">
                            <input type="date" name="date" class="form-control" id="date"
                                   placeholder="Enter date">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-2" for="places">Places:</label>
                        <div class="col-sm-10">
                            <input type="number" min="1" max="3" name="places" class="form-control" id="places"
                                   placeholder="Enter places">
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">
                            <button type="submit" value="book" class="btn btn-default">Book</button>
                        </div>
                    </div>

                </form>
            </div>
        </form>
    </div>
</div>

</body>
</html>
