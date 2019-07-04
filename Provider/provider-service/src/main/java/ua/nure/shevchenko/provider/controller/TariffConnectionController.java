package ua.nure.shevchenko.provider.controller;

import org.apache.log4j.Logger;
import ua.nure.shevchenko.provider.constans.Messages;
import ua.nure.shevchenko.provider.constans.Page;
import ua.nure.shevchenko.provider.dao.mysqlDao.MySqlTariffDao;
import ua.nure.shevchenko.provider.entity.Score;
import ua.nure.shevchenko.provider.entity.Tariff;
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

@WebServlet("/connTar")

public class TariffConnectionController extends HttpServlet {

    private static final Logger log = Logger.getLogger(LoginController.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();

        ServletContext context = req.getServletContext();

        MySqlTariffDao tariffDao = (MySqlTariffDao) context.getAttribute("tariffDao");

        InfoTariffService infoService = (InfoTariffService) context.getAttribute("infoTariffService");

        User user = (User) session.getAttribute("user");

        log.trace(Messages.TRACE_FOUND_USER+user);

        Score  score = (Score) session.getAttribute("score");

        log.trace(Messages.TRACE_FOUND_SCORE+score);

        String id = req.getParameter("id");

        int tariffId = Integer.parseInt(id);

        Tariff tariff = tariffDao.findTariffById(tariffId);

        log.trace(Messages.TRACE_FOUND_TARIFF+tariff);

        session.setAttribute("currentTariff",tariff);

        log.trace(Messages.TRACE_SESSION_REMOVE_TARIFF+tariff);

        List<Boolean> mass = infoService.checkConnectedTariff(user,tariff);

        boolean connected = mass.get(0);

        boolean update = mass.get(1);

        if(connected){
            req.getRequestDispatcher(Page.TARIFF_IS_CONNECTED_PAGE).forward(req,resp);
        }

        session.setAttribute("update",null);


        if(update){
            session.setAttribute("update", "yes");
        }

        req.setAttribute("money",score.getBalance()-tariff.getPrice());
        req.setAttribute("thisTariff",tariff.getName());
        req.setAttribute("scoreBalance",score.getBalance());
        req.setAttribute("tariffPrice",tariff.getPrice());


        req.getRequestDispatcher(Page.TARIFF_CONNECTION_PAGE).forward(req,resp);

    }

}
