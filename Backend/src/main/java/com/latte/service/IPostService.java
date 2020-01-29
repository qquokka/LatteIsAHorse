package com.latte.service;

import java.util.List;

import com.latte.model.post.Post;
import com.latte.model.post.PostComments;
import com.latte.model.post.PostHashtag;
import com.latte.payload.PostAddRequest;
import com.latte.payload.PostRequest;

public interface IPostService {
	// DB에 저장된 모든 Post를 반환
	List<Post> getPostList(PostRequest request);

	// Create
	int addPost(PostAddRequest post);

	// Read
	List<Post> getPostByTitle(String title);

	// Read
	Post getPostById(Long id);

	// Update
	int updatePostById(Long id);

	// Delete
	int deletePostById(Long id);

	Long getLastPostedId();
	
	// Post hashtag 관련 
	List<PostHashtag> getPostHashTags(Long post_id);
	
	List<PostHashtag> getAllHashTags();
	
	int addPostHashtag(PostHashtag hashTag);
	
	int updatePostHashtag(PostHashtag hashTag);
	
	int deletePostHashtag(Long id);

	// Post Comments 관련 
	List<PostComments> getPostComments(PostComments comment);
	
	int addPostComments(PostComments comment);
	
	int updatePostComments(PostComments comment);
	
	int deletePostComments(Long id);
	
}
