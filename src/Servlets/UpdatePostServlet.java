package Servlets;

import Helpers.Database;
import Models.PostDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

@WebServlet(name = "Servlets.UpdatePostServlet", urlPatterns = {"/drama/update"})
public class UpdatePostServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int postId = Integer.parseInt(request.getParameter("id"));

        request.setAttribute("post", PostDAO.getPost(postId));
        request.setAttribute("type", "update");
        request.getRequestDispatcher("/WEB-INF/posts/create.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title = request.getParameter("title");
        String body = request.getParameter("body");

        int postId = Integer.parseInt(request.getParameter("id"));
        int visibility = Integer.parseInt(request.getParameter("visibility"));

        try (
                Connection con = Database.getConnection();
                PreparedStatement ps = con.prepareStatement(
                        "UPDATE post set title=?, body=?, visibility=? where id=?", Statement.RETURN_GENERATED_KEYS)
        ) {
            int i = 0;

            ps.setString(++i, title);
            ps.setString(++i, body);
            ps.setInt(++i, visibility);
            ps.setInt(++i, postId);

            if (ps.executeUpdate() == 1) {
                response.sendRedirect("/drama");
            }
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }
}
