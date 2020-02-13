package com.latte.payload;

import java.util.List;

public class HashtagUpdateRequest {
	private Long post_id;
	private List<String> names;

	public HashtagUpdateRequest(Long post_id, List<String> names) {
		super();
		this.post_id = post_id;
		this.names = names;
	}

	public HashtagUpdateRequest() {
		super();
	}

	public Long getPost_id() {
		return post_id;
	}

	public void setPost_id(Long post_id) {
		this.post_id = post_id;
	}

	public List<String> getNames() {
		return names;
	}

	public void setNames(List<String> names) {
		this.names = names;
	}

}
