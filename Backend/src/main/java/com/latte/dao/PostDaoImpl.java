package com.latte.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.latte.model.post.Post;
import com.latte.model.post.PostComments;
import com.latte.payload.PostAddRequest;

@Repository
public class PostDaoImpl {

	String ns = "mapper.post."; // ns : namespace

	@Autowired
	private SqlSession sqlSession;

	// ---------------- Post 관련 ----------------
	public List<Post> getPostList() {
		return sqlSession.selectList(ns + "getPostList");
	}

	public int addPost(PostAddRequest post) {
		return sqlSession.insert(ns + "addPost", post);
	}

	public List<Post> getPostByTitle(String title) {
		return sqlSession.selectList(ns + "getPostByTitle", title);
	}

	public List<Post> getPostListByCafeId(Integer cafe_id) {
		return sqlSession.selectList(ns + "getPostListByCafeId", cafe_id);
	}

	public Post getPostById(Long id) {
		return sqlSession.selectOne(ns + "getPostById", id);
	}

	public int updatePost(Post post) {
		return sqlSession.update(ns + "updatePostById", post);
	}

	public int deletePostById(Long id) {
		return sqlSession.delete(ns + "deletePostById", id);
	}

	public Long getLastPostedId() {
		return sqlSession.selectOne(ns + "getLastPostedId");
	}

	public List<Post> getMyPostList(Long user_id) {
		return sqlSession.selectList(ns + "getMyPostList", user_id);
	}

	// ---------------- Post Comments 관련 ----------------

	public List<PostComments> getPostCommentsByPostId(Long post_id) {
		return sqlSession.selectList(ns + "getPostCommentsByPostId", post_id);
	}

	public int addPostComments(PostComments comment) {
		return sqlSession.insert(ns + "addPostComments", comment);
	}

	public int updatePostComments(PostComments comment) {
		return sqlSession.update(ns + "updatePostComments", comment);
	}

	public int deletePostComments(Long id) {
		return sqlSession.delete(ns + "deletePostComments", id);
	}

	public List<PostComments> getAllPostComments() {
		return sqlSession.selectList(ns + "getAllPostComments");
	}

	public List<PostComments> getMyPostCommentsList(Long user_id) {
		return sqlSession.selectList(ns + "getMyPostCommentsList", user_id);
	}

}
