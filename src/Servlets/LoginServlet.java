package Servlets;

import Filters.AuthenticationFilter;
import Models.User;
import Models.UserDAO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet(name = "Servlets.LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    private Pattern pattern;
    private Matcher matcher;
    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       getServletContext().getRequestDispatcher("/WEB-INF/login/login.jsp").forward(request,response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        User loggedUser;

        HttpSession session = request.getSession();
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(request.getParameter("email"));
        if(matcher.matches()) {
            loggedUser = UserDAO.getUser(email, password,true);
        }else{
            loggedUser = UserDAO.getUser(email, password,false);
        }
        if (loggedUser != null) {
            session.setAttribute(AuthenticationFilter.USER_ID_COOKIE, loggedUser.getId());
            response.sendRedirect("/");

        } else {
            PrintWriter out = response.getWriter();
            out.println("<font color=red>Either user name or password is wrong.</font>");
            getServletContext().getRequestDispatcher("/WEB-INF/login/login.jsp").forward(request, response);

        }

    }
}