package com.latte.dto;

import java.io.Serializable;

public class ULMCUID implements Serializable {
	private static final long serialVersionUID = 1L;
	private int cafe_id;
	private long users_id;

	public ULMCUID() {
		super();
	}

	public ULMCUID(int cafe_id, long users_id) {
		super();
		this.cafe_id = cafe_id;
		this.users_id = users_id;
	}

	public int getCafe_id() {
		return cafe_id;
	}

	public void setCafe_id(int cafe_id) {
		this.cafe_id = cafe_id;
	}

	public long getUsers_id() {
		return users_id;
	}

	public void setUsers_id(long users_id) {
		this.users_id = users_id;
	}

}
