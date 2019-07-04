package ua.nure.shevchenko.provider.listener;

import org.junit.Test;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ContextListenerTest {

    @Test
    public void contextInitialized() {
        ServletContextEvent servletContextEvent = mock(ServletContextEvent.class);
        ServletContext context = mock(ServletContext.class);

        when(servletContextEvent.getServletContext()).thenReturn(context);

        new ContextListener().contextInitialized(servletContextEvent);

    }

    @Test
    public void contextDestroyed() {
        ServletContextEvent servletContextEvent = mock(ServletContextEvent.class);
        new ContextListener().contextDestroyed(servletContextEvent);
    }
}