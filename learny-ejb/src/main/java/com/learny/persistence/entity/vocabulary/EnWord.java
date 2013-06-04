package com.learny.persistence.entity.vocabulary;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = EnWord.TABLE_NAME, uniqueConstraints = @UniqueConstraint(columnNames = { AbstractDefaultWord.VALUE_COLUMN,
        AbstractDefaultWord.DESC_COLUMN }))
public class EnWord extends AbstractDefaultWord {

    private static final long serialVersionUID = -7298810756827186978L;

    public final static String TABLE_NAME = "EN_WORD";

}
