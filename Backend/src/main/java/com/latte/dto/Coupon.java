package com.latte.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class Coupon implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id; // bigint(20) NOT NULL AUTO_INCREMENT,
	private Long users_id;
	private Integer cafe_id;
	private int count;

	public Coupon() {
		super();
	}

	public Coupon(Long id, Long users_id, Integer cafe_id, int count) {
		super();
		this.id = id;
		this.users_id = users_id;
		this.cafe_id = cafe_id;
		this.count = count;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUsers_id() {
		return users_id;
	}

	public void setUsers_id(Long users_id) {
		this.users_id = users_id;
	}

	public Integer getCafe_id() {
		return cafe_id;
	}

	public void setCafe_id(Integer cafe_id) {
		this.cafe_id = cafe_id;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
