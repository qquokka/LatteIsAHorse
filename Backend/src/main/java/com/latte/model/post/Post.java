package com.latte.model.post;

import java.io.Serializable;
import java.time.Instant;

import com.latte.model.audit.DateAudit;

public class Post implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id; // bigint(20) NOT NULL AUTO_INCREMENT,
	private Integer like_count; // int default 0, -- 좋아요 수
	private String thumbnail; // varchar(200) default null, -- 썸네일
	private String title; // varchar(200) not null, -- post의 제목
	private String content; // text not null, -- post의 게시글 본문
	private Long writer_id; // bigint(20) not null,
	private Instant created_at;
	private Instant updated_at;

	private String writer_name;

	public Post() {
		super();
	}

	public Post(Long id, Integer like_count, String thumbnail, String title, String content, Long writer_id) {
		super();
		this.id = id;
		this.like_count = like_count;
		this.thumbnail = thumbnail;
		this.title = title;
		this.content = content;
		this.writer_id = writer_id;
	}

	public Post(Long id, Integer like_count, String thumbnail, String title, String content, Long writer_id,
			Instant created_at, Instant updated_at) {
		super();
		this.id = id;
		this.like_count = like_count;
		this.thumbnail = thumbnail;
		this.title = title;
		this.content = content;
		this.writer_id = writer_id;
		this.created_at = created_at;
		this.updated_at = updated_at;
	}

	public Post(Long id, Integer like_count, String thumbnail, String title, String content, Long writer_id,
			Instant created_at, Instant updated_at, String writer_name) {
		super();
		this.id = id;
		this.like_count = like_count;
		this.thumbnail = thumbnail;
		this.title = title;
		this.content = content;
		this.writer_id = writer_id;
		this.created_at = created_at;
		this.updated_at = updated_at;
		this.writer_name = writer_name;
	}

	public String getWriter_name() {
		return writer_name;
	}

	public void setWriter_name(String writer_name) {
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getLike_count() {
		return like_count;
	}

	public void setLike_count(Integer like_count) {
		this.like_count = like_count;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Long getWriter_id() {
		return writer_id;
	}

	public void setWriter_id(Long writer_id) {
		this.writer_id = writer_id;
	}

}
