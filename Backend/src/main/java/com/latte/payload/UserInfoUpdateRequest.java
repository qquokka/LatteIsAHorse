package com.latte.payload;

import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/*
As stated in problem, to solve this error you MUST use correct annotations. 
In above problem, @NotBlank annotation must be applied on any String field only.
To validate long type field, use annotation @NotNull.
*/
public class UserInfoUpdateRequest {

	@NotNull
	private Long id;

	@NotBlank
	private String name;

	@NotBlank
	@Size(max = 200)
	private String phone;

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

}
