package ua.nure.shevchenko.provider.controller;

import org.junit.Test;
import ua.nure.shevchenko.provider.entity.Score;
import ua.nure.shevchenko.provider.entity.Tariff;
import ua.nure.shevchenko.provider.entity.User;
import ua.nure.shevchenko.provider.service.ClientService;
import ua.nure.shevchenko.provider.service.InfoTariffService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import static org.junit.Assert.*;
import static org.mockito.Matchers.matches;
import static org.mockito.Mockito.*;

public class TariffConfirmControllerTest {

    @Test
    public void doPost() throws ServletException, IOException {


        HttpServletRequest req = mock(HttpServletRequest.class);

        HttpServletResponse resp = mock(HttpServletResponse.class);

        RequestDispatcher dispatcher = mock(RequestDispatcher.class);

        HttpSession session = mock(HttpSession.class);

        ServletContext context = mock(ServletContext.class);

        InfoTariffService service = mock(InfoTariffService.class);

        ClientService clientService = mock(ClientService.class);

        Tariff tariff = mock(Tariff.class);

        Score score = mock(Score.class);

        when(req.getSession()).thenReturn(session);

        when(req.getServletContext()).thenReturn(context);

        when(context.getAttribute("clientService")).thenReturn(clientService);

        when(context.getAttribute("infoTariffService")).thenReturn(service);

        when( session.getAttribute("user")).thenReturn(new User());

        when(session.getAttribute("currentTariff")).thenReturn(new Tariff());

        when(session.getAttribute("score")).thenReturn(new Score());

        when(session.getAttribute("update")).thenReturn(true);

        when(service.findIdInfo(new Tariff(),new User())).thenReturn((long) 2);

        when(score.getBalance()).thenReturn((double) 100);

        when(tariff.getPrice()).thenReturn((double) 30);

        when(req.getRequestDispatcher("menu")).thenReturn(dispatcher);

        new TariffConfirmController().doPost(req,resp);

        verify(dispatcher).forward(req,resp);

    }


    @Test
    public void doPost2() throws ServletException, IOException {

        HttpServletRequest req = mock(HttpServletRequest.class);

        HttpServletResponse resp = mock(HttpServletResponse.class);

        RequestDispatcher dispatcher = mock(RequestDispatcher.class);

        HttpSession session = mock(HttpSession.class);

        ServletContext context = mock(ServletContext.class);

        InfoTariffService service = mock(InfoTariffService.class);

        ClientService clientService = mock(ClientService.class);

        Tariff tariff = mock(Tariff.class);

        Score score = mock(Score.class);

        when(req.getSession()).thenReturn(session);

        when(req.getServletContext()).thenReturn(context);

        when(context.getAttribute("clientService")).thenReturn(clientService);

        when(context.getAttribute("infoTariffService")).thenReturn(service);

        when( session.getAttribute("user")).thenReturn(new User());

        when(session.getAttribute("currentTariff")).thenReturn(new Tariff());

        when(session.getAttribute("score")).thenReturn(new Score());

        when(session.getAttribute("update")).thenReturn(null);

        when(score.getBalance()).thenReturn((double) 100);

        when(tariff.getPrice()).thenReturn((double) 30);

        when(req.getRequestDispatcher("menu")).thenReturn(dispatcher);

        new TariffConfirmController().doPost(req,resp);

        verify(dispatcher).forward(req,resp);


    }

}