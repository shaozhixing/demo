package com.example.demo.contants;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author: shaozhixing
 * @date: 2019/8/30 12:16
 * @copyright: gofun
 */
@Component
public class TestFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String name = filterConfig.getFilterName();
        ServletContext servletContext = filterConfig.getServletContext();
        String token = filterConfig.getInitParameter("token");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String token = servletRequest.getParameter("token");
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
