package ua.nure.shevchenko.provider.filter;

import org.apache.log4j.Logger;
import ua.nure.shevchenko.provider.controller.LoginController;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CharsetFilter implements Filter {

    private static final Logger log = Logger.getLogger(LoginController.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {


        HttpServletRequest request = (HttpServletRequest) servletRequest;

        HttpServletResponse response = (HttpServletResponse) servletResponse;


        request.setCharacterEncoding("UTF-8");


        filterChain.doFilter(request,response);

    }

    @Override
    public void destroy() {

    }
}
