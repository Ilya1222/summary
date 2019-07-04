package ua.nure.shevchenko.provider.controller;

import org.apache.log4j.Logger;
import ua.nure.shevchenko.provider.constans.Messages;
import ua.nure.shevchenko.provider.constans.Page;
import ua.nure.shevchenko.provider.dao.mysqlDao.MySqlTariffDao;
import ua.nure.shevchenko.provider.entity.Tariff;
import ua.nure.shevchenko.provider.service.TariffService;
import ua.nure.shevchenko.provider.utils.Validator;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static java.util.Objects.nonNull;

@WebServlet("/updTariff")
public class TariffUpdateController extends HttpServlet {

    private static final Logger log = Logger.getLogger(LoginController.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        Integer roleAccess = (Integer) session.getAttribute("roleAccess");

        if(!(roleAccess ==null) && roleAccess==1 ) {

            String id = req.getParameter("id");
            req.setAttribute("id", id);
            req.getRequestDispatcher(Page.TARIFFS_UPDATE_PAGE).forward(req, resp);

        }

        else {
            req.getRequestDispatcher(Page.ERROR_PAGE).forward(req, resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ServletContext context = req.getServletContext();

        MySqlTariffDao tariffDao = (MySqlTariffDao) context.getAttribute("tariffDao");


        String id = req.getParameter("current");
        String newPrice = req.getParameter("priceNew");

        if(!newPrice.isEmpty() && Validator.validateTariffParameters(newPrice)){
            Tariff tariff = tariffDao.findTariffById(Integer.parseInt(id));
            log.trace(Messages.TRACE_FOUND_TARIFF+tariff);
            tariff.setPrice(Double.parseDouble(newPrice));
            log.trace(Messages.TRACE_TARIFF_NEW_PRICE+newPrice);
            tariffDao.updateTariff(tariff);
            log.trace(Messages.TARIFF_UPDATE+tariff);
            req.getRequestDispatcher("/con").forward(req, resp);
        }
        else {
            req.getRequestDispatcher("/con").forward(req, resp);
        }
    }

}
