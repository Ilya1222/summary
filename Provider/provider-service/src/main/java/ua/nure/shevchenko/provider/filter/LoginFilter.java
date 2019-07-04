package ua.nure.shevchenko.provider.filter;

import ua.nure.shevchenko.provider.constans.Page;
import ua.nure.shevchenko.provider.entity.User;
import ua.nure.shevchenko.provider.service.LoginService;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static java.util.Objects.nonNull;

public class LoginFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession currentSession = request.getSession();

        final String login = request.getParameter("login");
        final String password = request.getParameter("pass");

        ServletContext context = request.getServletContext();

        LoginService loginService = (LoginService) context.getAttribute("loginService");

        User user;


        if (nonNull(currentSession) && nonNull(currentSession.getAttribute("login")) && nonNull(currentSession.getAttribute("password")) && nonNull(currentSession.getAttribute("page"))) {
            filterChain.doFilter(request, response);

        } else if (!loginService.isExist(login, password) || login==null || password==null) {

            currentSession.setAttribute("page", Page.START_PAGE);
            filterChain.doFilter(request, response);

        }

        else {

            currentSession.setAttribute("login", login);

            currentSession.setAttribute("password", password);

            user = loginService.getUser(login);

            currentSession.setAttribute("role",user.getRoleId());

            currentSession.setAttribute("user", user);

            currentSession.setAttribute("roleAccess",user.getRoleId());

            currentSession.setAttribute("blockAccess",false);

            String page = loginService.getPage(user);

            currentSession.setAttribute("page",page);

            filterChain.doFilter(request, response);

        }


    }

    @Override
    public void destroy() {

    }
}
