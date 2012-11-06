package com.learny.persistence.entity;

import java.util.UUID;

public class Id {

	private Long id;

	private final String uuid = UUID.randomUUID().toString();

	public Long getId() {
		return id;
	}

	public String getUuid() {
		return uuid;
	}

}
