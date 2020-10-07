package Servlets;

import Helpers.Database;
import Models.Post;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

@WebServlet(name = "Servlets.CreatePostServlet", urlPatterns = {"/drama/create"})
public class CreatePostServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setAttribute("type", "create");
        request.setAttribute("post", new Post(0,"", "",new Date(0),1,0));
        request.getRequestDispatcher("/WEB-INF/posts/create.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title = request.getParameter("title");
        String body = request.getParameter("body");

        int visibility = Integer.parseInt(request.getParameter("visibility"));
        int userId = (Integer) request.getSession().getAttribute("id");

        try (
                Connection con = Database.getConnection();
                PreparedStatement ps = con.prepareStatement(
                        "INSERT INTO post (title, body, visibility, user_id)  VALUES(?,?,?,?)", Statement.RETURN_GENERATED_KEYS)
        ) {
            int i = 0;

            ps.setString(++i, title);
            ps.setString(++i, body);
            ps.setInt(++i, visibility);
            ps.setInt(++i, userId);

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
