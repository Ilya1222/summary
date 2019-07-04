package ua.nure.shevchenko.provider.controller;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.junit.Test;
import ua.nure.shevchenko.provider.constans.Page;
import ua.nure.shevchenko.provider.dao.mysqlDao.MySqlTariffDao;
import ua.nure.shevchenko.provider.entity.Score;
import ua.nure.shevchenko.provider.entity.Tariff;
import ua.nure.shevchenko.provider.entity.User;
import ua.nure.shevchenko.provider.service.InfoTariffService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TariffConnectionControllerTest {

    @Test
    public void doPost() throws ServletException, IOException {

        HttpServletRequest req = mock(HttpServletRequest.class);

        HttpServletResponse resp = mock(HttpServletResponse.class);

        HttpSession session = mock(HttpSession.class);

        RequestDispatcher dispatcher = mock(RequestDispatcher.class);

        ServletContext context = mock(ServletContext.class);

        MySqlTariffDao tariffDao = mock(MySqlTariffDao.class);

        InfoTariffService service = mock(InfoTariffService.class);

        User user = new User();

        Score score = new Score();

        Tariff tariff = new Tariff();

        List<Boolean> list = new ArrayList<>();

        list.add(true);

        list.add(false);

        when(req.getSession()).thenReturn(session);

        when(req.getServletContext()).thenReturn(context);

        when(context.getAttribute("tariffDao")).thenReturn(tariffDao);

        when(context.getAttribute("infoTariffService")).thenReturn(service);

        when(session.getAttribute("user")).thenReturn(user);

        when(session.getAttribute("score")).thenReturn(score);

        when(req.getParameter("id")).thenReturn("2");

        when(tariffDao.findTariffById(2)).thenReturn(tariff);

        when(service.checkConnectedTariff(user,tariff)).thenReturn(list);

        when(req.getRequestDispatcher(Page.TARIFF_IS_CONNECTED_PAGE)).thenReturn(dispatcher);

        when(req.getRequestDispatcher(Page.TARIFF_CONNECTION_PAGE)).thenReturn(dispatcher);

        new TariffConnectionController().doPost(req,resp);


    }
}