package com.tracer.welcomesystem.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.xml.ws.WebFault;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;


@Order(1)
@WebFilter(filterName = "testFilter", urlPatterns = "/*" , initParams = {
        @WebInitParam(name = "URL", value = "http://localhost:8080")})
//@Component
public class testfilter implements Filter{

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse Response, FilterChain filterChain) throws IOException, ServletException, ServletException, IOException {
        System.out.println("CilentToServer");
//        HttpServletResponse httpResponse = (HttpServletResponse) Response;
//        httpResponse.addHeader("Access-Control-Allow-Origin", "*");
        filterChain.doFilter(servletRequest, Response);
        System.out.println("ServerToClient");
    }
}