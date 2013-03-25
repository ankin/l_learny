package com.learny.dto;

import java.io.Serializable;

public class Translation implements Serializable {

    private static final long serialVersionUID = -559066764138295084L;

    private String value;

    private String extended;

    public Translation(String value, String extended) {
        super();
        this.value = value;
        this.extended = extended;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getExtended() {
        return extended;
    }

    public void setExtended(String extended) {
        this.extended = extended;
    }

}
