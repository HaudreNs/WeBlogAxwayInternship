<%@ page import="Models.Post" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Map" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create a post</title>
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
<div class="panel panel-default">
    <form method="post">
        <%
            Post post = (Post) request.getAttribute("post");
        %>
        <div class="panel-heading">
            <h2>Create Post</h2>
        </div>
        <div class="panel-body">
            <div class="form-group">
                <label class="control-label col-sm-2" for="title">Tittle</label>
                <div class="col-sm-10">
                    <input type="text" value="<%= post.getTitle()%>" name="title" class="form-control" id="title"
                           placeholder="Enter title">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="post">Post</label>
                <div class="col-sm-10">
                    <textarea rows="5" cols="15" name="body" class="form-control" id="post"
                              placeholder="Enter your drama"><%= post.getBody()%></textarea>
                </div>
            </div>

            <div class="pickVisibility">
                <label class="control-label col-sm-2" for="post">Visibility</label>
                <div class="col-sm-10">
                    <select name="visibility">
                        <option value="1" <%= post.getVisibility() == 1 ? "selected" : "" %>>Public</option>
                        <option value="2" <%= post.getVisibility() == 2 ? "selected" : "" %> >Friends only</option>
                        <option value="3" <%= post.getVisibility() == 3 ? "selected" : "" %>>Private</option>
                    </select>
                </div>
                <% if (request.getAttribute("type") == "create") { %>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <button type="submit" value="create-post" class="btn btn-default">Create Post</button>
                    </div>
                </div>
                <% } else if (request.getAttribute("type") == "update"){ %>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <button type="submit" value="update" class="btn btn-default">Update Post</button>
                    </div>
                </div>
                <%}%>
            </div>
        </div>
    </form>
</div>
</body>
</html>
