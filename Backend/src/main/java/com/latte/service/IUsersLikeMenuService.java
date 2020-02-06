package com.latte.service;

import java.util.List;

import com.latte.dto.UsersLikeMenu;

public interface IUsersLikeMenuService {
	// DB에 저장된 모든 좋아요를 반환[관리자용]
	List<UsersLikeMenu> getUsersLikeMenuList();

	// user_id 로 좋아하는 메뉴를 리스트로 반환
	List<UsersLikeMenu> getUsersLikeMenuListByUserId(Long user_id);

	// READ
	UsersLikeMenu getUsersLikeMenuByUserIdNMenuId(UsersLikeMenu userslikemenu);

	// Create
	int addUsersLikeMenu(UsersLikeMenu userslikemenu);

	// Update
	int updateUsersLikeMenu(UsersLikeMenu userslikemenu);

	// Delete
	int deleteLikeByUserIdNMenuId(UsersLikeMenu userslikemenu);

}
