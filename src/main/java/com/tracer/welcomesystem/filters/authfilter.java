package com.tracer.welcomesystem.filters;

import com.tracer.welcomesystem.services.AuthService;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import java.io.IOException;
import java.util.*;


@Order(2)
@WebFilter(filterName = "authFilter", urlPatterns = "/*")
@Component
public class authfilter implements Filter {

    private static final Set<String> ALLOWED_PATHS = Collections.unmodifiableSet(
            new HashSet<>(Arrays.asList("/admin/register",
                    "/admin/login"
                    )));

    @Autowired
    private AuthService authService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, filterConfig.getServletContext());
    }

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain
    ) throws IOException, ServletException {

        System.out.println("AuthFilter");

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String path = httpRequest.getRequestURI().substring(httpRequest.getContextPath().length()).replaceAll("[/]+$", "");

        if (Objects.equals(httpRequest.getMethod(), "OPTIONS")) {
            //access-control-allow-origin
            httpResponse.setHeader("Access-Control-Allow-Origin", "*");
            httpResponse.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE");
            httpResponse.setHeader("Access-Control-Allow-Headers", "Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With, X-Token");
            httpResponse.setHeader("Access-Control-Max-Age", "3600");


            chain.doFilter(request, response);
            return;
        }

        if (ALLOWED_PATHS.contains(path)) {
            chain.doFilter(request, response);
            return;
        }
        String token = httpRequest.getHeader("X-Token");
        if (token == null) {
            Cookie[] cookies = httpRequest.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("tokenKey")) {
                        token = cookie.getValue();
                    }
                }
            }
        }

        if (token== null) {
            token = httpRequest.getParameter("token");
        }



        if (authService.validateToken(token)) {
            chain.doFilter(request, response);
        } else {
            throw new ServletException("Invalid token");
        }
    }
}