package com.latte.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.latte.dto.UsersLikeCafeDto;

@Repository
public class UsersLikeCafeDaoImpl {
	private String ns = "mapper.like.";

	@Autowired
	private SqlSession sqlSession;

	// DB의 모든 UserLikeCafe 리스트 반환
	public List<UsersLikeCafeDto> getUsersLikeCafeList() {
		return sqlSession.selectList(ns + "getUsersLikeCafeList");
	}

	// 카페의 좋아요 수를 반환
	public UsersLikeCafeDto getUsersLikeCafeByCafeId(int cafe_id) {
		return sqlSession.selectOne(ns + "getUsersLikeCafeByCafeId", cafe_id);
	}

	// 좋아요 누름
	public int addUsersLikeCafe(UsersLikeCafeDto users_id) {
		return sqlSession.insert(ns + "addUsersLikeCafe", users_id);
	}

	// 좋아요 취소
	public int deleteUsersLikeCafe(Long users_id) {
		return sqlSession.delete(ns + "deleteUsersLikeCafe", users_id);
	}
	
}
