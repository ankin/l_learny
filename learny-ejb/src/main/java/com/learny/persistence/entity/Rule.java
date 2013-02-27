package com.learny.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.learny.persistence.entity.core.IdEntity;

@Entity
@Table(name = Rule.TABLE_NAME)
public class Rule extends IdEntity {

    private static final long serialVersionUID = 799240082172805374L;

    public final static String TABLE_NAME = "RULE";

    @Column(name = "TEXT")
    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
