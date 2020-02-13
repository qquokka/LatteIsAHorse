package com.latte.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.latte.model.Hashtag;
import com.latte.model.PostHashtag;

@Repository
public class HashTagDaoImpl {
	private final String ns = "mapper.hashtag.";
	
	@Autowired
	private SqlSession sqlSession;

	public int isHashtagNameExist(String name) {
		return sqlSession.selectOne(ns + "isHashtagNameExist", name);
	}

	public int addHashtagName(String name) {
		return sqlSession.insert(ns + "addHashtagName", name);
	}

	public int updateHashtagId(Long post_id) {
		return sqlSession.update(ns + "updateHashtagId", post_id);
	}

	public int deleteHashtagId(Long post_id) {
		return sqlSession.delete(ns + "deleteHashtagId", post_id);
	}

	public int addHashtagId(PostHashtag post_hashtag) {
		return sqlSession.insert(ns + "addHashtagId", post_hashtag);
	}

	public List<String> hashtagsInThePost(Long post_id) {
		return sqlSession.selectList(ns + "hashtagsInThePost", post_id);
	}

	public List<Hashtag> getAllHashtagByNames(List<String> names) {
		return sqlSession.selectList(ns + "getAllHashtagByNames", names);
	}
	
	
}