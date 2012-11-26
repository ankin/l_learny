package com.learny.persistence.entity;

import java.util.Set;

import com.learny.persistence.entity.core.ModificationDateEntity;

public class Group extends ModificationDateEntity {

    private String name;

    private School school;

    private Set<User> docents;

    private Set<User> students;

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

    public Set<User> getDocents() {
        return docents;
    }

    public void setDocents(Set<User> docents) {
        this.docents = docents;
    }

    public Set<User> getStudents() {
        return students;
    }

    public void setStudents(Set<User> students) {
        this.students = students;
    }

}
