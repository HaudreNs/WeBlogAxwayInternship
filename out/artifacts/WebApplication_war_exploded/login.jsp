<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Map" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
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

<div class="container">
    <% if (request.getAttribute("errors") != null) { %>
    <b>Some errors occurred:</b>
    <% for (Map.Entry<String, String> entry : ((HashMap<String, String>) request.getAttribute("errors")).entrySet()) {%>
    <div class="alert alert-danger">
        <% out.print(entry.getValue()); %>
    </div>
    <% } %>
    <% } %>
    <div class="panel panel-default">
        <form action="/Login" method="post">

            <div class="panel-heading">
                <h2>Login</h2>
            </div>
            <div class="panel-body">
                <form class="form-horizontal">
                    <div class="form-group">
                        <label class="control-label col-sm-2" for="userEmail">Email:</label>
                        <div class="col-sm-10">
                            <input type="email" name="userEmail" class="form-control" id="userEmail"
                                   placeholder="Enter email" value="ivan@abv.bg">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-2" for="userPass">Password:</label>
                        <div class="col-sm-10">
                            <input type="password" name="userPass" class="form-control" id="userPass"
                                   placeholder="Enter password" value="123456789">
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">
                            <button type="submit" value="register" class="btn btn-default">Login</button>
                        </div>
                    </div>
                </form>
            </div>
        </form>
    </div>
</div>
</body>
</html>
