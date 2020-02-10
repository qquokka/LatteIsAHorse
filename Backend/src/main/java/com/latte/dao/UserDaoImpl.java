package com.latte.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.latte.payload.UserInfoUpdateRequest;
import com.latte.payload.UserRoleUpdateRequest;

@Repository
public class UserDaoImpl {

	String ns = "mapper.user."; // ns : namespace

	@Autowired
	private SqlSession sqlSession;

	public int updateUserInfo(UserInfoUpdateRequest request) {
		return sqlSession.update(ns + "updateUserInfo", request);
	}

	public int withdrawalUserAccount(Long id) {
		return sqlSession.update(ns + "withdrawalUserAccount", id);
	}

	public int updateUserRole(UserRoleUpdateRequest request) {
		return sqlSession.update(ns + "updateUserRole", request);
	}

}
