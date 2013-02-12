package com.learny.persistence.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.learny.persistence.entity.core.CreationDateEntity;

@Entity
@Table(name = Group.TABLE_NAME)
public class Group extends CreationDateEntity {

    private static final long serialVersionUID = 3991955313670372941L;

    public final static String TABLE_NAME = "GROUP";

    @Column(name = "NAME")
    private String name;

    @JsonIgnore
    @ManyToOne(targetEntity = School.class, fetch = FetchType.LAZY)
    @JoinColumn(name = School.TABLE_NAME + UNDERSCORE + ID, nullable = false)
    private School school;

    @JsonIgnore
    @ManyToMany(targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinTable(name = User.TABLE_NAME + UNDERSCORE + TABLE_NAME, joinColumns = { @JoinColumn(name = TABLE_NAME
            + UNDERSCORE + ID) }, inverseJoinColumns = { @JoinColumn(name = User.TABLE_NAME + UNDERSCORE + ID) })
    private Set<User> users;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

}
