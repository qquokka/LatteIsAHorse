package com.latte.payload;

import java.util.List;

import javax.validation.constraints.NotNull;

public class PostHashtagRequest {
	@NotNull
	private Long post_id;
	@NotNull
	private List<Integer> hashtag_ids;

	public PostHashtagRequest(@NotNull Long post_id, List<Integer> hashtag_ids) {
		super();
		this.post_id = post_id;
		this.hashtag_ids = hashtag_ids;
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

	public List<Integer> getHashtag_ids() {
		return hashtag_ids;
	}

	public void setHashtag_ids(List<Integer> hashtag_ids) {
		this.hashtag_ids = hashtag_ids;
	}

}
