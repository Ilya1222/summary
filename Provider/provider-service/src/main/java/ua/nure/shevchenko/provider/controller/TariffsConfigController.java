package ua.nure.shevchenko.provider.controller;

import org.apache.log4j.Logger;
import ua.nure.shevchenko.provider.constans.Messages;
import ua.nure.shevchenko.provider.constans.Page;
import ua.nure.shevchenko.provider.dao.mysqlDao.MySqlTariffDao;
import ua.nure.shevchenko.provider.entity.Service;
import ua.nure.shevchenko.provider.entity.Tariff;
import ua.nure.shevchenko.provider.service.TariffService;
import ua.nure.shevchenko.provider.utils.Validation;
import ua.nure.shevchenko.provider.utils.Validator;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;


@WebServlet("/con")
public class TariffsConfigController extends HttpServlet {

    private List<Tariff> tariffs;

    private static final Logger log = Logger.getLogger(LoginController.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ServletContext context = req.getServletContext();

        HttpSession session = req.getSession();

        Integer roleAccess = (Integer) session.getAttribute("roleAccess");

        if(!(roleAccess ==null) && roleAccess==1 ) {

            MySqlTariffDao tariffDao = (MySqlTariffDao) context.getAttribute("tariffDao");

            tariffs = tariffDao.getAllTariffs();
            req.setAttribute("tariffs", tariffs);
            req.getRequestDispatcher(Page.TARIFFS_CONFIGURATION_PAGE).forward(req, resp);
        }
        else {
            req.getRequestDispatcher(Page.ERROR_PAGE).forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ServletContext context = req.getServletContext();

        HttpSession session = req.getSession();

        TariffService tariffService = (TariffService) context.getAttribute("tariffService");

        MySqlTariffDao tariffDao = (MySqlTariffDao) context.getAttribute("tariffDao");

        String name = req.getParameter("tarn");
        String specification = req.getParameter("spec");
        String price = req.getParameter("price");
        String service = req.getParameter("service");
        if (name==null) {
            doGet(req, resp);
        } else {
            Service current = tariffService.findService(service);
            log.trace(Messages.TRACE_FOUND_SERVICE+service);
            if (isNull(current)) {
                doGet(req, resp);
                session.setAttribute("nullTariff", "Not valid");
            } else if (Validator.validateTariffParameters(specification, name, price) && tariffService.isExist(name) ) {

                session.removeAttribute("nullTariff");

                int id = (int) current.getId();

                double currentPrice = Double.parseDouble(price);

                Tariff tariff = new Tariff(name, specification, currentPrice, id);

                log.trace(Messages.TRACE_CREATE_TARIFF+tariff);

                tariffDao.addTariff(tariff);

                doGet(req, resp);
            } else {
                session.setAttribute("nullTariff", "Not valid");
                doGet(req, resp);
            }
        }
    }


}
