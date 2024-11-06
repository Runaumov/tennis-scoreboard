package com.runaumov.filter;

import com.runaumov.exceptions.DatabaseAccessException;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class ServlerFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        httpServletResponse.setCharacterEncoding("UTF-8");

        try {
            filterChain.doFilter(request, response);
        } catch (DatabaseAccessException e) {
            handleException(httpServletRequest, httpServletResponse, e.getMessage(), HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    private void handleException(HttpServletRequest request, HttpServletResponse response, String message, int statusCode) throws IOException, ServletException {
        request.setAttribute("errorMessage", message);
        request.setAttribute("statusCode", statusCode);
        response.setStatus(statusCode);
        request.getRequestDispatcher("/error.jsp").forward(request, response);;
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
