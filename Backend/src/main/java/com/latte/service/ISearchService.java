package com.latte.service;

import java.util.List;

import com.latte.dto.CafeDto;
import com.latte.model.post.Post;

public interface ISearchService {
	// Post 검색
	List<Post> searchInPosts(String query);

	// Cafe 검색
	List<CafeDto> searchInCafe(String query);
	
	// Hashtag 이름으로 Cafe 검색
	List<CafeDto> searchInHashtag(String query);
}
