package com.latte.dto;

import java.io.Serializable;
import java.sql.Date;

public class CommentDto implements Serializable {
	private static final long serialVersionUID = 1L;

	private int comment_id;
	private int post_id;
	private String comment_content;
	private String comment_writer;
	private Date posted_date;

	public CommentDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CommentDto(int comment_id, int post_id, String comment_content, String comment_writer,
			Date posted_date) {
		super();
		this.comment_id = comment_id;
		this.post_id = post_id;
		this.comment_content = comment_content;
		this.comment_writer = comment_writer;
		this.posted_date = posted_date;
	}

	@Override
	public String toString() {
		return "Post [comment_id=" + comment_id + ", post_id=" + post_id + ", comment_content=" + comment_content
				+ ", comment_writer=" + comment_writer + ", posted_date=" + posted_date + "]";
	}

	public int getComment_id() {
		return comment_id;
	}

	public void setComment_id(int comment_id) {
		this.comment_id = comment_id;
	}

	public int getPost_id() {
		return post_id;
	}

	public void setPost_id(int post_id) {
		this.post_id = post_id;
	}

	public String getComment_content() {
		return comment_content;
	}

	public void setComment_content(String comment_content) {
		this.comment_content = comment_content;
	}

	public String getComment_writer() {
		return comment_writer;
	}

	public void setComment_writer(String comment_writer) {
		this.comment_writer = comment_writer;
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