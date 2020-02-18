package com.latte.model.post;

import java.io.Serializable;
import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonInclude;
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class Post implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id; 			 // id
	private Integer like_count;  //  좋아요 수
	private String thumbnail; 	 // 썸네일
	private String title; 		 // 제목
	private String content; 	 // 게시글 본문
	private Long writer_id = 0L; // 작성자의 id
	private Instant created_at;  //게시물 작성일
	private Instant updated_at;  //게시물 수정일

	private String writer_name;
	private int cafe_id; // get post 에서 cafe_id를 주기위함.

	public Post() {
		super();
	}

	public Post(String thumbnail, String title, String content) {
		super();
		this.thumbnail = thumbnail;
		this.title = title;
		this.content = content;
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

	public Post(Long id, Integer like_count, String thumbnail, String title, String content, Long writer_id,
			Instant created_at, Instant updated_at, String writer_name, int cafe_id) {
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
		this.cafe_id = cafe_id;
	}

	public int getCafe_id() {
		return cafe_id;
	}

	public void setCafe_id(int cafe_id) {
		this.cafe_id = cafe_id;
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
