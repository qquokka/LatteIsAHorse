package com.latte.service;

import java.util.List;

import com.latte.dto.CafeDto;
import com.latte.model.post.Post;

public interface ISearchService {
	List<Post> searchInPosts(String query);

	List<CafeDto> searchInCafe(String query);
	
	List<CafeDto> searchInHashtag(String query);
}
