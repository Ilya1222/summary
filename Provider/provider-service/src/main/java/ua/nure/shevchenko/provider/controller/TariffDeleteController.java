package ua.nure.shevchenko.provider.controller;

import org.apache.log4j.Logger;
import ua.nure.shevchenko.provider.constans.Messages;
import ua.nure.shevchenko.provider.constans.Page;
import ua.nure.shevchenko.provider.dao.mysqlDao.MySqlTariffDao;
import ua.nure.shevchenko.provider.entity.Tariff;
import ua.nure.shevchenko.provider.service.InfoTariffService;
import ua.nure.shevchenko.provider.service.TariffService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/del")
public class TariffDeleteController extends HttpServlet {

    private static final Logger log = Logger.getLogger(LoginController.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ServletContext context = req.getServletContext();

            InfoTariffService infoService = (InfoTariffService) context.getAttribute("infoTariffService");

            MySqlTariffDao tariffDao = (MySqlTariffDao) context.getAttribute("tariffDao");

            String id = req.getParameter("id");
            Tariff tariff = tariffDao.findTariffById(Integer.parseInt(id));

            log.trace(Messages.TRACE_FOUND_TARIFF + tariff);

            tariffDao.deleteTariff(tariff);

            log.trace(Messages.TRACE_DELETE_TARIFF + tariff);
            infoService.deleteTariffInfo(tariff);
            req.getRequestDispatcher("/con").forward(req, resp);


    }
}
