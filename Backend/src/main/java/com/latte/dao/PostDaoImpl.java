package com.latte.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.latte.model.post.Post;
import com.latte.payload.PostRequest;

@Repository
public class PostDaoImpl {

	String ns = "mapper.post."; // ns : namespace

	@Autowired
	private SqlSession sqlSession;

	public List<Post> getPostList(PostRequest request) {
		return sqlSession.selectList(ns + "getPostList", request);
	}

	public int addPost(Post post) {
		return sqlSession.insert(ns + "addPost", post);
	}

	public Post getPostByTitle(String title) {
		return sqlSession.selectOne(ns + "getPostByTitle", title);
	}

	public int updatePostById(Long id) {
		return sqlSession.update(ns + "updatePostById", id);
	}

	public int deletePostById(Long id) {
		return sqlSession.delete(ns + "deletePostById", id);
	}


}
