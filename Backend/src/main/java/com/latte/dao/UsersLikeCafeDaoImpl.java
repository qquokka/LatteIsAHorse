package com.latte.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.latte.dto.UsersLikeCafeDto;

@Repository
public class UsersLikeCafeDaoImpl {
	private final String ns = "mapper.userslikecafe.";

	@Autowired
	private SqlSession sqlSession;

	// user가 좋아요를 누른 모든 카페 반환
	public List<UsersLikeCafeDto> getUsersLikeCafeList() {
		return sqlSession.selectList(ns + "getUsersLikeCafeList");
	}

	// 카페의 전체 좋아요 수를 반환
	public List<UsersLikeCafeDto> getUsersLikeCafeByCafeId(int cafe_id) {
		return sqlSession.selectList(ns + "getUsersLikeCafeByCafeId", cafe_id);
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
