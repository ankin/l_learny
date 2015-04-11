package com.learny.model;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Id;

public class BaseEntity {

    @Id
    private ObjectId objectId = new ObjectId();

    public ObjectId getObjectId() {
        return objectId;
    }

    public void setObjectId(ObjectId objectId) {
        this.objectId = objectId;
    }
}
