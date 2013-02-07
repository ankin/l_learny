package com.learny.persistence.entity.core;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;

@MappedSuperclass
public class CreationDateEntity extends IdEntity {

    @Column(name = "DATE_CREATED", nullable = false)
    private Date dateCreated;

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    @PrePersist
    public void initCreatedDate() {
        dateCreated = new Date();
    }

}
