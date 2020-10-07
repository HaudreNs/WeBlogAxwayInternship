package Filters;

import Models.User;
import Models.UserDAO;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebFilter("/AuthenticationFilter")
public class AuthenticationFilter implements Filter {
    public static final String USER_ID_COOKIE = "id";

    private ArrayList<String> allowedGuestUris = new ArrayList<>();

    public void init(FilterConfig fConfig){
        allowedGuestUris.add("/");
        allowedGuestUris.add("/login");
        allowedGuestUris.add("/register");
        allowedGuestUris.add("/drama");
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String uri = req.getRequestURI();
        HttpSession session = req.getSession(false);
        User user = null;

        if(session != null && session.getAttribute(USER_ID_COOKIE) !=null) {
            user = UserDAO.getUser(Integer.parseInt(session.getAttribute(USER_ID_COOKIE).toString()));
        }

        if (user == null && !allowedGuestUris.contains(uri)) {
            res.sendRedirect("/");
        } else {
            request.setAttribute("user", user);
            chain.doFilter(request, response);
        }
    }


    public void destroy() {
    }
}