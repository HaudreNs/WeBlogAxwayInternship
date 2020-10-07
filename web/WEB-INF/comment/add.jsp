<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Map" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add a comment</title>
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
    <form action="/add-comment" method="post">

        <div class="panel-heading">
            <h2>Add comment</h2>
        </div>
        <div class="panel-body">
            <form class="form-horizontal">
                <div class="form-group">
                    <label class="control-label col-sm-2" for="post">Comment</label>
                    <div class="col-sm-10">
                        <textarea rows = "5" cols="15" name="body" class="form-control" id="post" placeholder="Enter Post"></textarea>
                    </div>
                </div>
                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">
                            <button type="submit" value="addComment" class="btn btn-default">Add</button>
                            <input type="hidden" name="post_id" value="<%= request.getParameter("post_id")%>">
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </form>
</div>
</body>
</html>