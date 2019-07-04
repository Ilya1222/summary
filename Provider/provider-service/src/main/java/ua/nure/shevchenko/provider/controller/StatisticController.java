package ua.nure.shevchenko.provider.controller;

import ua.nure.shevchenko.provider.constans.Page;
import ua.nure.shevchenko.provider.dao.mysqlDao.MySqlTariffConnectionInfoDao;
import ua.nure.shevchenko.provider.dao.mysqlDao.MySqlTariffDao;
import ua.nure.shevchenko.provider.entity.Statistic;
import ua.nure.shevchenko.provider.entity.Tariff;
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
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@WebServlet("/statistic")
public class StatisticController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ServletContext context = req.getServletContext();

        HttpSession session = req.getSession();

        MySqlTariffDao tariffDao = (MySqlTariffDao) context.getAttribute("tariffDao");

        MySqlTariffConnectionInfoDao infoDao = (MySqlTariffConnectionInfoDao) context.getAttribute("infoDao");

        List<Statistic> statisticList = new ArrayList<>();

        List<TariffConnectionInfo> allTariffs = infoDao.getAll();

        for (TariffConnectionInfo info : allTariffs) {

            Tariff tariff = tariffDao.findTariffById((int) info.getTariffId());

            Statistic statistic = new Statistic(tariff.getName(), info.getMoney(), 1);

            if (statisticList.contains(statistic)) {

                int index = statisticList.indexOf(statistic);

                double money = statisticList.get(index).getMoney() + statistic.getMoney();

                int amount = statisticList.get(index).getAmountUsers() + 1;

                Statistic newStatistic = new Statistic(statistic.getTariffName(), money, amount);

                statisticList.remove(index);

                statisticList.add(newStatistic);

            } else {
                statisticList.add(statistic);
            }
        }

        Collections.sort(statisticList, (o1, o2) -> o2.getAmountUsers() - o1.getAmountUsers());

        session.setAttribute("statisticList", statisticList);

        req.getRequestDispatcher(Page.STATISTIC_PAGE).forward(req, resp);

    }





}
