package com.latte.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.latte.dao.PostDaoImpl;
import com.latte.model.post.Post;
import com.latte.model.post.PostComments;
import com.latte.model.post.PostHashtag;
import com.latte.payload.PostAddRequest;
import com.latte.payload.PostRequest;

@Service
public class PostServiceImpl implements IPostService {

	@Autowired
	PostDaoImpl postDao;

	@Override
	@Transactional(readOnly = true)
	public List<Post> getPostList() {
		return postDao.getPostList();
	}

	@Override
	@Transactional
	public int addPost(PostAddRequest post) {
		return postDao.addPost(post);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Post> getPostByTitle(String title) {
		return postDao.getPostByTitle(title);
	}

	@Override
	@Transactional(readOnly = true)
	public Post getPostById(Long id) {
		return postDao.getPostById(id);
	}

	@Override
	@Transactional
	public int updatePostById(Long id) {
		return postDao.updatePostById(id);
	}

	@Override
	@Transactional
	public int deletePostById(Long id) {
		return postDao.deletePostById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Long getLastPostedId() {
		return postDao.getLastPostedId();
	}

	//---------------- Post hashtag 관련 ----------------
	
	@Override
	@Transactional(readOnly = true)
	public List<PostHashtag> getPostHashTags(Long post_id) {
		return postDao.getPostHashTags(post_id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<PostHashtag> getAllHashTags() {
		return postDao.getAllHashTags();
	}

	@Override
	@Transactional
	public int addPostHashtag(PostHashtag hashTag) {
		return postDao.addPostHashtag(hashTag);
	}

	@Override
	@Transactional
	public int updatePostHashtag(PostHashtag hashTag) {
		return postDao.updatePostHashtag(hashTag);
	}

	@Override
	@Transactional
	public int deletePostHashtag(Long id) {
		return postDao.deletePostHashtag(id);
	}
	
	//---------------- Post Comments 관련 ----------------
	
	@Override
	public List<PostComments> getPostComments(PostComments comment) {
		return postDao.getPostComments(comment);
	}

	@Override
	public int addPostComments(PostComments comment) {
		return postDao.addPostComments(comment);
	}

	@Override
	public int updatePostComments(PostComments comment) {
		return postDao.updatePostComments(comment);
	}

	@Override
	public int deletePostComments(Long id) {
		return postDao.deletePostComments(id);
	}

}
