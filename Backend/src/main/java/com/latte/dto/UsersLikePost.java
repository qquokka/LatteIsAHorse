package com.latte.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class UsersLikePost implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private Long user_id;
	private Long post_id;
	private Boolean like_count;

	public UsersLikePost() {
		super();
	}

	public UsersLikePost(Long id, Long user_id, Long post_id, Boolean like_count) {
		super();
		this.id = id;
		this.user_id = user_id;
		this.post_id = post_id;
		this.like_count = like_count;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

	public Long getPost_id() {
		return post_id;
	}

	public void setPost_id(Long post_id) {
		this.post_id = post_id;
	}

	public Boolean getLike_count() {
		return like_count;
	}

	public void setLike_count(Boolean like_count) {
		this.like_count = like_count;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
