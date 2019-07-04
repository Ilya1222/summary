package ua.nure.shevchenko.provider.listener;

import ua.nure.shevchenko.provider.dao.mysqlDao.MySqlTariffConnectionInfoDao;
import ua.nure.shevchenko.provider.dao.mysqlDao.MySqlTariffDao;
import ua.nure.shevchenko.provider.dao.mysqlDao.MySqlUserDao;
import ua.nure.shevchenko.provider.service.ClientService;
import ua.nure.shevchenko.provider.service.InfoTariffService;
import ua.nure.shevchenko.provider.service.LoginService;
import ua.nure.shevchenko.provider.service.TariffService;

import javax.servlet.*;

public class ContextListener  implements ServletContextListener {


    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {

        InfoTariffService infoTariffService = new InfoTariffService();
        MySqlTariffDao tariffDao = new MySqlTariffDao();
        ClientService clientService = new ClientService();
        TariffService tariffService = new TariffService();
        LoginService loginService = new LoginService();
        MySqlUserDao userDao = new MySqlUserDao();
        MySqlTariffConnectionInfoDao infoDao = new MySqlTariffConnectionInfoDao();

        ServletContext servletContext = servletContextEvent.getServletContext();

        servletContext.setAttribute("infoTariffService",infoTariffService);
        servletContext.setAttribute("tariffDao",tariffDao);
        servletContext.setAttribute("clientService",clientService);
        servletContext.setAttribute("tariffService",tariffService);
        servletContext.setAttribute("loginService",loginService);
        servletContext.setAttribute("userDao",userDao);
        servletContext.setAttribute("infoDao",infoDao);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
