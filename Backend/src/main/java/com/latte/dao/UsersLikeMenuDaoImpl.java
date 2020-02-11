package com.latte.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.latte.dto.MenuDto;
import com.latte.dto.UsersLikeMenu;

@Repository
public class UsersLikeMenuDaoImpl {

	String ns = "mapper.like.";

	@Autowired
	SqlSession sqlSession;

	// DB에 저장된 모든 좋아요를 반환[관리자용]
	public List<UsersLikeMenu> getUsersLikeMenuList() {
		return sqlSession.selectList(ns + "getUsersLikeMenuList");
	}

	// user_id 로 좋아하는 메뉴를 리스트로 반환
	public UsersLikeMenu getUsersLikeMenuCountBymenuId(int mid) {
		return sqlSession.selectOne(ns + "getUsersLikeMenuCountBymenuId", mid);
	}

	// READ
	public List<MenuDto> getUsersLikeMenuByCafeIdNUserId(UsersLikeMenu userslikemenu) {
		return sqlSession.selectList(ns + "getUsersLikeMenuByCafeIdNUserId", userslikemenu);
	}

	// Create
	public int addUsersLikeMenu(UsersLikeMenu userslikemenu) {
		return sqlSession.insert(ns + "addUsersLikeMenu", userslikemenu);
	}

	// Delete
	public int deleteLikeByUserIdNMenuId(UsersLikeMenu userslikemenu) {
		return sqlSession.delete(ns + "deleteLikeByUserIdNMenuId", userslikemenu);
	}

}
