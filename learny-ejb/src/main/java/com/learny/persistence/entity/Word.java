package com.learny.persistence.entity;

import com.learny.persistence.entity.core.IdEntity;

public class Word extends IdEntity {

    private static final long serialVersionUID = 9064223559376466447L;

    private String source;

	private String target;

	public Word() {

	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

}
