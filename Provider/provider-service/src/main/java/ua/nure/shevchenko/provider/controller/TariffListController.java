package ua.nure.shevchenko.provider.controller;

import org.apache.log4j.Logger;
import ua.nure.shevchenko.provider.constans.Messages;
import ua.nure.shevchenko.provider.constans.Page;
import ua.nure.shevchenko.provider.dao.mysqlDao.MySqlTariffDao;
import ua.nure.shevchenko.provider.entity.TariffConnectionInfo;
import ua.nure.shevchenko.provider.entity.Service;
import ua.nure.shevchenko.provider.entity.Tariff;
import ua.nure.shevchenko.provider.entity.User;
import ua.nure.shevchenko.provider.service.TariffService;
import ua.nure.shevchenko.provider.utils.TariffComparator;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;

@WebServlet("/listTar")
public class TariffListController extends HttpServlet {
    private static final Logger log = Logger.getLogger(LoginController.class);
    private Comparator<Tariff> comparator;

    @Override
    public void init() throws ServletException {
        comparator = TariffComparator.getComparatorByService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ServletContext context = req.getServletContext();

        HttpSession session = req.getSession();

        Integer roleAccess = (Integer) session.getAttribute("roleAccess");

        if(!(roleAccess ==null) && roleAccess==2 ) {

            MySqlTariffDao tariffDao = (MySqlTariffDao) context.getAttribute("tariffDao");

            TariffService tariffService = (TariffService) context.getAttribute("tariffService");

            User current = (User) session.getAttribute("user");

            log.trace(Messages.TRACE_FOUND_USER + current);

            List<Service> services = tariffService.getAllServices();

            log.trace(Messages.TRACE_FOUND_SERVICES + services);


            List<Tariff> tariffs = tariffDao.getAllTariffs();

            log.trace(Messages.TRACE_FOUND_TARIFFS + tariffs);


            List<TariffConnectionInfo> infs = tariffService.getAllInfoByUser(current);

            log.trace(Messages.TRACE_FOUND_INFO_TARIFFS + infs);


            tariffs.sort(comparator);

            req.setAttribute("services", services);

            req.setAttribute("tariffs", tariffs);

            req.setAttribute("infs", infs);

            req.getRequestDispatcher(Page.TARIFF_LIST_PAGE).forward(req, resp);
        }else {
            req.getRequestDispatcher(Page.ERROR_PAGE).forward(req, resp);
        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String sort = req.getParameter("sort");

        switch (sort){
            case "pr" : comparator = TariffComparator.getComparatorByPrice();
            break;
            case "az" : comparator = TariffComparator.getComparatorByName();
            break;
            case "za" : comparator = TariffComparator.getComparatorByNameReverse();
            break;
            default: comparator = TariffComparator.getComparatorByPrice();
        }

        doGet(req,resp);

    }


}
