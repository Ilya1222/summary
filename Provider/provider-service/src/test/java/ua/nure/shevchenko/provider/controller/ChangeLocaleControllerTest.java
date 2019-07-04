package ua.nure.shevchenko.provider.controller;

import org.junit.Test;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ChangeLocaleControllerTest {

    @Test
    public void doPost() throws ServletException, IOException {

        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);

        when(req.getParameter("locale")).thenReturn("ru");

        when(req.getSession()).thenReturn(session);

        new ChangeLocaleController().doPost(req,resp);

        session.setAttribute("ru","ru");



    }
}