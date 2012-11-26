package com.learny.persistence.entity;

import javax.xml.bind.annotation.XmlRootElement;

import com.learny.persistence.entity.core.IdEntity;

@XmlRootElement
public class Rule extends IdEntity {

    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
