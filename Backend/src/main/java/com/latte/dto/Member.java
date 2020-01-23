package com.latte.dto;

import java.io.Serializable;

public class Member implements Serializable {
	private static final long serialVersionUID = 4897905121165107128L;
	private String mid; // user id
	private String pwd; // user password
	private String mname; // user name
	private String address; // user address
	private String phone; // user phone number
	private int auth; // authorization value
	private boolean del_flag; // sign in/out
	private String allergies;

	public Member() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Member(String mid, String pwd, String mname, String address, String phone, int auth, boolean del_flag) {
		super();
		this.mid = mid;
		this.pwd = pwd;
		this.mname = mname;
		this.address = address;
		this.phone = phone;
		this.auth = auth;
		this.del_flag = del_flag;
	}

	public Member(String mid, String pwd, String mname, String address, String phone, int auth, boolean del_flag,
			String allergies) {
		super();
		this.mid = mid;
		this.pwd = pwd;
		this.mname = mname;
		this.address = address;
		this.phone = phone;
		this.auth = auth;
		this.del_flag = del_flag;
		this.allergies = allergies;
	}

	public String getAllergies() {
		return allergies;
	}

	public void setAllergies(String allergies) {
		this.allergies = allergies;
	}

	@Override
	public String toString() {
		return "Member [mid=" + mid + ", pwd=" + pwd + ", mname=" + mname + ", address=" + address + ", phone=" + phone
				+ ", auth=" + auth + ", del_flag=" + del_flag + "]";
	}

	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getMname() {
		return mname;
	}

	public void setMname(String mname) {
		this.mname = mname;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getAuth() {
		return auth;
	}

	public void setAuth(int auth) {
		this.auth = auth;
	}

	public boolean isDel_flag() {
		return del_flag;
	}

	public void setDel_flag(boolean del_flag) {
		this.del_flag = del_flag;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
