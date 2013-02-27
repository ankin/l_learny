package com.learny.rest.core;

import org.apache.shiro.SecurityUtils;

public abstract class AbstractService {

    protected final static String EMAIL_ATTR_KEY = "USER_EMAIL";


    protected String getCurrentUserEmail(){
        if (SecurityUtils.getSubject() == null) {
            return null;
        }
        return (String) SecurityUtils.getSubject().getSession().getAttribute(EMAIL_ATTR_KEY);
    }
}
