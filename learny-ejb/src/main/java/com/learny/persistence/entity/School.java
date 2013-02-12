package com.learny.persistence.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.learny.persistence.entity.core.CreationDateEntity;

@Entity
@Table(name = School.TABLE_NAME)
public class School extends CreationDateEntity {

    private static final long serialVersionUID = 5733878609119058102L;

    public final static String TABLE_NAME = "SCHOOL";

    @Column(name = "NAME")
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @JsonIgnore
    @ManyToOne(targetEntity = Address.class, fetch = FetchType.LAZY)
    @JoinColumn(name = Address.TABLE_NAME + UNDERSCORE + ID, nullable = false)
    private Address address;

    @OneToMany(targetEntity = Group.class, fetch = FetchType.LAZY, mappedBy = "school")
    private Set<Group> groups;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Set<Group> getGroups() {
        return groups;
    }

    public void setGroups(Set<Group> groups) {
        this.groups = groups;
    }

}
