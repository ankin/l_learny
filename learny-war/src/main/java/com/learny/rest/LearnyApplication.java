package com.learny.rest;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath(LearnyApplication.PATH)
public class LearnyApplication extends Application {

    public static final String PATH = "/services";

}
