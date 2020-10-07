package Servlets;

import Helpers.Database;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@WebServlet(name = "Servlets.RegisterServlet", urlPatterns = {"/register"})
public class RegisterServlet extends HttpServlet {
    private Pattern pattern;
    private Matcher matcher;
    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static final String PASSWORD_PATTERN = "[A-Za-z0-9]{6,}";

    private void validateFields(HttpServletRequest request) {
        HashMap<String, String> errors = new HashMap<>();

        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(request.getParameter("email"));

//        pattern2 = Pattern.compile(PASSWORD_PATTERN);
//        matcher2 = pattern.matcher(request.getParameter("password"));

        if (!matcher.matches()) {
            errors.put("email", "The email format is incorrect");
        }
//
//        if (!matcher2.matches()) {
//            errors.put("password", "The password format is incorrect");
//        }


        if (request.getParameter("fullName").length() == 0
                || request.getParameter("username").length() == 0
                || request.getParameter("password").length() == 0
                || request.getParameter("email").length() == 0) {
            errors.put("fullName", "Every field is required");
            errors.put("username", "Every field is required");
            errors.put("password", "Every field is required");
            errors.put("email", "Every field is required");
        } else {
            if (request.getParameter("username").length() < 4) {
                errors.put("username", "Too short username!");
            }
            if (request.getParameter("password").length() < 6) {
                errors.put("password", "Password should be at least 6 symbols");
            }
            if (request.getParameter("fullName").length() < 5) {
                errors.put("fullName", "Enter your full name!");
            }
        }

        try (
                Connection connection = Database.getConnection();
                PreparedStatement stmt = connection.prepareStatement("SELECT COUNT(id) FROM user WHERE email=? OR username=?")
        ) {
            stmt.setString(1, request.getParameter("email"));
            stmt.setString(2, request.getParameter("username"));
            ResultSet res = stmt.executeQuery();
            res.next();

            if (res.getInt(1) > 0) {
                errors.put("other", "Email or username are in use.");
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/register/register.jsp").forward(request, response);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            validateFields(request);

            response.setContentType("text/html");
            PrintWriter out = response.getWriter();

            String fn = request.getParameter("fullName");
            String un = request.getParameter("username");
            String p = request.getParameter("password");
            String e = request.getParameter("email");
            String ht = request.getParameter("homeTown");
            int a = Integer.parseInt(request.getParameter("age"));
            String m = request.getParameter("motto");

            try (
                    Connection con = Database.getConnection();
                    PreparedStatement ps = con.prepareStatement(
                            "INSERT INTO user (username, email, password, fullName, age, town, motto)  VALUES(?,?,MD5(?),?,?,?,?)", Statement.RETURN_GENERATED_KEYS)
            ) {

                ps.setString(1, un);
                ps.setString(2, e);
                ps.setString(3, p);
                ps.setString(4, fn);
                ps.setInt(5, a);
                ps.setString(6, ht);
                ps.setString(7, m);


                ps.executeUpdate();

                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    int userId = rs.getInt(1);

                    request.getSession().setAttribute("id", userId);
                    response.sendRedirect("/");
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }

    }

    public Pattern getPattern() {
        return pattern;
    }

    public Matcher getMatcher() {
        return matcher;
    }
}
