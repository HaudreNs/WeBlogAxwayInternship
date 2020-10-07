<%@ page import="Helpers.Database" %>
<%@ page import="Models.FriendsList" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Friend Panel</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

    <!-- jQuery library -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

    <!-- Latest compiled JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</head>
<body>
<nav class="navbar">
    <form action="/WEB-INF/friends/friendPage.jsp" method="post">
        <div class="container-fluid" style="border-bottom-style: groove;">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="myNavbar">
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="\">WeBlog</a>
            </div>
            <div class="collapse navbar-collapse" id="myNavbar">
                <ul class="nav navbar-nav">
                    <% if (null != session.getAttribute("isLogged")) { %>
                    <li><a href="admin">Admin</a></li>
                    <% } %>

                </ul>
                <ul class="nav navbar-nav navbar-right">

                    <% if (null == session.getAttribute("isLogged")) { %>
                    <li><a href="logout"><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
                    <% } else { %>
                    <li><a href="../register/register.jsp"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>
                    <li><a href="login"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
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
            <th>Username</th>
        </tr>
        </thead>

        <tbody>
        <%
            try {
                Connection con = Database.getConnection();

                String query = "SELECT id, fullName, username FROM user WHERE id != ?";

                PreparedStatement stmt = con.prepareStatement(query);
                stmt.setInt(1, (Integer) session.getAttribute("id"));
                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {

        %>
        <tr>
            <td><%=rs.getString("fullName") %>
            </td>
            <td><%=rs.getString("username") %>
            </td>
            <td>
                <%
                    FriendsList friends = (FriendsList) request.getAttribute("friends");
                    int userId = rs.getInt("id");

                    if(friends.isFriend(userId)) {
                %>
                <form action="friends/remove" method="post">
                    <input type="submit" value="Remove From Friends">
                    <input type="hidden" name="user_id" value="<%= userId %>">
                </form>
                <%
                    } else if (friends.hasSentMeUnaccepted(userId)) {
                %>
                <form action="friends/confirm" method="post">
                    <input type="submit" value="Confirm Friend Request">
                    <input type="hidden" name="user_id" value="<%= userId %>">
                </form>
                <form action="friends/remove" method="post">
                    <input type="submit" value="Cancel Friend Request">
                    <input type="hidden" name="user_id" value="<%= userId %>">
                </form>
                <%
                    } else if (friends.hasReceivedFromMeUnaccapted(userId)) {
                %>
                <form action="friends/remove" method="post">
                    <input type="submit" value="Cancel Friend Request">
                    <input type="hidden" name="user_id" value="<%= userId %>">
                </form>

                <%
                    } else if (!friends.isFriend(userId)) {
                %>
                <form action="friends/send" method="post">
                    <input type="submit" value="Send friend request">
                    <input type="hidden" name="user_id" value="<%= userId %>">
                </form>
                <%
                }

                %>

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
