package com.learny.persistence.entity.core;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.xml.bind.annotation.XmlTransient;

@MappedSuperclass
public class IdEntity implements Serializable {

    private static final long serialVersionUID = 2166631470723029483L;

    public final static String ID = "ID";

    public final static String UNDERSCORE = "_";

    @XmlTransient
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = ID, nullable = false, unique = true)
    private Long id;

    public Long getId() {
        return id;
    }

}
