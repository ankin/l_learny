package com.learny.ejb.service.exception;

import javax.ejb.ApplicationException;

@ApplicationException(rollback = true)
public class LoginException extends Exception {

    private static final long serialVersionUID = -709288376860600037L;

    //TODO: Add i18n messages support

}
