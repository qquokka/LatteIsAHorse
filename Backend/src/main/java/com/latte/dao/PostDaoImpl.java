package com.latte.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.latte.model.post.Post;
import com.latte.model.post.PostHashtag;
import com.latte.payload.PostAddRequest;
import com.latte.payload.PostRequest;

@Repository
public class PostDaoImpl {

	String ns = "mapper.post."; // ns : namespace

	@Autowired
	private SqlSession sqlSession;

	//---------------- Post 관련 ----------------
	public List<Post> getPostList(PostRequest request) {
		return sqlSession.selectList(ns + "getPostList", request);
	}

	public int addPost(PostAddRequest post) {
		return sqlSession.insert(ns + "addPost", post);
	}

	public List<Post> getPostByTitle(String title) {
		return sqlSession.selectList(ns + "getPostByTitle", title);
	}

	public Post getPostById(Long id) {
		return sqlSession.selectOne(ns + "getPostById", id);
	}

	public int updatePostById(Long id) {
		return sqlSession.update(ns + "updatePostById", id);
	}

	public int deletePostById(Long id) {
		return sqlSession.delete(ns + "deletePostById", id);
	}

	public Long getLastPostedId() {
		return sqlSession.selectOne(ns + "getLastPostedId");
	}

	//---------------- Post hashtag 관련 ----------------
	public List<PostHashtag> getPostHashTags(Long post_id) {
		return sqlSession.selectList(ns + "getPostHashTags", post_id);
	}

	public List<PostHashtag> getAllHashTags() {
		return sqlSession.selectList(ns + "getAllHashTags");
	}

	public int addPostHashtag(PostHashtag hashTag) {
		return sqlSession.insert(ns + "addPostHashtag", hashTag);
	}

	public int updatePostHashtag(PostHashtag hashTag) {
		return sqlSession.update(ns + "updatePostHashtag", hashTag);
	}

	public int deletePostHashtag(Long id) {
		return sqlSession.delete(ns + "deletePostHashtag", id);
	}

}
