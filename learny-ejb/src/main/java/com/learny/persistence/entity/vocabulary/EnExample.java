package com.learny.persistence.entity.vocabulary;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = EnExample.TABLE_NAME)
public class EnExample extends AbstractExample {

    private static final long serialVersionUID = -7687975103893777258L;

    public final static String TABLE_NAME = "EN_EXAMPLE";

}
