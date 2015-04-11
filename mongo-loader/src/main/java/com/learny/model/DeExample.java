package com.learny.model;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Reference;

@Entity(value = "germanExample", noClassnameStored = true)
public class DeExample extends AbstractExample {

    public final static String TABLE_NAME = "DE_EXAMPLE";

    @Reference
    private EnExample enExample;

    public EnExample getEnExample() {
        return enExample;
    }

    public void setEnExample(EnExample en_example) {
        this.enExample = en_example;
    }

}
