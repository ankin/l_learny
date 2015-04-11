package com.learny.model;


public class AbstractDefaultWord extends BaseEntity {

    private String value;

    private String description;

    private String abbreviation;

    private Type type;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        AbstractDefaultWord abstractWord = (AbstractDefaultWord) obj;
        if (this.getValue() == null || abstractWord.getValue() == null || this.getType() == null
                || abstractWord.getType() == null) {
            return false;
        }
        return this.getValue().equals(abstractWord.getValue()) && this.getType().equals(abstractWord.getType());
    }
}
