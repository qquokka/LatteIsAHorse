package com.latte.model.post;

import java.io.Serializable;

import com.latte.model.audit.DateAudit;

public class PostComments extends DateAudit implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id; // bigint(20) NOT NULL AUTO_INCREMENT,
	private Long post_id; // bigint(20) not null, -- 댓글이 달린 게시물 id
	private Long writer_id; // bigint(20) not null, -- 댓글이 작성자의 id
	private String content; // text not null, -- 댓글 내용

	private String writer_name;

	public PostComments() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PostComments(Long id, Long post_id, Long writer_id, String content) {
		super();
		this.id = id;
		this.post_id = post_id;
		this.writer_id = writer_id;
		this.content = content;
	}

	public PostComments(Long id, Long post_id, Long writer_id, String content, String writer_name) {
		super();
		this.id = id;
		this.post_id = post_id;
		this.writer_id = writer_id;
		this.content = content;
		this.writer_name = writer_name;
	}

	public String getWriter_name() {
		return writer_name;
	}

	public void setWriter_name(String writer_name) {
		this.writer_name = writer_name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPost_id() {
		return post_id;
	}

	public void setPost_id(Long post_id) {
		this.post_id = post_id;
	}

	public Long getWriter_id() {
		return writer_id;
	}

	public void setWriter_id(Long writer_id) {
		this.writer_id = writer_id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
