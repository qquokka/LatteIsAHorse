package com.latte.payload;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotNull;

public class PostHashtagRequest implements Serializable {
	private static final long serialVersionUID = 1L;

	@NotNull
	private Long post_id;
	@NotNull
	private List<Integer> hashtag_id;

	public PostHashtagRequest(Long post_id, List<Integer> hashtag_id) {
		super();
		this.post_id = post_id;
		this.hashtag_id = hashtag_id;
	}

	public PostHashtagRequest() {
		super();
	}

	public Long getPost_id() {
		return post_id;
	}

	public void setPost_id(Long post_id) {
		this.post_id = post_id;
	}

	public List<Integer> getHashtag_id() {
		return hashtag_id;
	}

	public void setHashtag_id(List<Integer> hashtag_id) {
		this.hashtag_id = hashtag_id;
	}

}
