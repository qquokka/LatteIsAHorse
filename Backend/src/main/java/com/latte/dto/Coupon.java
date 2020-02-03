package com.latte.dto;

import java.io.Serializable;

public class Coupon implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id; // bigint(20) NOT NULL AUTO_INCREMENT,
	private Long users_id;
	private Long cafe_id;
	private Integer count;

	public Coupon() {
		super();
	}

	public Coupon(Long id, Long users_id, Long cafe_id, Integer count) {
		super();
		this.id = id;
		this.users_id = users_id;
		this.cafe_id = cafe_id;
		this.count = count;
	}

	public Long getId() {
		return id;
	}

	public Long getUsers_id() {
		return users_id;
	}

	public void setUsers_id(Long users_id) {
		this.users_id = users_id;
	}

	public Long getCafe_id() {
		return cafe_id;
	}

	public void setCafe_id(Long cafe_id) {
		this.cafe_id = cafe_id;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

}
