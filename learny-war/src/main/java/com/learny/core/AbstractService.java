package com.learny.core;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Context;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.learny.persistence.entity.User;

public class AbstractService {

    private final static Logger LOGGER = LogManager.getLogger(AbstractService.class);

    public static final String LOGGED_IN_USER = "LoggedInUser";

    @Context
    private HttpServletRequest httpRequest;

    @Context
    private HttpServletResponse httpResponse;

    protected void redirectToRoot() throws IOException {
        try {
            httpResponse.sendRedirect(httpRequest.getContextPath());
        } catch (IOException e) {
            String errorMsg = "Failed to redired to " + httpRequest.getContextPath();
            LOGGER.error(errorMsg, e);
            throw e;

        }
    }

    protected User getSessionUser() {
        HttpSession httpSession = httpRequest.getSession(false);
        if (httpSession == null) {
            return null;
        } else {
            return (User) httpSession.getAttribute(LOGGED_IN_USER);
        }
    }

    protected void setSessionUser(User user) {
        User existingSessionUser = getSessionUser();
        if (existingSessionUser == null) {
            HttpSession httpSession = httpRequest.getSession(true);
            httpSession.setAttribute(LOGGED_IN_USER, user);
        } else {
            String errorMsg = "User can't be set in session. There is alredy user in session: "
                    + existingSessionUser.getEmail();
            LOGGER.error(errorMsg);
            assert false : errorMsg;
        }
    }
}
