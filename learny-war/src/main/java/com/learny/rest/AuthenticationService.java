package com.learny.rest;

import java.io.IOException;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.RequestScoped;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.learny.ejb.service.exception.LoginException;

@Path(AuthenticationService.PATH)
@RequestScoped
public class AuthenticationService {

    private final static Logger LOGGER = LogManager.getLogger(AuthenticationService.class);

    public final static String PATH = "/authentication";

    public final static String LOGIN_PATH = "/login";

    public final static String LOGOUT_PATH = "/logout";

    @Context
    private HttpServletRequest httpRequest;

    @Context
    private HttpServletResponse httpResponse;

    @GET
    @Path(LOGOUT_PATH)
    @RolesAllowed({ "STUDENT", "DOCENT", "MANAGER" })
    public void logout() throws LoginException {
        try {
            httpRequest.logout();
            redirectToRoot();
        } catch (IOException e) {
            throw new LoginException(); //TODO:something else should be thrown
        } catch (ServletException e) {
            throw new LoginException(); //TODO:something else should be thrown
        }
    }

    protected void redirectToRoot() throws IOException {
        try {
            httpResponse.sendRedirect(httpRequest.getContextPath());
        } catch (IOException e) {
            String errorMsg = "Failed to redired to " + httpRequest.getContextPath();
            LOGGER.error(errorMsg, e);
            throw e;

        }
    }
}
