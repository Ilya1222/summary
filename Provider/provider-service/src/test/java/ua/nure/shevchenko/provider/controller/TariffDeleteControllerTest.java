package ua.nure.shevchenko.provider.controller;

import org.junit.Test;
import ua.nure.shevchenko.provider.dao.mysqlDao.MySqlTariffDao;
import ua.nure.shevchenko.provider.entity.Tariff;
import ua.nure.shevchenko.provider.service.InfoTariffService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class TariffDeleteControllerTest {

    @Test
    public void doPost() throws ServletException, IOException {

        HttpServletRequest req = mock(HttpServletRequest.class);

        HttpServletResponse resp = mock(HttpServletResponse.class);

        RequestDispatcher dispatcher = mock(RequestDispatcher.class);



        ServletContext context = mock(ServletContext.class);

        InfoTariffService service = mock(InfoTariffService.class);

        MySqlTariffDao tariffDao = mock(MySqlTariffDao.class);

        when(req.getServletContext()).thenReturn(context);

        when(context.getAttribute("infoTariffService")).thenReturn(service);



        when(context.getAttribute("tariffDao")).thenReturn(tariffDao);

        when(req.getParameter("id")).thenReturn("4");

        when(tariffDao.findTariffById(4)).thenReturn(new Tariff());

        when(req.getRequestDispatcher("/con")).thenReturn(dispatcher);

          new TariffDeleteController().doPost(req,resp);

          verify(dispatcher).forward(req,resp);


    }
}