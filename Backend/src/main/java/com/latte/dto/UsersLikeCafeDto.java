package com.latte.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class UsersLikeCafeDto implements Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private Long users_id;
	private int cafe_id;
	private int like_count;
	
	public UsersLikeCafeDto() {
		super();
	}

	public UsersLikeCafeDto(int id, Long users_id, int cafe_id, int like_count) {
		this.id = id;
		this.users_id = users_id;
		this.cafe_id = cafe_id;
		this.like_count = like_count;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public Long getUsers_id() {
		return users_id;
	}

	public void setUsers_id(Long users_id) {
		this.users_id = users_id;
	}

	public int getCafe_id() {
		return cafe_id;
	}

	public void setCafe_id(int cafe_id) {
		this.cafe_id = cafe_id;
	}

	public int getLike_count() {
		return like_count;
	}

	public void setLike_count(int like_count) {
		this.like_count = like_count;
	}
	
}
