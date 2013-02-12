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
import javax.servlet.http.HttpSession;

import com.learny.persistence.entity.User;
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

    @Override
    public void destroy() {
        // TODO Auto-generated method stub
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException,
            ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        if (httpRequest.getServletPath().endsWith(".css")) { // TODO: make it better
            filterChain.doFilter(request, response);
            return;
        }

        HttpSession httpSession = httpRequest.getSession(false);

        if (httpSession == null) {
            if (isLoginAction(httpRequest)) {
                filterChain.doFilter(request, response);
            } else {
                redirectToLoginPage(httpRequest, httpResponse);
            }
            return;
        }

        User user = (User) httpSession.getAttribute(AuthenticationService.LOGGED_IN_USER);
        if (user == null && isLoginAction(httpRequest)) { // no user is session and login action
            filterChain.doFilter(request, response);
        } else if (user == null) {
            redirectToLoginPage(httpRequest, httpResponse); // no user in session, but not login action
        } else {
            filterChain.doFilter(request, response); // there is user in session and not login action
        }
    }

    private void redirectToLoginPage(HttpServletRequest httpRequest, HttpServletResponse httpResponse)
            throws IOException {
        String requestUri = httpRequest.getRequestURI();
        if (requestUri.contains(LearnyApplication.PATH)) {// Used as a tip for JS to redirect when AJAX request is submitted
            httpResponse.setHeader(REDIRECT_TO_HEADER_KEY, LOGIN_PAGE);
        } else {
            httpResponse.sendRedirect(httpRequest.getContextPath() + LOGIN_PAGE_FULL);
        }
    }

    private boolean isLoginAction(HttpServletRequest httpRequest) {
        String path = httpRequest.getServletPath();
        String requestUri = httpRequest.getRequestURI();
        if (LOGIN_PAGE_FULL.equals(path) || requestUri.endsWith(LOGIN_SERVICE)) {
            return true;
        }
        return false;
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {
        // TODO Auto-generated method stub

    }

}
