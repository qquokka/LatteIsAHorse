package com.latte.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.latte.dao.PostDaoImpl;
import com.latte.model.post.Post;
import com.latte.payload.PostRequest;

@Service
public class PostServiceImpl implements IPostService {

	@Autowired
	PostDaoImpl postDao;

	@Override
	@Transactional(readOnly = true)
	public List<Post> getPostList(PostRequest request) {
		return postDao.getPostList(request);
	}

	@Override
	@Transactional
	public int addPost(Post post) {
		return postDao.addPost(post);
	}

	@Override
	@Transactional(readOnly = true)
	public Post getPostByTitle(String title) {
		return postDao.getPostByTitle(title);
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

}
