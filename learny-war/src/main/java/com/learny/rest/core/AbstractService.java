package com.learny.rest.core;

import org.apache.shiro.SecurityUtils;

public abstract class AbstractService {


    protected String getCurrentUserEmail(){
        if (SecurityUtils.getSubject() == null) {
            return null;
        }
        return (String) SecurityUtils.getSubject().getSession().getAttribute("email");
    }
}
