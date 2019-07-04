package ua.nure.shevchenko.provider.controller;

import org.apache.log4j.Logger;
import ua.nure.shevchenko.provider.constans.Messages;
import ua.nure.shevchenko.provider.constans.Page;
import ua.nure.shevchenko.provider.entity.TariffConnectionInfo;
import ua.nure.shevchenko.provider.entity.User;
import ua.nure.shevchenko.provider.service.InfoTariffService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/blocked")
public class BlockedUserController extends HttpServlet {


    private static final Logger log = Logger.getLogger(LoginController.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();

        ServletContext context = req.getServletContext();

        Boolean blockAccess = (Boolean) session.getAttribute("blockAccess");

        if(!(blockAccess ==null) && blockAccess) {

            InfoTariffService infoService = (InfoTariffService) context.getAttribute("infoTariffService");

            User user = (User) session.getAttribute("user");

            List<TariffConnectionInfo> list = infoService.findAllUserTariffs(user);

            list = infoService.findDebtTariff(list);

            log.trace(Messages.TRACE_FOUND_DEBT_TARIFFS + list);

            session.setAttribute("debtUserInfo", list);

            log.trace(Messages.TRACE_SESSION_ATTRIBUTE_DEBT_TARIFFS + list);

            req.getRequestDispatcher(Page.CLIENT_BLOCK_PAGE).forward(req, resp);
        }
        else{
            req.getRequestDispatcher(Page.ERROR_PAGE).forward(req, resp);
        }

    }


}
