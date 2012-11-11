package com.learny.persistence.entity;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Rule extends Id {

    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
