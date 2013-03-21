package com.learny.persistence.entity.core;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.xml.bind.annotation.XmlTransient;

@MappedSuperclass
public class IdEntity implements Serializable {

    private static final long serialVersionUID = 5218711029922418065L;

    public final static String ID = "ID";

    public final static String UNDERSCORE = "_";

    @XmlTransient
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = ID, nullable = false, unique = true)
    private Long id;

    @Column(name = "UUID", nullable = false, unique = true)
    private String uuid;

    public IdEntity() {
        this.uuid = UUID.randomUUID().toString();
    }

    public Long getId() {
        return id;
    }

    public String getUuid() {
        return uuid;
    }

    @Override
    public final boolean equals(Object obj) {
        IdEntity otherObj = (IdEntity) obj;
        return this.getUuid().equals(otherObj.getUuid());
    }
    
    @Override
    public final int hashCode() {
        return getUuid().hashCode();
    }

}
