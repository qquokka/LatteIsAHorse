package com.latte.dto;

import java.io.Serializable;
import java.sql.Date;
import java.time.Instant;

public class Member implements Serializable {

	private static final long serialVersionUID = 4897905121165107128L;
	private Long id; // bigint(20) NOT NULL AUTO_INCREMENT,
	private Instant created_at; // datetime NOT NULL, -- signed_up_date
	private Instant updated_at; // datetime NOT NULL,
	private String email; // varchar(40) DEFAULT NULL,
	private String name; // varchar(40) DEFAULT NULL, -- 사용자의 실명
	private String password; // varchar(100) DEFAULT NULL,
	private String username; // varchar(15) DEFAULT NULL, -- 사용자의 닉네임
	private String phone; // varchar(100) DEFAULT NULL,
	private Boolean active; // TINYINT(1) DEFAULT 1,

	public Member(Long id, Instant created_at, Instant updated_at, String email, String name, String password,
			String username, String phone, Boolean active) {
		super();
		this.id = id;
		this.created_at = created_at;
		this.updated_at = updated_at;
		this.email = email;
		this.name = name;
		this.password = password;
		this.username = username;
		this.phone = phone;
		this.active = active;
	}

	public Member() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Instant getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Instant created_at) {
		this.created_at = created_at;
	}

	public Instant getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(Instant updated_at) {
		this.updated_at = updated_at;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

}
