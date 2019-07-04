package ua.nure.shevchenko.provider.controller;

import org.junit.Test;
import ua.nure.shevchenko.provider.constans.Page;
import ua.nure.shevchenko.provider.dao.mysqlDao.MySqlTariffDao;
import ua.nure.shevchenko.provider.entity.Tariff;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class TariffUpdateControllerTest {


    @Test
    public void doGet() throws ServletException, IOException {

        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        HttpSession session = mock(HttpSession.class);

        when(req.getSession()).thenReturn(session);

        when(session.getAttribute("roleAccess")).thenReturn(1);

        when(req.getParameter("id")).thenReturn("id");
        when(req.getRequestDispatcher(Page.TARIFFS_UPDATE_PAGE)).thenReturn(dispatcher);

        new TariffUpdateController().doGet(req,resp);

        verify(dispatcher).forward(req,resp);

    }

    @Test
    public void doGet2() throws ServletException, IOException {

        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        HttpSession session = mock(HttpSession.class);

        when(req.getSession()).thenReturn(session);

        when(session.getAttribute("roleAccess")).thenReturn(2);

        when(req.getParameter("id")).thenReturn("id");
        when(req.getRequestDispatcher(Page.ERROR_PAGE)).thenReturn(dispatcher);

        new TariffUpdateController().doGet(req,resp);

        verify(dispatcher).forward(req,resp);

    }

    @Test
    public void doPost() throws ServletException, IOException {

        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        ServletContext context = mock(ServletContext.class);
        MySqlTariffDao tariffDao = mock(MySqlTariffDao.class);



        when(req.getServletContext()).thenReturn(context);

        context.setAttribute("tariffDao","yttyy");

        when(context.getAttribute("tariffDao")).thenReturn(tariffDao);

        when(req.getParameter("current")).thenReturn("5");

        when(req.getParameter("priceNew")).thenReturn("wew");





        when(req.getRequestDispatcher("/con")).thenReturn(dispatcher);

        new TariffUpdateController().doPost(req,resp);


    }

    @Test
    public void doPost2() throws ServletException, IOException {

        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        ServletContext context = mock(ServletContext.class);
        MySqlTariffDao tariffDao = mock(MySqlTariffDao.class);
        Tariff tariff = mock(Tariff.class);


        when(req.getServletContext()).thenReturn(context);

        context.setAttribute("tariffDao","yttyy");

        when(context.getAttribute("tariffDao")).thenReturn(tariffDao);

        when(req.getParameter("current")).thenReturn("5");

        when(req.getParameter("priceNew")).thenReturn("10");


        when(tariffDao.findTariffById(5)).thenReturn(tariff);


        when(req.getRequestDispatcher("/con")).thenReturn(dispatcher);

        new TariffUpdateController().doPost(req,resp);


    }

}