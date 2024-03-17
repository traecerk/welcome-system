package com.tracer.welcomesystem.filters;

import com.tracer.welcomesystem.services.AuthService;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import java.io.IOException;
import java.util.*;


@Order(2)
@WebFilter(filterName = "authFilter", urlPatterns = "/*")
@Component
public class authfilter implements Filter {

    private static final Set<String> ALLOWED_PATHS = Collections.unmodifiableSet(
            new HashSet<>(Arrays.asList("/admin/register", "/admin/login")));

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
        String token = httpRequest.getHeader("Authorization");
        String username = httpRequest.getHeader("Username");
        String path = httpRequest.getRequestURI().substring(httpRequest.getContextPath().length()).replaceAll("[/]+$", "");
        if (ALLOWED_PATHS.contains(path)) {
            chain.doFilter(request, response);
            return;
        }
        if (authService.validateToken(username, token)) {
            chain.doFilter(request, response);
        } else {
            throw new ServletException("Invalid token");
        }
    }
}