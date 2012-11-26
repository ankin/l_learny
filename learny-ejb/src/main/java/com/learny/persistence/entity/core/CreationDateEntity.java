package com.learny.persistence.entity.core;

import java.util.Date;

public class CreationDateEntity extends IdEntity {

    private Date dateCreated;

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

}
