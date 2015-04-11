package com.learny.model;

import java.util.Date;

public class CreationDateEntity extends BaseEntity {

    private Date dateCreated;

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public void initCreatedDate() {
        dateCreated = new Date();
    }

}
