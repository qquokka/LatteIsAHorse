package com.latte.dto;

import java.io.Serializable;

public class TestUser implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id;
	private String pwd;

	public TestUser(String id, String pwd) {
		super();
		this.id = id;
		this.pwd = pwd;
	}

	public TestUser() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

}
