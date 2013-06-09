package com.learny.persistence.entity.core;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class UuidEntity extends IdEntity {

    private static final long serialVersionUID = 5218711029922418065L;

    public final static String ID = "ID";

    public final static String UNDERSCORE = "_";

    @Column(name = "UUID", nullable = false, unique = true)
    private String uuid;

    public UuidEntity() {
        this.uuid = UUID.randomUUID().toString();
    }

    public String getUuid() {
        return uuid;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        UuidEntity otherObj = (UuidEntity) obj;
        return this.getUuid().equals(otherObj.getUuid());
    }

    @Override
    public final int hashCode() {
        return getUuid().hashCode();
    }

}
