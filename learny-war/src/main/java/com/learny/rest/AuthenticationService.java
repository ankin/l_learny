package com.learny.rest;

import java.io.IOException;

import javax.enterprise.context.RequestScoped;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.learny.ejb.service.exception.LoginException;
import com.learny.rest.core.AbstractService;
import com.learny.util.LearnyProperties;

@Path(AuthenticationService.PATH)
@RequestScoped
public class AuthenticationService extends AbstractService {

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationService.class);

    public static final String PATH = "/authentication";

    public static final String LOGIN_PATH = "/login";

    public static final String LOGOUT_PATH = "/logout";

    public static final String LOGIN_PAGE_PATH = "/login.html";

    @Context
    private HttpServletRequest httpRequest;

    @Context
    private HttpServletResponse httpResponse;

    @POST
    @Path(LOGIN_PATH)
    public void login(@FormParam("username") String username, @FormParam("password") String password)
            throws LoginException {

        // reassigning method parameters is not good, this is done only in testing purposes 
        if (LearnyProperties.isLoginWithoutCredentialsEnabled()) {
            username = LearnyProperties.getLoginWithoutCredentialsEmail();
            password = LearnyProperties.getLoginWithoutCredentialsPassword();
        }
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);

        Subject currentUser = SecurityUtils.getSubject();
        currentUser.login(token);
        currentUser.getSession(true).setAttribute(EMAIL_ATTR_KEY, currentUser.getPrincipal());
        try {
            String url = httpRequest.getContextPath();
            redirect(url);
        } catch (IOException e) {
            throw new LoginException();
        }
    }

    @GET
    @Path(LOGOUT_PATH)
    public void logout() throws LoginException {
        try {
            SecurityUtils.getSubject().logout();
            if (httpRequest.getSession() != null) {
                httpRequest.getSession().invalidate();
            }
            String url = httpRequest.getContextPath() + LOGIN_PAGE_PATH;
            redirect(url);
        } catch (IOException e) {
            throw new LoginException(); // TODO:something else should be thrown
        }
    }

    private void redirect(String url) throws IOException {
        try {
            httpResponse.sendRedirect(url);
        } catch (IOException e) {
            String errorMsg = "Failed to redired to " + url;
            logger.error(errorMsg, e);
            throw e;

        }
    }
}
