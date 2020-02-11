package com.latte.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.latte.dto.UsersLikePost;

@Repository
public class UsersLikePostDaoImpl {
	private String ns = "mapper.like.";

	@Autowired
	private SqlSession sqlSession;

	public int userLikedPost(UsersLikePost ulp) {
		return sqlSession.insert(ns + "userLikedPost", ulp);
	}

	public int userDislikedPost(UsersLikePost ulp) {
		return sqlSession.delete(ns + "userLikedPost", ulp);
	}

	public List<UsersLikePost> getUsersLikePostList() {
		return sqlSession.selectList(ns + "getUsersLikePostList");
	}

	public int getNumberOfUsersLikedPost(Long post_id) {
		return sqlSession.selectOne(ns + "getNumberOfUsersLikedPost", post_id);
	}

}
