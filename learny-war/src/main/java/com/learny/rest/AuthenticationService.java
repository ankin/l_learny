package com.learny.rest;

import java.io.IOException;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.learny.ejb.service.exception.LoginException;
import com.learny.ejb.service.local.UserBeanLocal;
import com.learny.persistence.entity.User;

@Path(AuthenticationService.PATH)
@RequestScoped
public class AuthenticationService {

    private final static Logger LOGGER = LogManager.getLogger(AuthenticationService.class);

    public final static String PATH = "/authentication";

    public final static String LOGIN_PATH = "/login";

    public static final String LOGGED_IN_USER = "LoggedInUser";

    @Inject
    private UserBeanLocal userBean;

    @Context
    private HttpServletRequest httpRequest;

    @Context
    private HttpServletResponse httpResponse;

    @Path(LOGIN_PATH)
    @POST
    public void login(@FormParam("email") String email, @FormParam("password") String password) throws LoginException {
        User user = userBean.login(email, password);
        HttpSession httpSession = httpRequest.getSession(true);
        httpSession.setAttribute(LOGGED_IN_USER, user);
        try {
            httpResponse.sendRedirect(httpRequest.getContextPath());
        } catch (IOException e) {
            LOGGER.error("Failed to redired to " + httpRequest.getContextPath(), e);
            throw new LoginException();
        }
    }

}
