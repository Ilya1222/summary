package ua.nure.shevchenko.provider.controller;

import org.junit.Test;
import ua.nure.shevchenko.provider.constans.Page;
import ua.nure.shevchenko.provider.entity.TariffConnectionInfo;
import ua.nure.shevchenko.provider.service.InfoTariffService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.*;

public class BlockClientsControllerTest {





    @Test
    public void doGet() throws ServletException, IOException {


        HttpServletRequest req = mock(HttpServletRequest.class);

        HttpServletResponse resp = mock(HttpServletResponse.class);

        RequestDispatcher dispatcher = mock(RequestDispatcher.class);

        HttpSession session = mock(HttpSession.class);

        ServletContext context = mock(ServletContext.class);

        InfoTariffService service = mock(InfoTariffService.class);



        List<TariffConnectionInfo> list = new ArrayList<>();

        list.add(new TariffConnectionInfo(1,1,new Date()));




        when(req.getSession()).thenReturn(session);

        when(req.getServletContext()).thenReturn(context);

        when(session.getAttribute("roleAccess")).thenReturn(1);

        context.setAttribute("infoTariffService","service");

        when(context.getAttribute("infoTariffService")).thenReturn(service);

        when(service.findAllDebt()).thenReturn(list);

        when(req.getRequestDispatcher(Page.BLOCKING_USERS_PAGE)).thenReturn(dispatcher);

        session.setAttribute("infoDebt", list);

        new BlockClientsController().doGet(req, resp);

        verify(dispatcher).forward(req, resp);




    }



    @Test
    public void doPost() throws ServletException, IOException {

        HttpServletRequest req = mock(HttpServletRequest.class);

        HttpServletResponse resp = mock(HttpServletResponse.class);

        RequestDispatcher dispatcher = mock(RequestDispatcher.class);

        HttpSession session = mock(HttpSession.class);

        ServletContext context = mock(ServletContext.class);

        InfoTariffService service = mock(InfoTariffService.class);

        when(session.getAttribute("roleAccess")).thenReturn(1);

        List<TariffConnectionInfo> list = new ArrayList<>();

        list.add(new TariffConnectionInfo(1,1,new Date()));


        when(req.getServletContext()).thenReturn(context);

        when(context.getAttribute("infoTariffService")).thenReturn(service);

        when(req.getSession()).thenReturn(session);

        session.setAttribute("infoDebt",list);

        when(req.getSession()).thenReturn(session);

        when(req.getServletContext()).thenReturn(context);

        context.setAttribute("infoTariffService","service");

        when(context.getAttribute("infoTariffService")).thenReturn(service);

        when(service.findAllDebt()).thenReturn(list);

        when(req.getRequestDispatcher(Page.BLOCKING_USERS_PAGE)).thenReturn(dispatcher);

        session.setAttribute("infoDebt", list);



        new BlockClientsController().doPost(req,resp);



    }
}