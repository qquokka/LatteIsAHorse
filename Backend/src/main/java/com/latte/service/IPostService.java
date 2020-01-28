package com.latte.service;

import java.util.List;

import com.latte.model.post.Post;
import com.latte.payload.PostRequest;

public interface IPostService {
	// DB에 저장된 모든 Post를 반환
	List<Post> getPostList(PostRequest request);

	// Create
	int addPost(Post post);

	// Read
	Post getPostByTitle(String title);

	// Update
	int updatePostById(Long id);

	// Delete
	int deletePostById(Long id);

}
