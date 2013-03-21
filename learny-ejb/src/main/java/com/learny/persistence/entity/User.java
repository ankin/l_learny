package com.learny.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.learny.persistence.entity.core.IdEntity;

@Entity
@Table(name = User.TABLE_NAME)
@NamedQueries(@NamedQuery(name = User.QUERY_BY_EMAIL, query = "select u from User u where u.email=:"
        + User.PARAM_EMAIL))
public class User extends IdEntity {

    private static final long serialVersionUID = 1652908666766597691L;

    public final static String TABLE_NAME = "USER";

    public final static String QUERY_BY_EMAIL = "User.queryByEmail";
    public final static String PARAM_EMAIL = "email";

    @Column(name = "EMAIL", nullable = false, unique = true)
    private String email;

    @JsonIgnore
    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Column(name = "FIRST_NAME", nullable = false)
    private String firstName;

    @Column(name = "LAST_NAME", nullable = false)
    private String lastName;

    @JsonIgnore
    @ManyToOne(targetEntity = Address.class, fetch = FetchType.LAZY)
    @JoinColumn(name = Address.TABLE_NAME + UNDERSCORE + ID, nullable = true)
    private Address address;

    @JsonIgnore
    @Column(name = "ROLE", nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    @Transient
    private Group group;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public String getDisplayName() {
        return firstName + " " + lastName;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

}
