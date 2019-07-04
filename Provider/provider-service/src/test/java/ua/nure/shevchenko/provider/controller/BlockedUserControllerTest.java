package ua.nure.shevchenko.provider.controller;

import org.junit.Test;
import ua.nure.shevchenko.provider.constans.Page;
import ua.nure.shevchenko.provider.entity.TariffConnectionInfo;
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
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.*;

public class BlockedUserControllerTest {

    @Test
    public void doGet() throws ServletException, IOException {

        HttpServletRequest req = mock(HttpServletRequest.class);

        HttpServletResponse resp = mock(HttpServletResponse.class);

        RequestDispatcher dispatcher = mock(RequestDispatcher.class);

        HttpSession session = mock(HttpSession.class);

        ServletContext context = mock(ServletContext.class);

        InfoTariffService service = mock(InfoTariffService.class);

        User user = new User();

        List<TariffConnectionInfo> list = new ArrayList<>();

        list.add(new TariffConnectionInfo(1,1,new Date()));

        when(req.getSession()).thenReturn(session);

        when(req.getServletContext()).thenReturn(context);

        when(session.getAttribute("blockAccess")).thenReturn(true);

        when(context.getAttribute("infoTariffService")).thenReturn(service);

        when(session.getAttribute("user")).thenReturn(user);

        when(service.findAllUserTariffs(user)).thenReturn(list);

        when(service.findDebtTariff(list)).thenReturn(list);

        when(req.getRequestDispatcher(Page.CLIENT_BLOCK_PAGE)).thenReturn(dispatcher);

        new BlockedUserController().doGet(req,resp);

        verify(dispatcher).forward(req,resp);




    }

    @Test
    public void doGet2() throws ServletException, IOException {

        HttpServletRequest req = mock(HttpServletRequest.class);

        HttpServletResponse resp = mock(HttpServletResponse.class);

        RequestDispatcher dispatcher = mock(RequestDispatcher.class);

        HttpSession session = mock(HttpSession.class);

        ServletContext context = mock(ServletContext.class);

        InfoTariffService service = mock(InfoTariffService.class);

        User user = new User();

        List<TariffConnectionInfo> list = new ArrayList<>();

        list.add(new TariffConnectionInfo(1,1,new Date()));

        when(req.getSession()).thenReturn(session);

        when(req.getServletContext()).thenReturn(context);

        when(session.getAttribute("blockAccess")).thenReturn(false);


        when(req.getRequestDispatcher(Page.ERROR_PAGE)).thenReturn(dispatcher);

        new BlockedUserController().doGet(req,resp);

        verify(dispatcher).forward(req,resp);




    }
}