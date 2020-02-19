package com.latte.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.latte.dto.CafeDto;
import com.latte.model.post.Post;

@Repository
public class SearchDaoImpl {
	String ns = "mapper.search."; // ns : namespace

	@Autowired
	private SqlSession sqlSession;

	public List<Post> searchInPosts(String query) {
		return sqlSession.selectList(ns + "searchInPosts", query);
	}

	public List<CafeDto> searchInCafe(String query) {
		return sqlSession.selectList(ns + "searchInCafe", query);
	}

	public List<CafeDto> searchInHashtag(String query) {
		return sqlSession.selectList(ns + "searchInHashtag", query);
	}

}
