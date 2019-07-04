package ua.nure.shevchenko.provider.controller;

import org.junit.Test;
import sun.rmi.runtime.Log;
import ua.nure.shevchenko.provider.constans.Page;
import ua.nure.shevchenko.provider.dao.entityDaoInterface.UserDao;
import ua.nure.shevchenko.provider.dao.mysqlDao.MySqlUserDao;
import ua.nure.shevchenko.provider.entity.User;
import ua.nure.shevchenko.provider.service.ClientService;
import ua.nure.shevchenko.provider.service.InfoTariffService;
import ua.nure.shevchenko.provider.service.LoginService;

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
import static org.mockito.Mockito.*;

public class RegistrationControllerTest {

    @Test
    public void doGet() throws ServletException, IOException {
        HttpServletRequest req = mock(HttpServletRequest.class);

        HttpServletResponse resp = mock(HttpServletResponse.class);

        HttpSession session = mock(HttpSession.class);

        RequestDispatcher dispatcher = mock(RequestDispatcher.class);

        ServletContext context = mock(ServletContext.class);

        LoginService loginService = mock(LoginService.class);


        List<User> list = new ArrayList<>();

        list.add(new User());

        when(req.getServletContext()).thenReturn(context);

        when(req.getSession()).thenReturn(session);

        when(session.getAttribute("roleAccess")).thenReturn(1);

        when(context.getAttribute("loginService")).thenReturn(loginService);

        when(loginService.getAllUser()).thenReturn(list);

        when(req.getRequestDispatcher(Page.REGISTRATION_PAGE)).thenReturn(dispatcher);

        new RegistrationController().doGet(req, resp);

        verify(dispatcher).forward(req,resp);

    }
    @Test
    public void doGet2() throws ServletException, IOException {
        HttpServletRequest req = mock(HttpServletRequest.class);

        HttpServletResponse resp = mock(HttpServletResponse.class);

        HttpSession session = mock(HttpSession.class);

        RequestDispatcher dispatcher = mock(RequestDispatcher.class);

        ServletContext context = mock(ServletContext.class);

        LoginService loginService = mock(LoginService.class);


        List<User> list = new ArrayList<>();

        list.add(new User());

        when(req.getServletContext()).thenReturn(context);

        when(req.getSession()).thenReturn(session);

        when(session.getAttribute("roleAccess")).thenReturn(2);

        when(context.getAttribute("loginService")).thenReturn(loginService);

        when(loginService.getAllUser()).thenReturn(list);

        when(req.getRequestDispatcher(Page.ERROR_PAGE)).thenReturn(dispatcher);

        new RegistrationController().doGet(req, resp);

        verify(dispatcher).forward(req,resp);

    }

    @Test
    public void doPost() throws ServletException, IOException {

        HttpServletRequest req = mock(HttpServletRequest.class);

        HttpServletResponse resp = mock(HttpServletResponse.class);

        HttpSession session = mock(HttpSession.class);

        RequestDispatcher dispatcher = mock(RequestDispatcher.class);

        ServletContext context = mock(ServletContext.class);

        ClientService clientService = mock(ClientService.class);

        LoginService loginService = mock(LoginService.class);

        MySqlUserDao userDao = mock(MySqlUserDao.class);

        when(context.getAttribute("userDao")).thenReturn(userDao);

        when(req.getSession()).thenReturn(session);

        when(session.getAttribute("roleAccess")).thenReturn(1);

        when(context.getAttribute("clientService")).thenReturn(clientService);

        when(req.getAttribute("validate")).thenReturn(false);

        List<User> list = new ArrayList<>();

        list.add(new User());

        when(req.getServletContext()).thenReturn(context);

        when(context.getAttribute("loginService")).thenReturn(loginService);

        when(loginService.getAllUser()).thenReturn(list);

        when(req.getRequestDispatcher(Page.REGISTRATION_PAGE)).thenReturn(dispatcher);

        new RegistrationController().doPost(req, resp);

        verify(dispatcher).forward(req,resp);

    }

    @Test
    public void doPost2() throws ServletException, IOException {

        HttpServletRequest req = mock(HttpServletRequest.class);

        HttpServletResponse resp = mock(HttpServletResponse.class);

        HttpSession session = mock(HttpSession.class);

        RequestDispatcher dispatcher = mock(RequestDispatcher.class);

        ServletContext context = mock(ServletContext.class);

        ClientService clientService = mock(ClientService.class);

        LoginService loginService = mock(LoginService.class);

        MySqlUserDao userDao = mock(MySqlUserDao.class);

        when(context.getAttribute("userDao")).thenReturn(userDao);

        when(context.getAttribute("clientService")).thenReturn(clientService);

        when(req.getAttribute("validate")).thenReturn(true);

        when(req.getParameter("login")).thenReturn("login1");
        when( req.getParameter("pass")).thenReturn("passss22");
        when(req.getParameter("name")).thenReturn("Name");
        when(req.getParameter("sur")).thenReturn("Partgvcd");
        when(req.getParameter("last")).thenReturn("Lastt");
        when( req.getParameter("email")).thenReturn("email@gmail.com");
        when(req.getParameter("numb")).thenReturn("0955939605");

        when(clientService.createScore()).thenReturn((long) 2);


        when(req.getSession()).thenReturn(session);

        when(session.getAttribute("roleAccess")).thenReturn(1);


        List<User> list = new ArrayList<>();

        list.add(new User());

        when(req.getServletContext()).thenReturn(context);

        when(context.getAttribute("loginService")).thenReturn(loginService);

        when(loginService.getAllUser()).thenReturn(list);

        when(req.getRequestDispatcher(Page.REGISTRATION_PAGE)).thenReturn(dispatcher);

        new RegistrationController().doPost(req, resp);

        verify(dispatcher).forward(req,resp);

    }


}