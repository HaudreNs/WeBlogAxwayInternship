package Servlets;

import Helpers.Database;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@WebServlet(name = "Servlets.ProfilePageServlet", urlPatterns = {"/profilePage"})
public class ProfilePageServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        try {
            Database.openConnection();
            Connection connection = null;
            Statement statement = null;
            ResultSet resultSet = null;

            connection = Database.getConnection();
            statement = connection.createStatement();
            int userID = (Integer) session.getAttribute("id");
            String sql = "select * from user where id=" + userID;
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                resultSet.getString("id");
                resultSet.getString("fullName");
                resultSet.getString("age");
                resultSet.getString("town");
                resultSet.getString("motto");
            }
            connection.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
