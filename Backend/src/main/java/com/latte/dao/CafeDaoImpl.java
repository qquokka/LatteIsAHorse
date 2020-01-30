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

	public List<CafeDto> getCafeList() {
		return sqlSession.selectList(ns + "getCafeList");
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
	public int updateCafeById(int cafe_id) {
		return sqlSession.update(ns + "updateCafeById", cafe_id);
	}

	// Delete
	public int deleteCafeById(int cafe_id) {
		return sqlSession.delete(ns + "deleteCafeById", cafe_id);
	}
}
