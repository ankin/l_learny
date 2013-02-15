package com.learny.security;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.learny.rest.AuthenticationService;
import com.learny.rest.LearnyApplication;

@WebFilter(urlPatterns = { "/*" })
public class AuthenticationFilter implements Filter {

    private static final String REDIRECT_TO_HEADER_KEY = "redirectTo";
    private static final String PATH_SEPARATOR = "/";
    private static final String LOGIN_PAGE = "login.html";
    private static final String LOGIN_PAGE_FULL = PATH_SEPARATOR + LOGIN_PAGE;

    private static final String LOGIN_SERVICE = LearnyApplication.PATH + AuthenticationService.PATH
            + AuthenticationService.LOGIN_PATH;
    private static final String LOGOUT_SERVICE = LearnyApplication.PATH + AuthenticationService.PATH
            + AuthenticationService.LOGOUT_PATH;

    @Override
    public void destroy() {
        // TODO Auto-generated method stub
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException,
            ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String uri = httpRequest.getRequestURI();
        if (uri != null && uri.equals("/learny/")) {
            if (httpRequest.getUserPrincipal() == null) {
                httpResponse.sendRedirect(httpRequest.getContextPath() + LOGIN_PAGE_FULL);
                return;
            }
        }
        filterChain.doFilter(httpRequest, httpResponse);
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {
        // TODO Auto-generated method stub

    }

}
