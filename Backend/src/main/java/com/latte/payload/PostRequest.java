package com.latte.payload;

import java.io.Serializable;

public class PostRequest implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer pageNum = 0;
	private String username = "";
	private Boolean likest = false;
	private Boolean newest = false;

	public PostRequest() {
		super();
	}

	public PostRequest(Integer pageNum, String username, Boolean likest, Boolean newest) {
		super();
		this.pageNum = pageNum;
		this.username = username;
		this.likest = likest;
		this.newest = newest;
	}

	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Boolean getLikest() {
		return likest;
	}

	public void setLikest(Boolean likest) {
		this.likest = likest;
	}

	public Boolean getNewest() {
		return newest;
	}

	public void setNewest(Boolean newest) {
		this.newest = newest;
	}

}
