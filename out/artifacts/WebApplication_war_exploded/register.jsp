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
        <form action="/Register" method="post">
            <%--<%--%>
            <%--if (request.getAttribute("errors") != null && ((HashMap) request.getAttribute("errors")).get("userName") != null) {--%>
            <%--out.println(((HashMap) request.getAttribute("errors")).get("userName"));--%>
            <%--}--%>
            <%--%>--%>
            <%--Name:<input type="text" name="userName"/><br/>--%>
            <%--<br/><br/>Surname:<input type="text" name="userSurname"/><br/><br/>--%>
            <%--Password:<input type="password" name="userPass"/><br/><br/>--%>
            <%--Email:<input type="text" name="userEmail"/><br/><br/>--%>
            <%--School number:<input type="text" name="userNumber"/><br/><br/>--%>

            <%--<input type="submit" value="register"/>--%>

            <div class="panel-heading">
                <h2>Register</h2>
            </div>
            <div class="panel-body">
                <form class="form-horizontal">
                    <div class="form-group">
                        <label class="control-label col-sm-2" for="userName">Name:</label>
                        <div class="col-sm-10">
                            <input type="text" name="userName" class="form-control" id="userName"
                                   placeholder="Enter name">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-2" for="userSurname">Surname:</label>
                        <div class="col-sm-10">
                            <input type="text" name="userSurname" class="form-control" id="userSurname"
                                   placeholder="Enter surname">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-2" for="userPass">Password:</label>
                        <div class="col-sm-10">
                            <input type="password" name="userPass" class="form-control" id="userPass"
                                   placeholder="Enter password">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-2" for="userEmail">Email:</label>
                        <div class="col-sm-10">
                            <input type="email" name="userEmail" class="form-control" id="userEmail"
                                   placeholder="Enter email">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-2" for="userNumber">School Number:</label>
                        <div class="col-sm-10">
                            <input type="text" name="userNumber" class="form-control" id="userNumber"
                                   placeholder="Enter school number">
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
