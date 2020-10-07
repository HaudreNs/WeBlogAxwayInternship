package Servlets;

import Helpers.Database;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet(urlPatterns = {"/friends/remove"})
public class DeleteFriendRequestServlet  extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Connection connection = Database.getConnection();

            PreparedStatement ps = connection.prepareStatement("DELETE FROM friends WHERE (from_id = ? AND to_id = ?) OR (from_id = ? AND to_id = ?)");

            int anotherUserId = Integer.parseInt(request.getParameter("user_id"));
            int currentUserId = (int) request.getSession().getAttribute("id");

            ps.setInt(1, anotherUserId);
            ps.setInt(2, currentUserId);
            ps.setInt(3, currentUserId);
            ps.setInt(4, anotherUserId);

            int updatedRows = ps.executeUpdate();

            if ( updatedRows == 1 ) {
                response.sendRedirect("/friends");
            } else {
                PrintWriter out = response.getWriter();
                out.println("<font color=red>Error while removing friend.</font>");
                response.sendRedirect("/friends");
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
