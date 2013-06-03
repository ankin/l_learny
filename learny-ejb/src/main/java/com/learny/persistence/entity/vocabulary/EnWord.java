package com.learny.persistence.entity.vocabulary;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = EnWord.TABLE_NAME)
public class EnWord extends AbstractWord {

    private static final long serialVersionUID = -7298810756827186978L;

    public final static String TABLE_NAME = "EN_WORD";

}
