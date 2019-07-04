package ua.nure.shevchenko.provider.controller;
import org.apache.log4j.Logger;
import ua.nure.shevchenko.provider.constans.Messages;
import ua.nure.shevchenko.provider.constans.Page;
import ua.nure.shevchenko.provider.entity.TariffConnectionInfo;
import ua.nure.shevchenko.provider.entity.Score;
import ua.nure.shevchenko.provider.entity.User;
import ua.nure.shevchenko.provider.service.ClientService;
import ua.nure.shevchenko.provider.service.InfoTariffService;
import ua.nure.shevchenko.provider.service.LoginService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;


@WebServlet("/menu")
public class LoginController extends HttpServlet {
    private static final Logger log = Logger.getLogger(LoginController.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();

        Integer roleAccess = (Integer) session.getAttribute("roleAccess");

        String page = (String) session.getAttribute("page");

        log.trace(Messages.TRACE_SESSION_ATTRIBUTE_PAGE + page);

        if(isNull(session.getAttribute("user"))){
            req.getRequestDispatcher(page).forward(req, resp);
        }
        else if(!(roleAccess ==null)){

                User user = (User) session.getAttribute("user");
                installUser(req, resp, user);
                log.trace(Messages.TRACE_SESSION_ATTRIBUTE_USER + user);
                req.getRequestDispatcher(page).forward(req, resp);
        }
        else {
            req.getRequestDispatcher(Page.ERROR_PAGE).forward(req,resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ServletContext context = req.getServletContext();

        HttpSession currentSession = req.getSession();

        InfoTariffService infoTariffService = (InfoTariffService) context.getAttribute("infoTariffService");

        LoginService loginService = (LoginService) context.getAttribute("loginService");

        String page = (String) currentSession.getAttribute("page");

        if (nonNull(currentSession.getAttribute("login")) && nonNull(currentSession.getAttribute("password"))) {

            User user;

            user = (User) currentSession.getAttribute("user");

            log.trace(Messages.TRACE_FOUND_USER + user);

            installUser(req, resp, user);

            user = loginService.getUser(user.getLogin());

            currentSession.removeAttribute("notValidAut");

            currentSession.setAttribute("user", user);

            log.trace(Messages.TRACE_SESSION_ATTRIBUTE_USER + user);

            currentSession.setAttribute("blockUser", false);

            log.trace(Messages.TRACE_SESSION_ATTRIBUTE_BLOCK + false);


            if (user.isBlocked()) {

                log.trace(Messages.TRACE_BLOCK_USER + user);

                page = Page.CLIENT_BLOCK_PAGE;

                currentSession.setAttribute("page", page);

                currentSession.setAttribute("blockAccess",true);

                log.trace(Messages.TRACE_SESSION_ATTRIBUTE_PAGE + page);

                List<TariffConnectionInfo> list = infoTariffService.findDebtTariff(user);

                log.trace(Messages.TRACE_FOUND_INFO_LIST + list);

                currentSession.setAttribute("debtUserInfo", list);

                currentSession.setAttribute("autoReUP", true);

                log.trace(Messages.TRACE_SESSION_ATTRIBUTE_AUTO_RE_UP + true);

                currentSession.setAttribute("login", null);
                currentSession.setAttribute("password", null);
            }

            req.getRequestDispatcher(page).forward(req, resp);
        } else {
            req.getRequestDispatcher(Page.START_PAGE).forward(req, resp);
        }
    }

    private void installUser(HttpServletRequest req, HttpServletResponse resp, User user) {

        ServletContext context = req.getServletContext();

        HttpSession session = req.getSession();

        ClientService clientService = (ClientService) context.getAttribute("clientService");

        InfoTariffService infoTariffService = (InfoTariffService) context.getAttribute("infoTariffService");

        String fullName = user.getFirstName() + " " + user.getSurName();

        session.setAttribute("fullName", fullName);

        log.trace(Messages.TRACE_SESSION_ATTRIBUTE_FULL_NAME + fullName);

        if (user.getRoleId() > 1) {

            int scoreId = user.getScoreId();


            List<TariffConnectionInfo> infoTariffs;

            List<TariffConnectionInfo> debtTariffs;


            debtTariffs = infoTariffService.findDebtTariff(user);

            log.trace(Messages.TRACE_FOUND_DEBT_TARIFFS + debtTariffs);

            if (debtTariffs.size() > 0) {
                infoTariffService.autoReUpdate(debtTariffs);
            }

            infoTariffs = infoTariffService.findAllUserTariffs(user);

            log.trace(Messages.TRACE_FOUND_INFO_TARIFFS + infoTariffs);

            Score score = clientService.getScoreById(scoreId);

            log.trace(Messages.TRACE_FOUND_SCORE + score);

            session.setAttribute("infoTariffs", infoTariffs);

            log.trace(Messages.TRACE_SESSION_ATTRIBUTE_INFO_TARIFFS + infoTariffs);

            session.setAttribute("scoreBalance", score.getBalance());

            session.setAttribute("score", score);

            log.trace(Messages.TRACE_SESSION_ATTRIBUTE_SCORE + score);

        } else {

            List<TariffConnectionInfo> infoList = infoTariffService.findAll();

            session.setAttribute("infoList", infoList);

            log.trace(Messages.TRACE_SESSION_ATTRIBUTE_INFO_LIST + infoList);

        }
    }

}

