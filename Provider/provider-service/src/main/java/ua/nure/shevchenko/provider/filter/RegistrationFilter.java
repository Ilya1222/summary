package ua.nure.shevchenko.provider.filter;

import ua.nure.shevchenko.provider.service.LoginService;
import ua.nure.shevchenko.provider.utils.Validator;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegistrationFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        ServletContext context = request.getServletContext();

        LoginService loginService = (LoginService) context.getAttribute("loginService");

        if(loginService.isExist(request.getParameter("login"),request.getParameter("pass"))){

            request.setAttribute("validate",false);
            request.setAttribute("errorValid","!!!!!!!!");
        }
       else if(Validator.isNotNull(request.getParameter("login"),
               request.getParameter("pass"),
               request.getParameter("name"),
               request.getParameter("sur"),
               request.getParameter("last"),
               request.getParameter("email"),
               request.getParameter("numb")) &&
               Validator.validateUserParameters(request.getParameter("login"),
               request.getParameter("pass"),
               request.getParameter("name"),
               request.getParameter("sur"),
               request.getParameter("last"),
               request.getParameter("email"),
               request.getParameter("numb"))){

           request.setAttribute("validate",true);




       }else {
            request.setAttribute("errorValid","!!!!!!!!");
           request.setAttribute("validate",false);
       }

        filterChain.doFilter(request,response);

    }

    @Override
    public void destroy() {

    }
}
