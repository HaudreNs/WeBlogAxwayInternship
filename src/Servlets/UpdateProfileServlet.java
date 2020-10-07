package Servlets;

import Helpers.Database;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet(name = "Servlets.ProfilePageServlet", urlPatterns = {"/update-profile"})
public class UpdateProfileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/profile/updateProfile.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Database.openConnection();

            String id = request.getParameter("id");
            String fullName = request.getParameter("fullName");
            String age = request.getParameter("age");
            String town = request.getParameter("town");
            String motto = request.getParameter("motto");
            if (id != null) {
                Connection con = null;
                PreparedStatement ps = null;
                try {
                    con = Database.getConnection();
                    String sql = "Update user set id=?,fullName=?,age=?,town=?,motto=? where id=" + id;
                    ps = con.prepareStatement(sql);
                    ps.setString(1, id);
                    ps.setString(2, fullName);
                    ps.setString(3, age);
                    ps.setString(4, town);
                    ps.setString(5, motto);
                    int i = ps.executeUpdate();
                    if (i > 0) {
                        response.sendRedirect("/update-profile?id=" + id);
                    } else {
                        System.out.print("There is a problem in updating Record.");
                    }
                } catch (SQLException | ClassNotFoundException sql) {
                    request.setAttribute("error", sql);
                    System.out.println(sql);
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
