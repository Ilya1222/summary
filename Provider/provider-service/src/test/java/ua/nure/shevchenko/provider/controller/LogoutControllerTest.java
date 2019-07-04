package ua.nure.shevchenko.provider.controller;

import org.junit.Test;
import ua.nure.shevchenko.provider.constans.Page;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class LogoutControllerTest {



    @Test
    public void doGet() throws ServletException, IOException {

        String page = Page.START_PAGE;

        LogoutController controller = new LogoutController();

         HttpServletRequest req =  mock(HttpServletRequest.class);

         HttpServletResponse resp =  mock(HttpServletResponse.class);

         RequestDispatcher dispatcher = mock(RequestDispatcher.class);

         HttpSession session = mock(HttpSession.class);

         when(req.getRequestDispatcher(page)).thenReturn(dispatcher);

         when(req.getSession()).thenReturn(session);

         when(session.getAttribute("roleAccess")).thenReturn(2);

         session.setAttribute("login","login");

         session.setAttribute("password","pass");

         session.setAttribute("page",page);

         controller.doGet(req,resp);


         assertNull(session.getAttribute("login"));

         assertNull(session.getAttribute("password"));

         assertNull(session.getAttribute("page"));

         verify(dispatcher).forward(req,resp);

    }
}