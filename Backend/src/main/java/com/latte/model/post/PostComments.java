package com.latte.model.post;

import java.io.Serializable;
import java.time.Instant;

public class PostComments implements Serializable {

	private static final long serialVersionUID = 1L;

	private Instant created_at;
	private Instant updated_at;
	private Long id; // id
	private Long post_id; // 댓글이 달린 게시물 id
	private Long writer_id; // 댓글이 작성자의 id
	private String content; // 댓글 내용
	private String writer_name; // username

	public PostComments() {
		super();
	}

	public PostComments(Instant created_at, Instant updated_at, Long id, Long post_id, Long writer_id, String content,
			String writer_name) {
		super();
		this.created_at = created_at;
		this.updated_at = updated_at;
		this.id = id;
		this.post_id = post_id;
		this.writer_id = writer_id;
		this.content = content;
		this.writer_name = writer_name;
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

	@Override
	public String toString() {
		return "PostComments [created_at=" + created_at + ", updated_at=" + updated_at + ", id=" + id + ", post_id="
				+ post_id + ", writer_id=" + writer_id + ", content=" + content + ", writer_name=" + writer_name + "]";
	}

}
