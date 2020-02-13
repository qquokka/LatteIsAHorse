package com.latte.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.latte.dto.CafeDto;

@Repository
public class CafeDaoImpl {
	private final String ns = "mapper.cafe.";

	@Autowired
	private SqlSession sqlSession;

	public List<CafeDto> getCafeList(CafeDto cafe) {
		return sqlSession.selectList(ns + "getCafeList", cafe);
	};

	// Create
	public int addCafe(CafeDto cafe) {
		return sqlSession.insert(ns + "addCafe", cafe);
	}

	// Read
	public CafeDto getCafeById(int cafe_id) {
		return sqlSession.selectOne(ns + "getCafeById", cafe_id);
	}

	// Read
	public List<CafeDto> getCafeByName(String cafe_name) {
		return sqlSession.selectList(ns + "getCafeByName", cafe_name);
	}

	// Read
	public List<CafeDto> getCafeByAddress(String cafe_address) {
		return sqlSession.selectList(ns + "getCafeByAddress", cafe_address);
	}

	// Update
	public int updateCafe(CafeDto cafedto) {
		return sqlSession.update(ns + "updateCafe", cafedto);
	}

	// Delete
	public int deleteCafeById(int cafe_id) {
		return sqlSession.delete(ns + "deleteCafeById", cafe_id);
	}

	// 좋아요한 카페
	public List<CafeDto> getMyCafeList(Long user_id) {
		return sqlSession.selectList(ns + "getMyCafeList", user_id);

	}

	public int isExist(Long cafe_owner_id) {
		return sqlSession.selectOne(ns + "isExist", cafe_owner_id);
	}
}
