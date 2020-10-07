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

@WebServlet(urlPatterns = {"/friends/confirm"})
public class ConfirmFriendRequestServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Connection connection = Database.getConnection();

            PreparedStatement ps = connection.prepareStatement("UPDATE friends SET accepted = 1 WHERE from_id = ? AND to_id = ?");

            int fromId = Integer.parseInt(request.getParameter("user_id"));
            int currentUserId = (Integer) request.getSession().getAttribute("id");

            ps.setInt(1, fromId);
            ps.setInt(2, currentUserId);

            int updatedRows = ps.executeUpdate();

            if ( updatedRows == 1 ) {
                response.sendRedirect("/friends");
            } else {
                PrintWriter out = response.getWriter();
                out.println("<font color=red>Error while adding friend.</font>");
                response.sendRedirect("/friends");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
