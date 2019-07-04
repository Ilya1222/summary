package ua.nure.shevchenko.provider.controller;

import org.junit.Test;
import ua.nure.shevchenko.provider.constans.Page;
import ua.nure.shevchenko.provider.dao.mysqlDao.MySqlTariffDao;
import ua.nure.shevchenko.provider.entity.User;
import ua.nure.shevchenko.provider.service.TariffService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import static org.mockito.Mockito.*;

public class TariffListControllerTest {

    @Test
    public void init() throws ServletException {
        new TariffListController().init();
    }

    @Test
    public void doGet() throws ServletException, IOException {

        HttpServletRequest req = mock(HttpServletRequest.class);

        HttpServletResponse resp = mock(HttpServletResponse.class);

        HttpSession session = mock(HttpSession.class);

        RequestDispatcher dispatcher = mock(RequestDispatcher.class);

        ServletContext context = mock(ServletContext.class);

        MySqlTariffDao tariffDao = mock(MySqlTariffDao.class);

        TariffService tariffService = mock(TariffService.class);

        when(req.getServletContext()).thenReturn(context);

        when(session.getAttribute("roleAccess")).thenReturn(2);

        when(context.getAttribute("tariffDao")).thenReturn(tariffDao);

        when(context.getAttribute("tariffService")).thenReturn(tariffService);

        when(req.getSession()).thenReturn(session);

        when(session.getAttribute("user")).thenReturn(new User());

        when(req.getRequestDispatcher(Page.TARIFF_LIST_PAGE)).thenReturn(dispatcher);

        new TariffListController().doGet(req,resp);

        verify(dispatcher).forward(req,resp);

    }

    @Test
    public void doGet2() throws ServletException, IOException {

        HttpServletRequest req = mock(HttpServletRequest.class);

        HttpServletResponse resp = mock(HttpServletResponse.class);

        HttpSession session = mock(HttpSession.class);

        RequestDispatcher dispatcher = mock(RequestDispatcher.class);

        ServletContext context = mock(ServletContext.class);

        MySqlTariffDao tariffDao = mock(MySqlTariffDao.class);

        TariffService tariffService = mock(TariffService.class);

        when(req.getServletContext()).thenReturn(context);

        when(session.getAttribute("roleAccess")).thenReturn(1);

        when(context.getAttribute("tariffDao")).thenReturn(tariffDao);

        when(context.getAttribute("tariffService")).thenReturn(tariffService);

        when(req.getSession()).thenReturn(session);

        when(session.getAttribute("user")).thenReturn(new User());

        when(req.getRequestDispatcher(Page.ERROR_PAGE)).thenReturn(dispatcher);

        new TariffListController().doGet(req,resp);

        verify(dispatcher).forward(req,resp);

    }


    @Test
    public void doPost() throws ServletException, IOException {

        HttpServletRequest req = mock(HttpServletRequest.class);

        HttpServletResponse resp = mock(HttpServletResponse.class);

        HttpSession session = mock(HttpSession.class);

        RequestDispatcher dispatcher = mock(RequestDispatcher.class);

        ServletContext context = mock(ServletContext.class);

        MySqlTariffDao tariffDao = mock(MySqlTariffDao.class);

        TariffService tariffService = mock(TariffService.class);

        when(req.getParameter("sort")).thenReturn("pr");


        when(req.getServletContext()).thenReturn(context);

        when(context.getAttribute("tariffDao")).thenReturn(tariffDao);

        when(context.getAttribute("tariffService")).thenReturn(tariffService);

        when(req.getSession()).thenReturn(session);

        when(session.getAttribute("roleAccess")).thenReturn(2);

        when(session.getAttribute("user")).thenReturn(new User());

        when(req.getRequestDispatcher(Page.TARIFF_LIST_PAGE)).thenReturn(dispatcher);



        new TariffListController().doPost(req,resp);

    }

@Test
    public void doPost1() throws ServletException, IOException {

        HttpServletRequest req = mock(HttpServletRequest.class);

        HttpServletResponse resp = mock(HttpServletResponse.class);

        HttpSession session = mock(HttpSession.class);

        RequestDispatcher dispatcher = mock(RequestDispatcher.class);

        ServletContext context = mock(ServletContext.class);

        MySqlTariffDao tariffDao = mock(MySqlTariffDao.class);

        TariffService tariffService = mock(TariffService.class);

        when(req.getParameter("sort")).thenReturn("az");


        when(req.getServletContext()).thenReturn(context);

        when(context.getAttribute("tariffDao")).thenReturn(tariffDao);

        when(context.getAttribute("tariffService")).thenReturn(tariffService);

        when(req.getSession()).thenReturn(session);

        when(session.getAttribute("user")).thenReturn(new User());

    when(session.getAttribute("roleAccess")).thenReturn(2);

        when(req.getRequestDispatcher(Page.TARIFF_LIST_PAGE)).thenReturn(dispatcher);



        new TariffListController().doPost(req,resp);

    }

@Test
    public void doPost2() throws ServletException, IOException {

        HttpServletRequest req = mock(HttpServletRequest.class);

        HttpServletResponse resp = mock(HttpServletResponse.class);

        HttpSession session = mock(HttpSession.class);

        RequestDispatcher dispatcher = mock(RequestDispatcher.class);

        ServletContext context = mock(ServletContext.class);

        MySqlTariffDao tariffDao = mock(MySqlTariffDao.class);

        TariffService tariffService = mock(TariffService.class);

        when(req.getParameter("sort")).thenReturn("za");


        when(req.getServletContext()).thenReturn(context);

        when(context.getAttribute("tariffDao")).thenReturn(tariffDao);

        when(context.getAttribute("tariffService")).thenReturn(tariffService);

        when(req.getSession()).thenReturn(session);

    when(session.getAttribute("roleAccess")).thenReturn(2);

        when(session.getAttribute("user")).thenReturn(new User());

        when(req.getRequestDispatcher(Page.TARIFF_LIST_PAGE)).thenReturn(dispatcher);



        new TariffListController().doPost(req,resp);

    }

@Test
    public void doPost3() throws ServletException, IOException {

        HttpServletRequest req = mock(HttpServletRequest.class);

        HttpServletResponse resp = mock(HttpServletResponse.class);

        HttpSession session = mock(HttpSession.class);

        RequestDispatcher dispatcher = mock(RequestDispatcher.class);

        ServletContext context = mock(ServletContext.class);

        MySqlTariffDao tariffDao = mock(MySqlTariffDao.class);

        TariffService tariffService = mock(TariffService.class);

        when(req.getParameter("sort")).thenReturn("dfddv");


        when(req.getServletContext()).thenReturn(context);

        when(context.getAttribute("tariffDao")).thenReturn(tariffDao);

        when(context.getAttribute("tariffService")).thenReturn(tariffService);

        when(req.getSession()).thenReturn(session);

        when(session.getAttribute("user")).thenReturn(new User());

    when(session.getAttribute("roleAccess")).thenReturn(2);

        when(req.getRequestDispatcher(Page.TARIFF_LIST_PAGE)).thenReturn(dispatcher);



        new TariffListController().doPost(req,resp);

    }

}