package com.tracer.welcomesystem.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.xml.ws.WebFault;

import org.springframework.core.annotation.Order;

import java.io.IOException;


@Order(1)
@WebFilter(filterName = "testFilter", urlPatterns = "/*" , initParams = {
        @WebInitParam(name = "URL", value = "http://localhost:8080")})
public class testfilter implements Filter{

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException, ServletException, IOException {
        System.out.println("CilentToServer");
        filterChain.doFilter(servletRequest, servletResponse);
        System.out.println("ServerToClient");
    }
}