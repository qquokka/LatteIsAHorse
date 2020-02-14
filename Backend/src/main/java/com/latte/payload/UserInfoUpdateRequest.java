package com.latte.payload;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/*
As stated in problem, to solve this error you MUST use correct annotations. 
In above problem, @NotBlank annotation must be applied on any String field only.
To validate long type field, use annotation @NotNull.
*/
public class UserInfoUpdateRequest {

	private Long id;

	@NotBlank
	private String name;

	@NotBlank
	@Size(max = 200)
	private String phone;

	@NotBlank
	@Size(max = 100) // 15자 하려면 15입니까...
	private String username;

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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public UserInfoUpdateRequest() {
		super();
	}

	public UserInfoUpdateRequest(Long id, @NotBlank String name, @NotBlank @Size(max = 200) String phone,
			@NotBlank @Size(max = 100) String username) {
		super();
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.username = username;
	}

	@Override
	public String toString() {
		return "UserInfoUpdateRequest [id=" + id + ", name=" + name + ", phone=" + phone + ", username=" + username
				+ "]";
	}

}
