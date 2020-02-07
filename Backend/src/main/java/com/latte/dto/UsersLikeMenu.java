package com.latte.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class UsersLikeMenu implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	private int like_count;
	private Long users_id;
	private int menu_id;
	private int cafe_id;

	public UsersLikeMenu() {
		super();
	}

	public UsersLikeMenu(int id, int like_count, Long users_id, int menu_id, int cafe_id) {
		this.id = id;
		this.like_count = like_count;
		this.users_id = users_id;
		this.menu_id = menu_id;
		this.cafe_id = cafe_id;
	}

	public UsersLikeMenu(int like_count, int menu_id) {
		this.like_count = like_count;
		this.menu_id = menu_id;
	}

	public int getCafe_id() {
		return cafe_id;
	}

	public void setCafe_id(int cafe_id) {
		this.cafe_id = cafe_id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getLike_count() {
		return like_count;
	}

	public void setLike_count(int like_count) {
		this.like_count = like_count;
	}

	public Long getUsers_id() {
		return users_id;
	}

	public void setUsers_id(Long users_id) {
		this.users_id = users_id;
	}

	public int getMenu_id() {
		return menu_id;
	}

	public void setMenu_id(int menu_id) {
		this.menu_id = menu_id;
	}

}
