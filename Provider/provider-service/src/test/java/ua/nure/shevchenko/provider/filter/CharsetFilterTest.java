package ua.nure.shevchenko.provider.filter;

import org.junit.Test;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CharsetFilterTest {

    @Test
    public void init() throws ServletException {
        FilterConfig filterConfig = mock(FilterConfig.class);
        new CharsetFilter().init(filterConfig);
    }

    @Test
    public void doFilter() throws IOException, ServletException {

        HttpServletRequest request = mock(HttpServletRequest.class);

        HttpServletResponse response = mock(HttpServletResponse.class);

        FilterChain filterChain = mock(FilterChain.class);

        new CharsetFilter().doFilter(request,response,filterChain);

    }

    @Test
    public void destroy() {
        new CharsetFilter().destroy();
    }
}