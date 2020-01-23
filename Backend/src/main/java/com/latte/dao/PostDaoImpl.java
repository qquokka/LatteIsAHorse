package com.latte.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.latte.dto.PostDto;

@Repository
public class PostDaoImpl {

	String ns = "latte.post."; // ns : namespace

	@Autowired
	private SqlSession sqlSession;

	public List<PostDto> getAllPostList() {
		return sqlSession.selectList(ns + "getAllPostList");
	}

	public int addPost(PostDto post) {
		return sqlSession.insert(ns + "addPost", post);
	}

	public PostDto getPostByPostId(int postid) {
		return sqlSession.selectOne(ns + "getPostByPostId", postid);
	}

	public int updatePostInfo(PostDto post) {
		return sqlSession.update(ns + "updatePostInfo", post);
	}

	public int deletePost(int postid) {
		return sqlSession.delete(ns + "deletePost", postid);
	}

}
