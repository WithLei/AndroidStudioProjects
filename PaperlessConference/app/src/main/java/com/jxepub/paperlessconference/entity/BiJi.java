package com.jxepub.paperlessconference.entity;

public class BiJi{
	String name;// Ԫ����
	String path;// �ļ�·��
	String cover;// ����

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getCover() {
		return cover;
	}

	public void setCover(String cover) {
		this.cover = cover;
	}

	public BiJi(String name, String path, String cover) {
		super();
		this.name = name;
		this.path = path;
		this.cover = cover;
	}

}
