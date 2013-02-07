package com.learny.persistence.entity.core;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PreUpdate;

@MappedSuperclass
public class ModificationDateEntity extends CreationDateEntity {

    @Column(name = "DATE_MODIFIED", nullable = false)
    private Date dateModified;

    public Date getDateModified() {
        return dateModified;
    }

    public void setDateModified(Date dateModified) {
        this.dateModified = dateModified;
    }

    @PreUpdate
    public void updateModifiedDate() {
        dateModified = new Date();
    }
}
