package ua.nure.shevchenko.provider.controller;

import org.junit.Test;
import ua.nure.shevchenko.provider.constans.Page;
import ua.nure.shevchenko.provider.entity.TariffConnectionInfo;
import ua.nure.shevchenko.provider.entity.Score;
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

import static org.mockito.Mockito.*;

public class LoginControllerTest {

    @Test
    public void doGet() throws ServletException, IOException {
        HttpServletRequest req = mock(HttpServletRequest.class);

        HttpServletResponse resp = mock(HttpServletResponse.class);

        HttpSession session = mock(HttpSession.class);

        RequestDispatcher dispatcher = mock(RequestDispatcher.class);


        String page = Page.START_PAGE;

        when(req.getSession()).thenReturn(session);

        when(session.getAttribute("user")).thenReturn(null);

        when(req.getRequestDispatcher(page)).thenReturn(dispatcher);

        when(session.getAttribute("page")).thenReturn(page);

        new LoginController().doGet(req,resp);

        verify(dispatcher).forward(req,resp);

    }

    @Test
    public void doGet2() throws ServletException, IOException {
        HttpServletRequest req = mock(HttpServletRequest.class);

        HttpServletResponse resp = mock(HttpServletResponse.class);

        HttpSession session = mock(HttpSession.class);

        RequestDispatcher dispatcher = mock(RequestDispatcher.class);

        ServletContext context = mock(ServletContext.class);

        ClientService clientService = mock(ClientService.class);

        InfoTariffService infoTariffService = mock(InfoTariffService.class);

        LoginService loginService = mock(LoginService.class);

        List<TariffConnectionInfo> list = new ArrayList<>();

        list.add(new TariffConnectionInfo());

        String page = Page.ADMIN_PAGE;

        User user = new User();

        user.setFirstName("Igor");

        user.setSurName("Miron");

        user.setRoleId(1);

        when(req.getSession()).thenReturn(session);

        when(session.getAttribute("user")).thenReturn(user);

        when(req.getRequestDispatcher(page)).thenReturn(dispatcher);

        when(session.getAttribute("roleAccess")).thenReturn(2);

        when(session.getAttribute("page")).thenReturn(page);

        when(req.getServletContext()).thenReturn(context);

        when(context.getAttribute("clientService")).thenReturn(clientService);

        when(context.getAttribute("infoTariffService")).thenReturn(infoTariffService);

        when(infoTariffService.findAll()).thenReturn(list);

        new LoginController().doGet(req,resp);

        verify(dispatcher).forward(req,resp);

    }

    @Test
    public void doGet3() throws ServletException, IOException {
        HttpServletRequest req = mock(HttpServletRequest.class);

        HttpServletResponse resp = mock(HttpServletResponse.class);

        HttpSession session = mock(HttpSession.class);

        RequestDispatcher dispatcher = mock(RequestDispatcher.class);

        ServletContext context = mock(ServletContext.class);

        ClientService clientService = mock(ClientService.class);

        InfoTariffService infoTariffService = mock(InfoTariffService.class);

        LoginService loginService = mock(LoginService.class);

        List<TariffConnectionInfo> list = new ArrayList<>();

        list.add(new TariffConnectionInfo());

        String page = Page.ADMIN_PAGE;

        User user = new User();

        user.setFirstName("Igor");

        user.setSurName("Miron");

        user.setRoleId(1);

        when(req.getSession()).thenReturn(session);

        when(session.getAttribute("user")).thenReturn(user);

        when(req.getRequestDispatcher(Page.ERROR_PAGE)).thenReturn(dispatcher);

        when(session.getAttribute("roleAccess")).thenReturn(null);

        when(session.getAttribute("page")).thenReturn(page);

        when(req.getServletContext()).thenReturn(context);

        when(context.getAttribute("clientService")).thenReturn(clientService);

        when(context.getAttribute("infoTariffService")).thenReturn(infoTariffService);

        when(infoTariffService.findAll()).thenReturn(list);

        new LoginController().doGet(req,resp);

        verify(dispatcher).forward(req,resp);

    }

    @Test
    public void doPost() throws ServletException, IOException {

        HttpServletRequest req = mock(HttpServletRequest.class);

        HttpServletResponse resp = mock(HttpServletResponse.class);

        HttpSession currentSession = mock(HttpSession.class);

        RequestDispatcher dispatcher = mock(RequestDispatcher.class);

        ServletContext context = mock(ServletContext.class);

        ClientService clientService = mock(ClientService.class);

        InfoTariffService infoTariffService = mock(InfoTariffService.class);

        LoginService loginService = mock(LoginService.class);

        String page = Page.START_PAGE;

        when(req.getSession()).thenReturn(currentSession);

        when(req.getServletContext()).thenReturn(context);

        when(context.getAttribute("infoTariffService")).thenReturn(infoTariffService);

        when(context.getAttribute("loginService")).thenReturn(loginService);

        when(currentSession.getAttribute("page")).thenReturn(page);

        when(currentSession.getAttribute("login")).thenReturn(null);

        when(currentSession.getAttribute("password")).thenReturn(null);

        when(req.getRequestDispatcher(page)).thenReturn(dispatcher);

        new LoginController().doPost(req,resp);


    }

    @Test
    public void doPost2() throws ServletException, IOException {

        HttpServletRequest req = mock(HttpServletRequest.class);

        HttpServletResponse resp = mock(HttpServletResponse.class);

        HttpSession currentSession = mock(HttpSession.class);

        RequestDispatcher dispatcher = mock(RequestDispatcher.class);

        ServletContext context = mock(ServletContext.class);

        ClientService clientService = mock(ClientService.class);

        InfoTariffService infoTariffService = mock(InfoTariffService.class);

        LoginService loginService = mock(LoginService.class);

        String page = Page.START_PAGE;

        User user = new User();

        user.setRoleId(2);
        user.setFirstName("Lol");
        user.setSurName("Kek");
        user.setBlocked(true);
        user.setScoreId(5);
        user.setLogin("login");


        List<TariffConnectionInfo> infoTariffs = new ArrayList<>();

        infoTariffs.add(new TariffConnectionInfo());

        List<TariffConnectionInfo> debtTariffs = new ArrayList<>();


        debtTariffs.add(new TariffConnectionInfo());

        when(req.getSession()).thenReturn(currentSession);

        when(req.getServletContext()).thenReturn(context);

        when(context.getAttribute("infoTariffService")).thenReturn(infoTariffService);

        when(context.getAttribute("loginService")).thenReturn(loginService);

        when(currentSession.getAttribute("page")).thenReturn(page);

        when(currentSession.getAttribute("login")).thenReturn("login");

        when(currentSession.getAttribute("password")).thenReturn("password");


        when(currentSession.getAttribute("user")).thenReturn(user);

        when(context.getAttribute("clientService")).thenReturn(clientService);

        when(infoTariffService.findDebtTariff(user)).thenReturn(debtTariffs);

        when(infoTariffService.findAllUserTariffs(user)).thenReturn(infoTariffs);

        when(clientService.getScoreById(5)).thenReturn(new Score());

        when(loginService.getUser(user.getLogin())).thenReturn(user);

        when(infoTariffService.findDebtTariff(user)).thenReturn(infoTariffs);

        when(req.getRequestDispatcher(Page.CLIENT_BLOCK_PAGE)).thenReturn(dispatcher);

        new LoginController().doPost(req,resp);



    }


}