package com.learny.rest.core;

import org.apache.shiro.SecurityUtils;

import com.learny.persistence.entity.User;

public abstract class AbstractService {

    protected final static String EMAIL_ATTR_KEY = "USER_EMAIL";


    protected User getCurrentUser() {
        if (SecurityUtils.getSubject() == null) {
            return null;
        }
        return (User) SecurityUtils.getSubject().getSession().getAttribute(EMAIL_ATTR_KEY);
    }
}
