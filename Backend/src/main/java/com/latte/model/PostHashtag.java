package com.latte.model;

import java.io.Serializable;

public class PostHashtag implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long posts_id;
	private Integer hashtag_id;

	public PostHashtag() {
		super();
	}

	public PostHashtag(Long posts_id, Integer hashtag_id) {
		super();
		this.posts_id = posts_id;
		this.hashtag_id = hashtag_id;
	}

	public Long getPosts_id() {
		return posts_id;
	}

	public void setPosts_id(Long posts_id) {
		this.posts_id = posts_id;
	}

	public Integer getHashtag_id() {
		return hashtag_id;
	}

	public void setHashtag_id(Integer hashtag_id) {
		this.hashtag_id = hashtag_id;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
