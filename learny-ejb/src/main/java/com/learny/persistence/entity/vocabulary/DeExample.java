package com.learny.persistence.entity.vocabulary;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = DeExample.TABLE_NAME)
public class DeExample extends AbstractExample {

    private static final long serialVersionUID = -7687975103893777258L;

    public final static String TABLE_NAME = "DE_EXAMPLE";


    @OneToOne(fetch = FetchType.LAZY, targetEntity = EnExample.class, cascade = CascadeType.ALL)
    @JoinTable(name = TABLE_NAME + UNDERSCORE + EnExample.TABLE_NAME, joinColumns = { @JoinColumn(name = TABLE_NAME
            + UNDERSCORE + ID) }, inverseJoinColumns = { @JoinColumn(name = EnExample.TABLE_NAME + UNDERSCORE + ID) })
    private EnExample enExample;

    public EnExample getEnExample() {
        return enExample;
    }

    public void setEnExample(EnExample en_example) {
        this.enExample = en_example;
    }

}
