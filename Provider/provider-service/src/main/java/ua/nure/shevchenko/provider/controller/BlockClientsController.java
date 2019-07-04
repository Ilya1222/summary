package ua.nure.shevchenko.provider.controller;

import org.apache.log4j.Logger;
import ua.nure.shevchenko.provider.constans.Messages;
import ua.nure.shevchenko.provider.constans.Page;
import ua.nure.shevchenko.provider.entity.TariffConnectionInfo;
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

@WebServlet("/block")
public class BlockClientsController extends HttpServlet {

    private static final Logger log = Logger.getLogger(LoginController.class);


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();

        Integer roleAccess = (Integer) session.getAttribute("roleAccess");

        if(!(roleAccess ==null) && roleAccess==1 ) {

            ServletContext context = req.getServletContext();

            InfoTariffService infoTariffService = (InfoTariffService) context.getAttribute("infoTariffService");

            List<TariffConnectionInfo> infoDebt = infoTariffService.findAllDebt();

            log.trace(Messages.TRACE_FOUND_DEBT_TARIFFS + infoDebt);

            req.getSession().setAttribute("infoDebt", infoDebt);

            log.trace(Messages.TRACE_SESSION_ATTRIBUTE_DEBT_INFO + infoDebt);

            req.getRequestDispatcher(Page.BLOCKING_USERS_PAGE).forward(req, resp);
        }

        else{
            req.getRequestDispatcher(Page.ERROR_PAGE).forward(req, resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ServletContext context = req.getServletContext();

        HttpSession session = req.getSession();

        InfoTariffService infoService;

        infoService = (InfoTariffService) context.getAttribute("infoTariffService");

        @SuppressWarnings("unchecked")
        List<TariffConnectionInfo>  usersDebt =  (List<TariffConnectionInfo>) session.getAttribute("infoDebt");

        infoService.autoReUpdate(usersDebt);

        doGet(req,resp);

    }

}
