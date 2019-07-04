package ua.nure.shevchenko.provider.filter;

import org.junit.Test;
import ua.nure.shevchenko.provider.constans.Page;
import ua.nure.shevchenko.provider.entity.User;
import ua.nure.shevchenko.provider.service.LoginService;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class LoginFilterTest {

    @Test
    public void init() throws ServletException {
        FilterConfig filterConfig = mock(FilterConfig.class);
        new LoginFilter().init(filterConfig);
    }

    @Test
    public void doFilter() throws IOException, ServletException {
        HttpServletRequest request = mock(HttpServletRequest.class);

        HttpServletResponse response = mock(HttpServletResponse.class);

        FilterChain filterChain = mock(FilterChain.class);

        ServletContext context = mock(ServletContext.class);

        HttpSession currentSession = mock(HttpSession.class);

        LoginService loginService = mock(LoginService.class);

        when(request.getParameter("login")).thenReturn("login");

        when(request.getParameter("pass")).thenReturn("password");

        when(request.getServletContext()).thenReturn(context);

        when(context.getAttribute("loginService")).thenReturn(loginService);

        when(request.getSession()).thenReturn(currentSession);

        when(currentSession.getAttribute("login")).thenReturn("login");

        when(currentSession.getAttribute("password")).thenReturn("passss");

        when(currentSession.getAttribute("page")).thenReturn("page");

        new LoginFilter().doFilter(request,response,filterChain);
    }


    @Test
    public void doFilter2() throws IOException, ServletException {
        HttpServletRequest request = mock(HttpServletRequest.class);

        HttpServletResponse response = mock(HttpServletResponse.class);

        FilterChain filterChain = mock(FilterChain.class);

        ServletContext context = mock(ServletContext.class);

        HttpSession currentSession = mock(HttpSession.class);

        LoginService loginService = mock(LoginService.class);

        when(request.getParameter("login")).thenReturn("login");

        when(request.getParameter("pass")).thenReturn("password");

        when(request.getServletContext()).thenReturn(context);

        when(context.getAttribute("loginService")).thenReturn(loginService);

        when(request.getSession()).thenReturn(currentSession);

        when(currentSession.getAttribute("login")).thenReturn(null);

        when(currentSession.getAttribute("password")).thenReturn(null);

        when(currentSession.getAttribute("page")).thenReturn("page");


        when(loginService.isExist("login","password")).thenReturn(false);




        new LoginFilter().doFilter(request,response,filterChain);
    }


    @Test
    public void doFilter3() throws IOException, ServletException {
        HttpServletRequest request = mock(HttpServletRequest.class);

        HttpServletResponse response = mock(HttpServletResponse.class);

        FilterChain filterChain = mock(FilterChain.class);

        ServletContext context = mock(ServletContext.class);

        HttpSession currentSession = mock(HttpSession.class);

        LoginService loginService = mock(LoginService.class);

        User user = new User();

        when(request.getParameter("login")).thenReturn("login");

        when(request.getParameter("pass")).thenReturn("password");

        when(request.getServletContext()).thenReturn(context);

        when(context.getAttribute("loginService")).thenReturn(loginService);

        when(request.getSession()).thenReturn(currentSession);

        when(currentSession.getAttribute("login")).thenReturn(null);

        when(currentSession.getAttribute("password")).thenReturn(null);

        when(currentSession.getAttribute("page")).thenReturn("page");


        when(loginService.isExist("login","password")).thenReturn(true);

        when(loginService.getUser("login")).thenReturn(user);

        when(loginService.getPage(user)).thenReturn(Page.START_PAGE);


        new LoginFilter().doFilter(request,response,filterChain);
    }



    @Test
    public void destroy() {

        new LoginFilter().destroy();
    }
}