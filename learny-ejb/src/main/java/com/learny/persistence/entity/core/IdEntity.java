package com.learny.persistence.entity.core;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.xml.bind.annotation.XmlTransient;

@MappedSuperclass
public class IdEntity {

    public final static String ID = "ID";

    public final static String UNDERSCORE = "_";

    @XmlTransient
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = ID, nullable = false)
    private Long id;

    @Column(name = "UUID", nullable = false)
    private final String uuid;

    public IdEntity() {
        this.uuid = UUID.randomUUID().toString();
    }

    public Long getId() {
        return id;
    }

    public String getUuid() {
        return uuid;
    }

}
