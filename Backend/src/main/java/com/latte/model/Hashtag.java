package com.latte.model;

import java.io.Serializable;

public class Hashtag implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer hashtag_id;
	private String name; // varchar(100) not null, -- 해쉬테그 이름

	public Hashtag() {
		super();
	}

	public Hashtag(Integer hashtag_id, String name) {
		super();
		this.hashtag_id = hashtag_id;
		this.name = name;
	}

	public Integer getHashtag_id() {
		return hashtag_id;
	}

	public void setHashtag_id(Integer hashtag_id) {
		this.hashtag_id = hashtag_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
