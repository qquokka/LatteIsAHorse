package com.latte.model;

import javax.persistence.Id;

public class Post {
	@Id
	private Long id;
	
	private String title;
	private String content;
	private String photo_url;
	private String thumbnail;
	
}
