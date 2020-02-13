package com.latte.payload;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class UserRoleUpdateRequest {
	private Long id;

	private Long role_id;

	@NotBlank
	private String role;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRole() {
		return role.toUpperCase();
	}

	public void setRole(String role) {
		this.role = role.toUpperCase();
	}

	public Long getRole_id() {
		return role_id;
	}

	public void setRole_id(Long role_id) {
		this.role_id = role_id;
	}

}
