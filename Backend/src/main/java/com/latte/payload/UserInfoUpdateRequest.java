package com.latte.payload;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UserInfoUpdateRequest {

	@NotBlank
	private Long id;

	@NotBlank
	private String name;

	@NotBlank
	@Size(max = 200)
	private String phone;

	@NotBlank
	private String role;

	@NotBlank
	private Boolean active;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

}
