package ua.nure.shevchenko.provider.controller;

import org.apache.log4j.Logger;
import ua.nure.shevchenko.provider.constans.Messages;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/chLock")
public class ChangeLocaleController extends HttpServlet {

    private static final Logger log = Logger.getLogger(LoginController.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String locale = req.getParameter("locale");

        req.getSession().setAttribute("locale",locale);

        log.trace(Messages.TRACE_SESSION_ATTRIBUTE_LOCALE+locale);

        resp.sendRedirect("menu");
    }

}
