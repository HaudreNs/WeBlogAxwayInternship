package Servlets;

import Helpers.Database;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet(name = "Servlets.PostServlet", urlPatterns = {"/post"})
public class PostServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher rd = req.getRequestDispatcher("post.jsp");
        rd.forward(req, resp);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            response.setContentType("text/html");
            PrintWriter out = response.getWriter();

            String t = request.getParameter("title");
            String b = request.getParameter("body");
            String d = request.getParameter("date");
            int v = Integer.parseInt(request.getParameter("visibility"));
            int ui = Integer.parseInt(request.getParameter("userId"));


            try (
                    Connection con = Database.getConnection();
                    PreparedStatement ps = con.prepareStatement(
                            "INSERT INTO post (title, body, date, visibility, user_id)  VALUES(?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS)) {

                ps.setString(1, t);
                ps.setString(2, b);
                ps.setDate(3, Date.valueOf(d));
                ps.setInt(4, v);
                ps.setInt(5, ui);

                ps.executeUpdate();

                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    int userId = rs.getInt(1);

//                    request.getSession().setAttribute("id", userId);
                    response.sendRedirect("/");
                }
            }
        } catch (ClassNotFoundException | SQLException e1) {
            e1.printStackTrace();
        }
    }
}