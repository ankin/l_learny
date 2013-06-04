package com.learny.persistence.entity.vocabulary;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class AbstractGenderWord extends AbstractDefaultWord {

    private static final long serialVersionUID = 4357259868217746912L;

    public static final String GENDER_COLUMN = "GENDER";

    @Column(name = GENDER_COLUMN)
    @Enumerated(EnumType.STRING)
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
