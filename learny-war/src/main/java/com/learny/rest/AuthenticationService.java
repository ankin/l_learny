package com.learny.rest;

import java.io.IOException;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.learny.core.AbstractService;
import com.learny.ejb.service.exception.LoginException;
import com.learny.ejb.service.local.UserBeanLocal;
import com.learny.persistence.entity.User;

@Path(AuthenticationService.PATH)
@RequestScoped
public class AuthenticationService extends AbstractService {

    public final static String PATH = "/authentication";

    public final static String LOGIN_PATH = "/login";

    @Inject
    private UserBeanLocal userBean;

    @Path(LOGIN_PATH)
    @POST
    public void login(@FormParam("email") String email, @FormParam("password") String password) throws LoginException {
        User user = userBean.login(email, password);
        setSessionUser(user);
        try {
            redirectToRoot();
        } catch (IOException e) {
            throw new LoginException();
        }
    }

}
