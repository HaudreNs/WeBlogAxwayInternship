package Servlets;

import Helpers.Database;
import Models.Friend;
import Models.FriendsList;
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

@WebServlet(name = "Servlets.FriendsServlet", urlPatterns = {"/friends"})
public class FriendsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            Connection connection = Database.getConnection();

            PreparedStatement ps = connection.prepareStatement("Select * From friends where from_id = ? OR to_id = ?");

            int currentUserId = (Integer) request.getSession().getAttribute("id");

            ps.setInt(1, currentUserId);
            ps.setInt(2, currentUserId);

            FriendsList friends = new FriendsList();

            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                Friend friend = new Friend(
                        rs.getInt("id"),
                        rs.getInt("from_id"),
                        rs.getInt("to_id"),
                        rs.getBoolean("accepted"),
                        rs.getDate("created_at")
                );

                friends.add(friend);
            }
            request.setAttribute("friends", friends);
            getServletContext().getRequestDispatcher("/WEB-INF/friends/friendPage.jsp").forward(request, response);

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
