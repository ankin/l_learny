package com.learny.dto;

import java.io.Serializable;

import com.learny.persistence.entity.vocabulary.Gender;
import com.learny.persistence.entity.vocabulary.Type;

public class Translation implements Serializable {

    private static final long serialVersionUID = -559066764138295084L;

    private String value;

    private String extended;

    private Gender gender;

    private Type type;

    public Translation(String value, String extended, Gender gender, Type type) {
        super();
        this.value = value;
        this.extended = extended;
        this.gender = gender;
        this.type = type;
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

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

}
