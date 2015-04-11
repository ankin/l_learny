package com.learny.model;

import org.mongodb.morphia.annotations.Entity;

@Entity(value = "englishWords", noClassnameStored = true)
public class EnWord extends AbstractDefaultWord {

    public final static String TABLE_NAME = "EN_WORD";

}
