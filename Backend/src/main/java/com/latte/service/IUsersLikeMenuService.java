package com.latte.service;

import java.util.List;

import com.latte.dto.MenuDto;
import com.latte.dto.UsersLikeMenu;

public interface IUsersLikeMenuService {
	// DB에 저장된 모든 좋아요를 반환[관리자용]
	List<UsersLikeMenu> getUsersLikeMenuList();

	// mid 로 메뉴의 좋아요 카운트를 반환
	UsersLikeMenu getUsersLikeMenuCountBymenuId(int mid);

//	// READ
	List<MenuDto> getUsersLikeMenuByCafeIdNUserId(UsersLikeMenu userslikemenu);

	// Create
	int addUsersLikeMenu(UsersLikeMenu userslikemenu);

	// Delete
	int deleteUsersLikeMenuByUserIdNMenuId(UsersLikeMenu userslikemenu);

}
