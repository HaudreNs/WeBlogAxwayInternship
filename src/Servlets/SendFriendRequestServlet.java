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

@WebServlet(name = "Servlets.SendFriendRequestServlet", urlPatterns = {"/friends/send"})
public class SendFriendRequestServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int toId = Integer.parseInt(request.getParameter("user_id"));

        try {

            Connection conn = Database.getConnection();

            PreparedStatement ps = conn.prepareStatement("INSERT into friends (from_id, to_id, accepted) VALUES (?,?,0)");

            ps.setInt(1, (Integer) request.getSession().getAttribute("id"));
            ps.setInt(2, toId);

            int updatedRows = ps.executeUpdate();

            if (updatedRows == 1) {
                response.sendRedirect("/friends");
            } else {
                PrintWriter out = response.getWriter();
                out.println("<font color=red>Error while sending friend request.</font>");
                response.sendRedirect("/friends");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
