package com.learny.persistence.entity.vocabulary;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import com.learny.persistence.entity.core.IdEntity;

@MappedSuperclass
public class AbstractExample extends IdEntity {

    private static final long serialVersionUID = 3557519872205111947L;

    @Column(name = "VALUE", nullable = false)
    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
