package com.latte.dto;

import java.io.Serializable;
import java.sql.Date;

public class PostDto implements Serializable {
	private static final long serialVersionUID = 1L;

	private int post_id;
	private String post_title;
	private String post_content;
	private String post_writer;
	private Date posted_date;

	public PostDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PostDto(int post_id, String post_title, String post_content, String post_writer, Date posted_date) {
		super();
		this.post_id = post_id;
		this.post_title = post_title;
		this.post_content = post_content;
		this.post_writer = post_writer;
		this.posted_date = posted_date;
	}

	@Override
	public String toString() {
		return "Post [post_id=" + post_id + ", post_title=" + post_title + ", post_content=" + post_content
				+ ", post_writer=" + post_writer + ", posted_date=" + posted_date + "]";
	}

	public int getPost_id() {
		return post_id;
	}

	public void setPost_id(int post_id) {
		this.post_id = post_id;
	}

	public String getPost_title() {
		return post_title;
	}

	public void setPost_title(String post_title) {
		this.post_title = post_title;
	}

	public String getPost_content() {
		return post_content;
	}

	public void setPost_content(String post_content) {
		this.post_content = post_content;
	}

	public String getPost_writer() {
		return post_writer;
	}

	public void setPost_writer(String post_writer) {
		this.post_writer = post_writer;
	}

	public Date getPosted_date() {
		return posted_date;
	}

	public void setPosted_date(Date posted_date) {
		this.posted_date = posted_date;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
