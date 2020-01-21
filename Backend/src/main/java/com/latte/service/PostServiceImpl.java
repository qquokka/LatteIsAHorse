package com.latte.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.latte.dao.PostDaoImpl;
import com.latte.dto.PostDto;

@Service
public class PostServiceImpl implements IPostService {

	@Autowired
	PostDaoImpl postDao;

	@Override
	public List<PostDto> getAllPostList() {
		return postDao.getAllPostList();
	}

	@Override
	public PostDto getPostByPostId(int postid) {
		return postDao.getPostByPostId(postid);
	}

	@Override
	public int addPost(PostDto post) {
		return postDao.addPost(post);
	}

	@Override
	public int updatePostInfo(PostDto post) {
		return postDao.updatePostInfo(post);
	}

	@Override
	public int deletePost(int postid) {
		return postDao.deletePost(postid);
	}

}
