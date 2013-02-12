package com.learny.persistence.entity;

import com.learny.persistence.entity.core.IdEntity;

public class Rule extends IdEntity {

    private static final long serialVersionUID = 799240082172805374L;

    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
