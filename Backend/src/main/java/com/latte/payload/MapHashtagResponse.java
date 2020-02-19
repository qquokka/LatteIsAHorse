package com.latte.payload;

public class MapHashtagResponse {
	private Integer hashtag_id;
	private String name;
	private Integer count;

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

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "[hashtag_id=" + hashtag_id + ", name=" + name + ", count=" + count + "]";
	}

}
