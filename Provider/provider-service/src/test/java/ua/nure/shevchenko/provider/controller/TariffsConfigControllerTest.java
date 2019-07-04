package ua.nure.shevchenko.provider.controller;

import org.junit.Test;
import ua.nure.shevchenko.provider.constans.Page;
import ua.nure.shevchenko.provider.dao.mysqlDao.MySqlTariffDao;
import ua.nure.shevchenko.provider.entity.Service;
import ua.nure.shevchenko.provider.service.TariffService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class TariffsConfigControllerTest {

    @Test
    public void doGet() throws ServletException, IOException {

        HttpServletRequest req = mock(HttpServletRequest.class);

        HttpServletResponse resp = mock(HttpServletResponse.class);

        RequestDispatcher dispatcher = mock(RequestDispatcher.class);

        HttpSession session = mock(HttpSession.class);

        ServletContext context = mock(ServletContext.class);

        MySqlTariffDao tariffDao = mock(MySqlTariffDao.class);

        when(req.getServletContext()).thenReturn(context);

        when(req.getSession()).thenReturn(session);

        when(session.getAttribute("roleAccess")).thenReturn(1);

        when(context.getAttribute("tariffDao")).thenReturn(tariffDao);

        when(req.getRequestDispatcher(Page.TARIFFS_CONFIGURATION_PAGE)).thenReturn(dispatcher);

        new TariffsConfigController().doGet(req,resp);

        verify(dispatcher).forward(req,resp);


    }



    @Test
    public void doPost() throws ServletException, IOException {

        HttpServletRequest req = mock(HttpServletRequest.class);

        HttpServletResponse resp = mock(HttpServletResponse.class);

        RequestDispatcher dispatcher = mock(RequestDispatcher.class);

        HttpSession session = mock(HttpSession.class);

        ServletContext context = mock(ServletContext.class);

        MySqlTariffDao tariffDao = mock(MySqlTariffDao.class);

        TariffService service = mock(TariffService.class);

        when(req.getServletContext()).thenReturn(context);

        when(req.getSession()).thenReturn(session);

        when(context.getAttribute("tariffService")).thenReturn(service);

        when(context.getAttribute("tariffDao")).thenReturn(tariffDao);

        when(req.getParameter("tarn")).thenReturn(null);

        when(req.getParameter("spec")).thenReturn("spec");

        when(session.getAttribute("roleAccess")).thenReturn(2);

        when(req.getParameter("price")).thenReturn("price");

        when(req.getParameter("service")).thenReturn("sercice");

        when(req.getRequestDispatcher(Page.ERROR_PAGE)).thenReturn(dispatcher);

        new TariffsConfigController().doPost(req,resp);

    }


    @Test
    public void doPost2() throws ServletException, IOException {


        HttpServletRequest req = mock(HttpServletRequest.class);

        HttpServletResponse resp = mock(HttpServletResponse.class);

        RequestDispatcher dispatcher = mock(RequestDispatcher.class);

        HttpSession session = mock(HttpSession.class);

        ServletContext context = mock(ServletContext.class);

        MySqlTariffDao tariffDao = mock(MySqlTariffDao.class);

        TariffService service = mock(TariffService.class);

        when(req.getServletContext()).thenReturn(context);

        when(req.getSession()).thenReturn(session);

        when(context.getAttribute("tariffService")).thenReturn(service);

        when(context.getAttribute("tariffDao")).thenReturn(tariffDao);

        when(req.getParameter("tarn")).thenReturn("tarn");

        when(req.getParameter("spec")).thenReturn("spec");

        when(req.getParameter("price")).thenReturn("price");

        when(session.getAttribute("roleAccess")).thenReturn(1);
        when(req.getParameter("service")).thenReturn("service");

        when(service.findService("service")).thenReturn(null);

        when(req.getRequestDispatcher(Page.TARIFFS_CONFIGURATION_PAGE)).thenReturn(dispatcher);

        new TariffsConfigController().doPost(req,resp);

    }


    @Test
    public void doPost3() throws ServletException, IOException {

        HttpServletRequest req = mock(HttpServletRequest.class);

        HttpServletResponse resp = mock(HttpServletResponse.class);

        RequestDispatcher dispatcher = mock(RequestDispatcher.class);

        HttpSession session = mock(HttpSession.class);

        ServletContext context = mock(ServletContext.class);

        MySqlTariffDao tariffDao = mock(MySqlTariffDao.class);

        TariffService tariffService = mock(TariffService.class);


        Service i = new Service();

        i.setId(3);

        i.setName("Internet");

        when(req.getServletContext()).thenReturn(context);

        when(req.getSession()).thenReturn(session);

        when(context.getAttribute("tariffService")).thenReturn(tariffService);

        when(context.getAttribute("tariffDao")).thenReturn(tariffDao);

        when(req.getParameter("tarn")).thenReturn("Mobb");

        when(req.getParameter("spec")).thenReturn("Its a mobbbbbbbbbb");

        when(req.getParameter("price")).thenReturn("50");

        when(session.getAttribute("roleAccess")).thenReturn(1);
        when(req.getParameter("service")).thenReturn("Internet");

        when(tariffService.findService("Internet")).thenReturn(i);

        when(req.getRequestDispatcher(Page.TARIFFS_CONFIGURATION_PAGE)).thenReturn(dispatcher);

        new TariffsConfigController().doPost(req,resp);

    }



    @Test
    public void doPost4() throws ServletException, IOException {

        HttpServletRequest req = mock(HttpServletRequest.class);

        HttpServletResponse resp = mock(HttpServletResponse.class);

        RequestDispatcher dispatcher = mock(RequestDispatcher.class);

        HttpSession session = mock(HttpSession.class);

        ServletContext context = mock(ServletContext.class);

        MySqlTariffDao tariffDao = mock(MySqlTariffDao.class);

        TariffService tariffService = mock(TariffService.class);


        Service i = new Service();

        i.setId(3);

        i.setName("Internet");

        when(req.getServletContext()).thenReturn(context);

        when(req.getSession()).thenReturn(session);

        when(context.getAttribute("tariffService")).thenReturn(tariffService);

        when(context.getAttribute("tariffDao")).thenReturn(tariffDao);

        when(req.getParameter("tarn")).thenReturn("Mobb");

        when(req.getParameter("spec")).thenReturn("Its");

        when(session.getAttribute("roleAccess")).thenReturn(1);

        when(req.getParameter("price")).thenReturn("5w0");

        when(req.getParameter("service")).thenReturn("Internet");

        when(tariffService.findService("Internet")).thenReturn(i);

        when(req.getRequestDispatcher(Page.TARIFFS_CONFIGURATION_PAGE)).thenReturn(dispatcher);

        new TariffsConfigController().doPost(req,resp);

    }


}