package com.latte.service;

import java.util.List;

import com.latte.model.post.Post;
import com.latte.model.post.PostComments;
import com.latte.payload.PostAddRequest;

public interface IPostService {
	// DB에 저장된 모든 Post를 반환
	List<Post> getPostList();

	// Create
	int addPost(PostAddRequest post);

	// Read
	List<Post> getPostByTitle(String title);

	List<Post> getPostListByCafeId(Integer cafe_id);

	// Read
	Post getPostById(Long id);

	// Update
	int updatePost(Post post);

	// Delete
	int deletePostById(Long id);

	Long getLastPostedId();

	List<Post> getMyPostList(Long user_id);

	// Post Comments 관련
	List<PostComments> getPostCommentsByPostId(Long post_id);

	int addPostComments(PostComments comment);

	int updatePostComments(PostComments comment);

	int deletePostComments(Long id);

	List<PostComments> getAllPostComments();

	List<PostComments> getMyPostCommentsList(Long user_id);
}
