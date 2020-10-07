<%@ page import="Helpers.Database" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.Statement" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Completed bookings panel</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

    <!-- jQuery library -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

    <!-- Latest compiled JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

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
                <li><a href="Completed">Completed bookings</a></li>
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
<div class="container">
    <table class="table table-hover">
        <thead>
        <tr>
            <th>Name</th>
            <th>Surname</th>
            <th>School Number</th>
            <th>Places</th>
            <th>Date</th>
        </tr>
        </thead>

        <tbody>
        <%
            try {
                Connection con = Database.getConnection();
                String query = "SELECT users.Name, users.Surname, users.school_num, bookings.places, bookings.date FROM users INNER JOIN usersToBookings ON users.id = usersToBookings.users_id INNER JOIN bookings ON bookings.id = usersToBookings.bookings_id";
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {

        %>
        <tr>
            <td><%=rs.getString("Name") %>
            </td>
            <td><%=rs.getString("Surname") %>
            </td>
            <td><%=rs.getString("school_num") %>
            </td>
            <td><%=rs.getInt("places") %>
            </td>
            <td><%=rs.getDate("date") %>
            </td>
        </tr>
        <%

            }
        %>
        </tbody>
    </table>
    <%
            rs.close();
            stmt.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    %>

</div>
</body>
</html>
