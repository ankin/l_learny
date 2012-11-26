package com.learny.persistence.entity.core;

import java.util.UUID;

import javax.xml.bind.annotation.XmlTransient;

public class IdEntity {

    @XmlTransient
	private Long id;

	private final String uuid = UUID.randomUUID().toString();

	public Long getId() {
		return id;
	}

	public String getUuid() {
		return uuid;
	}

}
