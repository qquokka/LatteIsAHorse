package com.latte.dto;

import java.io.Serializable;
import java.sql.Date;

public class Member implements Serializable {
	
	private static final long serialVersionUID = 4897905121165107128L;
	private String email; // 회원의 이메일 주소(ID) varchar(100) primary key NOT NULL ,
	private String mem_pwd; // 회원의 패스워드 varchar(30) NOT NULL,
	private String mem_name; // 회원 이름 varchar(45) NOT NULL,
	private String mem_phone; // 회원 연락처 varchar(45) NOT NULL,
	private int mem_auth; // 회원 권한 int NOT NULL, --
	private int mem_active; // 회원 탈퇴 여부 tinyint(1) NOT NULL DEFAULT 0,
	private String mem_nick_name; // 회원의 닉네임 varchar(45) DEFAULT NULL,
	private Date signed_up_date; // 회원 가입일 연.월.일 date not null

	public Member() {
		super();
	}

	public Member(String email, String mem_pwd, String mem_name, String mem_phone, int mem_auth, int mem_active,
			String mem_nick_name, Date signed_up_date) {
		super();
		this.email = email;
		this.mem_pwd = mem_pwd;
		this.mem_name = mem_name;
		this.mem_phone = mem_phone;
		this.mem_auth = mem_auth;
		this.mem_active = mem_active;
		this.mem_nick_name = mem_nick_name;
		this.signed_up_date = signed_up_date;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMem_pwd() {
		return mem_pwd;
	}

	public void setMem_pwd(String mem_pwd) {
		this.mem_pwd = mem_pwd;
	}

	public String getMem_name() {
		return mem_name;
	}

	public void setMem_name(String mem_name) {
		this.mem_name = mem_name;
	}

	public String getMem_phone() {
		return mem_phone;
	}

	public void setMem_phone(String mem_phone) {
		this.mem_phone = mem_phone;
	}

	public int getMem_auth() {
		return mem_auth;
	}

	public void setMem_auth(int mem_auth) {
		this.mem_auth = mem_auth;
	}

	public int getMem_active() {
		return mem_active;
	}

	public void setMem_active(int mem_active) {
		this.mem_active = mem_active;
	}

	public String getMem_nick_name() {
		return mem_nick_name;
	}

	public void setMem_nick_name(String mem_nick_name) {
		this.mem_nick_name = mem_nick_name;
	}

	public Date getSigned_up_date() {
		return signed_up_date;
	}

	public void setSigned_up_date(Date signed_up_date) {
		this.signed_up_date = signed_up_date;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
