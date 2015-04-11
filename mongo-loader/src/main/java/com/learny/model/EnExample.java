package com.learny.model;

import org.mongodb.morphia.annotations.Entity;

@Entity(value = "englishExample", noClassnameStored = true)
public class EnExample extends AbstractExample {

    public final static String TABLE_NAME = "EN_EXAMPLE";

}
