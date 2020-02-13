package com.latte.payload;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class UpdateHashtagRequest {
	@NotNull
	private Long posts_id;
	@NotNull
	private Integer hashtag_id;
	@NotBlank
	private String name;

	public UpdateHashtagRequest(@NotNull Long posts_id, @NotNull Integer hashtag_id, @NotBlank String name) {
		super();
		this.posts_id = posts_id;
		this.hashtag_id = hashtag_id;
		this.name = name;
	}

	public UpdateHashtagRequest() {
		super();
	}

	public Long getPosts_id() {
		return posts_id;
	}

	public void setPosts_id(Long posts_id) {
		this.posts_id = posts_id;
	}

	public Integer getHashtag_id() {
		return hashtag_id;
	}

	public void setHashtag_id(Integer hashtag_id) {
		this.hashtag_id = hashtag_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
