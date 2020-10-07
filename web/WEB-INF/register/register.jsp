<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Map" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
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
        <form action="register" method="post">
            <div class="panel-heading">
                <h2>Register</h2>
            </div>
            <div class="panel-body">
                <form class="form-horizontal">
                    <div class="form-group">
                        <label class="control-label col-sm-2" for="fullName">Name:</label>
                        <div class="col-sm-10">
                            <input type="text" name="fullName" class="form-control" id="fullName"
                                   placeholder="Enter your full name">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-2" for="username">Username:</label>
                        <div class="col-sm-10">
                            <input type="text" name="username" class="form-control" id="username"
                                   placeholder="Enter username">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-2" for="password">Password:</label>
                        <div class="col-sm-10">
                            <input type="password" name="password" class="form-control" id="password"
                                   placeholder="Enter password">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-2" for="email">Email:</label>
                        <div class="col-sm-10">
                            <input type="email" name="email" class="form-control" id="email"
                                   placeholder="Enter email">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-2" for="homeTown">Town:</label>
                        <div class="col-sm-10">
                            <input type="text" name="homeTown" class="form-control" id="homeTown"
                                   placeholder="Enter your home">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-2" for="age">Age:</label>
                        <div class="col-sm-10">
                            <input type="number" name="age" class="form-control" id="age"
                                   placeholder="Enter your age">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-2" for="motto">Description:</label>
                        <div class="col-sm-10">
                            <input type="text" name="motto" class="form-control" id="motto"
                                   placeholder="Say something about yourself">
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">
                            <button type="submit" value="register" class="btn btn-default">Register</button>
                        </div>
                    </div>
                </form>
            </div>
        </form>
    </div>
</div>

</body>
</html>
