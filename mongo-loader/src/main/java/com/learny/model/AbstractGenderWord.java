package com.learny.model;

public class AbstractGenderWord extends AbstractDefaultWord {

    public static final String GENDER_COLUMN = "GENDER";

    private Gender gender;

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        AbstractGenderWord abstractGenderWord = (AbstractGenderWord) obj;
        boolean isGenderEquals = false;
        if ((this.getGender() == null && abstractGenderWord.getGender() == null)
                || (this.getGender() != null && this.getGender().equals(abstractGenderWord.getGender()))) {
            isGenderEquals = true;
        }
        return super.equals(abstractGenderWord) && isGenderEquals;

    }
}
