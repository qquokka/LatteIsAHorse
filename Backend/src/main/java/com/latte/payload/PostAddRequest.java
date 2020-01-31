package com.latte.payload;

import java.io.Serializable;

public class PostAddRequest implements Serializable {
	private String title;
	private String thumbnail;
	private String content;
	private Long writer_id;
	private Integer cafe_id;

	public PostAddRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PostAddRequest(String title, String thumbnail, String content) {
		super();
		this.title = title;
		this.thumbnail = thumbnail;
		this.content = content;
	}

	public PostAddRequest(String title, String thumbnail, String content, Long writer_id) {
		super();
		this.title = title;
		this.thumbnail = thumbnail;
		this.content = content;
		this.writer_id = writer_id;
	}

	public Long getWriter_id() {
		return writer_id;
	}

	public void setWriter_id(Long writer_id) {
		this.writer_id = writer_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getCafe_id() {
		return cafe_id;
	}

	public void setCafe_id(Integer cafe_id) {
		this.cafe_id = cafe_id;
	}

}
