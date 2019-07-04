package ua.nure.shevchenko.provider.controller;

import org.junit.Test;
import ua.nure.shevchenko.provider.dao.mysqlDao.MySqlTariffDao;
import ua.nure.shevchenko.provider.entity.Service;
import ua.nure.shevchenko.provider.entity.Tariff;
import ua.nure.shevchenko.provider.service.ClientService;
import ua.nure.shevchenko.provider.service.InfoTariffService;
import ua.nure.shevchenko.provider.service.TariffService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DownloadControllerTest {


    @Test
    public void doPost() throws ServletException, IOException {
        HttpServletRequest req = mock(HttpServletRequest.class);

        HttpServletResponse resp = mock(HttpServletResponse.class);

        ServletOutputStream stream = mock(ServletOutputStream.class);

        RequestDispatcher dispatcher = mock(RequestDispatcher.class);

        ServletContext context = mock(ServletContext.class);

        TariffService tariffService = mock(TariffService.class);

        MySqlTariffDao tariffDao = mock(MySqlTariffDao.class);

        Tariff tariff = new Tariff("Name","Spec",20,2);

        Service service = new Service();

        service.setId(2);

        service.setName("Service");

        when(req.getServletContext()).thenReturn(context);

        when(context.getAttribute("tariffDao")).thenReturn(tariffDao);

        when(context.getAttribute("tariffService")).thenReturn(tariffService);

        when(req.getParameter("id")).thenReturn("2");

        when(tariffDao.findTariffById(2)).thenReturn(tariff);

        when(tariffService.findService(2)).thenReturn(service);

        when(resp.getOutputStream()).thenReturn(stream);

        new DownloadController().doPost(req,resp);
    }
}