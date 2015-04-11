package com.learny.model;

import java.util.Date;

public class ModificationDateEntity extends CreationDateEntity {

    private Date dateModified;

    public Date getDateModified() {
        return dateModified;
    }

    public void setDateModified(Date dateModified) {
        this.dateModified = dateModified;
    }

    public void updateModifiedDate() {
        dateModified = new Date();
    }
}
