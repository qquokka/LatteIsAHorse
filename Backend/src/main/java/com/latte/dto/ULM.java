package com.latte.dto;

import java.io.Serializable;

public class ULM implements Serializable {

	private static final long serialVersionUID = 1L;

	private int menu_id;
	private int like_count;

	public ULM() {
		super();
	}

	public int getMenu_id() {
		return menu_id;
	}

	public void setMenu_id(int menu_id) {
		this.menu_id = menu_id;
	}

	public int getLike_count() {
		return like_count;
	}

	public void setLike_count(int like_count) {
		this.like_count = like_count;
	}

	public ULM(int menu_id, int like_count) {
		super();
		this.menu_id = menu_id;
		this.like_count = like_count;
	}

}
