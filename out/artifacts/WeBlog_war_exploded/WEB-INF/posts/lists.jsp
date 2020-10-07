<%@ page import="Models.Comment" %>
<%@ page import="Models.Post" %>
<%@ page import="Models.User" %>
<%@ page import="java.util.HashMap" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Drama</title>
</head>
<body>
<h1>People's Drama</h1>
<a href="drama/create">Share your drama</a>
<%
    HashMap<Integer, User> authors = (HashMap<Integer, User>) request.getAttribute("authors");

    for (Post post : ((HashMap<Integer, Post>) request.getAttribute("posts")).values()) { %>
<article class="post" style="border-style: solid; border-color: aqua">
    <h2><%= post.getTitle() %>
    </h2>
    <%
        if (post.getUserID() == (Integer) session.getAttribute("id")) {
    %>
    <a href="/drama/update?id=<%= post.getId()%>">Edit</a>
    <%
    }
    %>
    <p class="post-meta">Drama by <%= authors.get(post.getUserID()).getFullName() %>
    </p>
    <p>
        <%= post.getBody() %>
    </p>
    <p class="small"><%= post.getDate() %>
    </p>

    <h3>Comments</h3>
    <a href="add-comment?post_id=<%= post.getId()%>">Add comment</a>

    <% for (Comment comment : post.getComments()) { %>
    <div class="comment" style="border-style: solid; border-color: red">
        <h4><%= authors.get(comment.getUserId()).getFullName() %> also related to the drama</h4>
        <p><%= comment.getBody() %>
        </p>
        <p class="date"><%= comment.getDate() %>
        </p>
    </div>
    <% } %>

</article>
<% } %>

</body>
</html>