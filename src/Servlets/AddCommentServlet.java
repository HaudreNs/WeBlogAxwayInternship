package Servlets;

import Helpers.Database;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

@WebServlet(name = "Servlets.AddCommentServlet", urlPatterns = {"/add-comment"})
public class AddCommentServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String postId = request.getParameter("post_id");
        request.getRequestDispatcher("WEB-INF/comment/add.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String body = request.getParameter("body");
        int postId = Integer.parseInt(request.getParameter("post_id"));
        int userId = (Integer) request.getSession().getAttribute("id");

        try (
                Connection con = Database.getConnection();
                PreparedStatement ps = con.prepareStatement(
                        "INSERT INTO comment (body, user_id, post_id)  VALUES(?,?,?)", Statement.RETURN_GENERATED_KEYS)
        ) {
            int i = 0;

            ps.setString(++i, body);
            ps.setInt(++i, userId);
            ps.setInt(++i, postId);

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                response.sendRedirect("/drama");
            }
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }
}
