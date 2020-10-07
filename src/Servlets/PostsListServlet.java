package Servlets;

import Helpers.Database;
import Models.Comment;
import Models.Post;
import Models.User;
import Models.UserFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

@WebServlet(name = "Servlets.PostsListServlet", urlPatterns = {"/drama"})
public class PostsListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            Connection connection = Database.getConnection();

            PreparedStatement ps;
            ResultSet rs;

            Set<Integer> authorIds = new HashSet<>();
            HashMap<Integer, Post> posts = new HashMap<>();

            ps = connection.prepareStatement("Select * From post where visibility = 1");
            resultPostData(posts, ps, authorIds);

            if (request.getSession().getAttribute("id") != null) {
                int userId = (int) request.getSession().getAttribute("id");

                ps = connection.prepareStatement("SELECT * FROM post WHERE visibility=3 AND user_id=" + userId);
                resultPostData(posts, ps, authorIds);

                ps = connection.prepareStatement("SELECT * from post inner join friends on ((post.user_id = friends.from_id AND friends.accepted = 1) or (post.user_id = friends.to_id AND friends.accepted = 1)) AND (from_id=" + userId + " or to_id=" + userId + ") AND post.visibility = 2");
                resultPostData(posts, ps, authorIds);
            }

            ps = connection.prepareStatement("SELECT * FROM comment where post_id IN (" + joinIds(posts.keySet()) + ") ");
            rs = ps.executeQuery();

            while (rs.next()) {
                Comment comment = new Comment(
                        rs.getInt("id"),
                        rs.getString("body"),
                        rs.getDate("date"),
                        rs.getInt("user_id"),
                        rs.getInt("post_id"));

                posts.get(comment.getPostId()).getComments().add(comment);
                authorIds.add(comment.getUserId());
            }

            HashMap<Integer, User> authors = new HashMap<>();

            ps = connection.prepareStatement("select * from user where id in (" + joinIds(authorIds) + ");");
            rs = ps.executeQuery();

            int authorId;

            while (rs.next()) {
                authorId = rs.getInt("id");

                if (!authors.containsKey(authorId)) {
                    authors.put(authorId, UserFactory.createFromResultSet(rs));
                }

            }
            request.setAttribute("posts", posts);
            request.setAttribute("authors", authors);

            getServletContext().getRequestDispatcher("/WEB-INF/posts/lists.jsp").forward(request, response);

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void resultPostData(HashMap<Integer, Post> posts, PreparedStatement ps, Set<Integer> authorIds) throws SQLException {
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Post post = new Post(
                    rs.getInt("id"),
                    rs.getString("title"),
                    rs.getString("body"),
                    rs.getDate("date"),
                    rs.getInt("visibility"),
                    rs.getInt("user_id")
            );

            authorIds.add(post.getUserID());
            posts.put(post.getId(), post);

        }
    }

    private String joinIds(Set<Integer> ids) {
        ArrayList<String> idsInString = new ArrayList<>();

        for (Integer id : ids) {
            idsInString.add(String.valueOf(id));
        }

        return String.join(",", idsInString);
    }
}
