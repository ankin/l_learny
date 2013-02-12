package com.learny.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.learny.persistence.entity.core.ModificationDateEntity;

@Entity
@Table(name = Comment.TABLE_NAME)
public class Comment extends ModificationDateEntity {

    private static final long serialVersionUID = 8683537506574789085L;

    public final static String TABLE_NAME = "COMMENT";

    @ManyToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(name = User.TABLE_NAME + UNDERSCORE + ID, nullable = false)
    private User user;

    @Column(name = "TEXT", nullable = false)
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
