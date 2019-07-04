package ua.nure.shevchenko.provider.filter;

import org.junit.Test;
import ua.nure.shevchenko.provider.service.LoginService;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RegistrationFilterTest {

    @Test
    public void init() throws ServletException {
        FilterConfig filterConfig = mock(FilterConfig.class);

        new RegistrationFilter().init(filterConfig);
    }

    @Test
    public void doFilter() throws IOException, ServletException {

        HttpServletRequest request = mock(HttpServletRequest.class);

        HttpServletResponse response = mock(HttpServletResponse.class);

        FilterChain filterChain = mock(FilterChain.class);

        ServletContext context = mock(ServletContext.class);


        LoginService loginService = mock(LoginService.class);



        when(request.getServletContext()).thenReturn(context);

        when(context.getAttribute("loginService")).thenReturn(loginService);

        when(request.getParameter("login")).thenReturn("login");

        when(request.getParameter("pass")).thenReturn("password");


        when(loginService.isExist("login","password")).thenReturn(true);

        new RegistrationFilter().doFilter(request,response,filterChain);

    }

    @Test
    public void doFilter2() throws IOException, ServletException {

        HttpServletRequest request = mock(HttpServletRequest.class);

        HttpServletResponse response = mock(HttpServletResponse.class);

        FilterChain filterChain = mock(FilterChain.class);

        ServletContext context = mock(ServletContext.class);


        LoginService loginService = mock(LoginService.class);



        when(request.getServletContext()).thenReturn(context);

        when(context.getAttribute("loginService")).thenReturn(loginService);

        when(request.getParameter("login")).thenReturn("login12");

        when(request.getParameter("pass")).thenReturn("password");


        when(loginService.isExist("login","password")).thenReturn(false);

        when(request.getParameter("name")).thenReturn("Name");

        when(request.getParameter("sur")).thenReturn("Namee");

        when(request.getParameter("last")).thenReturn("Last");

        when(request.getParameter("email")).thenReturn("email@gmail.com");

        when( request.getParameter("numb")).thenReturn("0964959608");

        new RegistrationFilter().doFilter(request,response,filterChain);

    }

    @Test
    public void doFilter3() throws IOException, ServletException {

        HttpServletRequest request = mock(HttpServletRequest.class);

        HttpServletResponse response = mock(HttpServletResponse.class);

        FilterChain filterChain = mock(FilterChain.class);

        ServletContext context = mock(ServletContext.class);


        LoginService loginService = mock(LoginService.class);



        when(request.getServletContext()).thenReturn(context);

        when(context.getAttribute("loginService")).thenReturn(loginService);

        when(request.getParameter("login")).thenReturn("login12");

        when(request.getParameter("pass")).thenReturn("password");


        when(loginService.isExist("login","password")).thenReturn(false);

        when(request.getParameter("name")).thenReturn("3wrame");

        when(request.getParameter("sur")).thenReturn("Named44e");

        when(request.getParameter("last")).thenReturn("4");

        when(request.getParameter("email")).thenReturn("email@gmail.com");

        when( request.getParameter("numb")).thenReturn("0964959608");

        new RegistrationFilter().doFilter(request,response,filterChain);

    }

    @Test
    public void destroy() {
        new RegistrationFilter().destroy();
    }
}