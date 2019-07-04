package ua.nure.shevchenko.provider.controller;

import org.junit.Test;
import ua.nure.shevchenko.provider.constans.Page;
import ua.nure.shevchenko.provider.entity.Score;
import ua.nure.shevchenko.provider.entity.User;
import ua.nure.shevchenko.provider.service.ClientService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ReplenishControllerTest {



    @Test
    public void doGet() throws ServletException, IOException {
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        HttpSession session = mock(HttpSession.class);

        when(req.getSession()).thenReturn(session);

        when(session.getAttribute("roleAccess")).thenReturn(2);

        when(req.getRequestDispatcher(Page.REPLENISH_PAGE)).thenReturn(dispatcher);

        new ReplenishController().doGet(req,resp);

        verify(dispatcher).forward(req,resp);

    }

    @Test
    public void doGet2() throws ServletException, IOException {
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        HttpSession session = mock(HttpSession.class);

        when(req.getSession()).thenReturn(session);

        when(session.getAttribute("roleAccess")).thenReturn(1);

        when(req.getRequestDispatcher(Page.ERROR_PAGE)).thenReturn(dispatcher);

        new ReplenishController().doGet(req,resp);

        verify(dispatcher).forward(req,resp);

    }




    @Test
    public void doPost() throws ServletException, IOException {
        HttpServletRequest req = mock(HttpServletRequest.class);

        HttpServletResponse resp = mock(HttpServletResponse.class);

        RequestDispatcher dispatcher = mock(RequestDispatcher.class);

        HttpSession session = mock(HttpSession.class);

        ServletContext context = mock(ServletContext.class);

        ClientService service = mock(ClientService.class);

        User user = mock(User.class);

        Score score = new Score();

        when(req.getServletContext()).thenReturn(context);

        when(context.getAttribute("clientService")).thenReturn(service);

        when(req.getParameter("currentAmount")).thenReturn("40");

        when(req.getSession()).thenReturn(session);

        when(session.getAttribute("user")).thenReturn(user);

        when(user.getScoreId()).thenReturn(31);

        when(service.doReplenish(new Score(),40)).thenReturn(score);

        when(req.getRequestDispatcher("menu")).thenReturn(dispatcher);

        new ReplenishController().doPost(req,resp);

         verify(dispatcher).forward(req,resp);

    }

    @Test
    public void doPost1() throws ServletException, IOException {
        HttpServletRequest req = mock(HttpServletRequest.class);

        HttpServletResponse resp = mock(HttpServletResponse.class);

        RequestDispatcher dispatcher = mock(RequestDispatcher.class);

        HttpSession session = mock(HttpSession.class);

        ServletContext context = mock(ServletContext.class);

        ClientService service = mock(ClientService.class);

        when(req.getServletContext()).thenReturn(context);

        when(context.getAttribute("clientService")).thenReturn(service);

        when(req.getParameter("currentAmount")).thenReturn("12fffd");

        when(req.getSession()).thenReturn(session);

        when(session.getAttribute("roleAccess")).thenReturn(2);

        when(req.getRequestDispatcher(Page.REPLENISH_PAGE)).thenReturn(dispatcher);

        new ReplenishController().doPost(req,resp);

        verify(dispatcher).forward(req,resp);

    }


}