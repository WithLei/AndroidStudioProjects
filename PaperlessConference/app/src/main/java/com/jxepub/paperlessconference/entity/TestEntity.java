package com.jxepub.paperlessconference.entity;

import java.io.Serializable;

public class TestEntity implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String name;
	String url;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public TestEntity(String name, String url) {
		super();
		this.name = name;
		this.url = url;
	}

}
