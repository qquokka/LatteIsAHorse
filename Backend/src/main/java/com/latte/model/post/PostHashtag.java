package com.latte.model.post;

import java.io.Serializable;

public class PostHashtag implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id; // bigint(20) NOT NULL AUTO_INCREMENT,
	private Long post_id; // bigint(20) not null, -- 게시물의 id
	private String name; // varchar(100) not null, -- 해쉬테그 이름

	public PostHashtag() {
		super();
	}

	public PostHashtag(String name) {
		super();
		this.name = name;
	}

	public PostHashtag(Long id, Long post_id, String name) {
		super();
		this.id = id;
		this.post_id = post_id;
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPost_id() {
		return post_id;
	}

	public void setPost_id(Long post_id) {
		this.post_id = post_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
