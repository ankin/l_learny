package com.learny.persistence.entity;

import javax.xml.bind.annotation.XmlRootElement;

import com.learny.persistence.entity.core.ModificationDateEntity;

@XmlRootElement
public class Comment extends ModificationDateEntity {

    private User user;

    private String text;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
