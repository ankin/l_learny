package com.learny.persistence.entity;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Word extends Id {

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
