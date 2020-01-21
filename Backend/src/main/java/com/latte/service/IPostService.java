package com.latte.service;

import java.util.List;

import com.latte.dto.PostDto;

public interface IPostService {
	// DB에 저장된 모든 Post를 반환
	List<PostDto> getAllPostList();

	// Create
	int addPost(PostDto post);

	// Read
	PostDto getPostByPostId(int postid);

	// Update
	int updatePostInfo(PostDto post);

	// Delete
	int deletePost(int postid);

}
