package com.latte.payload;

import java.time.Instant;

public class CouponUseResponse {
	private String username;
	private String phone;
	private String email;
	private String product;
	private String created_at;

	public CouponUseResponse(String username, String phone, String email, String product, String created_at) {
		super();
		this.username = username;
		this.phone = phone;
		this.email = email;
		this.product = product;
		this.created_at = created_at;
	}

	public CouponUseResponse() {
		super();
	}

	public String getCreated_at() {
		return created_at;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

}
