package com.latte.model.post;

import java.io.Serializable;

public class PostHashtagName implements Serializable {

	private static final long serialVersionUID = 1L;

	private String name; // varchar(100) not null, -- 해쉬테그 이름

	public PostHashtagName() {
		super();
	}

	public PostHashtagName(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
