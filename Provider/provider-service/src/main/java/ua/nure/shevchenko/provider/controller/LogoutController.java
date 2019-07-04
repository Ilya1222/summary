package ua.nure.shevchenko.provider.controller;

import org.apache.log4j.Logger;
import ua.nure.shevchenko.provider.constans.Messages;
import ua.nure.shevchenko.provider.constans.Page;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/logout")
public class LogoutController extends HttpServlet {
    private static final Logger log = Logger.getLogger(LoginController.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();

        Integer roleAccess = (Integer) session.getAttribute("roleAccess");

        if(!(roleAccess ==null)) {


            session.removeAttribute("login");

            log.trace(Messages.TRACE_SESSION_REMOVE_LOGIN);

            session.removeAttribute("password");

            log.trace(Messages.TRACE_SESSION_REMOVE_PASSWORD);

            session.removeAttribute("page");

            log.trace(Messages.TRACE_SESSION_REMOVE_PAGE);

            session.removeAttribute("user");

            log.trace(Messages.TRACE_SESSION_REMOVE_USER);

            session.removeAttribute("score");

            log.trace(Messages.TRACE_SESSION_REMOVE_SCORE);

            session.removeAttribute("roleAccess");

            session.removeAttribute("blockAccess");

            req.getRequestDispatcher(Page.START_PAGE).forward(req, resp);
        }
        else{
            req.getRequestDispatcher(Page.ERROR_PAGE).forward(req, resp);
        }
    }
}
