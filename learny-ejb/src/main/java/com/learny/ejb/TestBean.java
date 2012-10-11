package com.learny.ejb;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Named;

@Stateless
@LocalBean
@Named
public class TestBean {
    
    public String getName() {
        return "TestBean test method call!!!";
    }
}
