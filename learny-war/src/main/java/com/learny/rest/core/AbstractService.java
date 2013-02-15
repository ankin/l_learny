package com.learny.rest.core;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;

public abstract class AbstractService {

    @Context
    private HttpServletRequest httpRequest;

    protected String getCurrentUserEmail(){
        if (httpRequest.getUserPrincipal() == null) {
            return null;
        }
        return httpRequest.getUserPrincipal().getName();
    }
}
