package com.latte.service;

import java.util.List;

import com.latte.UsersLikeCafedto.UsersLikeCafe;

public interface IUsersLikeCafeService {
	// DB 에 저장된 모든 좋아요를 반환[관리자용]
	List<UsersLikeCafe> getLikeList();

	// 
	List<UsersLikeCafe> getLikeListByUserId(int user_id);

	// Create
	int addLike(UsersLikeCafe like);

	// READ
	UsersLikeCafe getLikeByUserId(Long user_id);

	// Update
	int updateLike(UsersLikeCafe like);

	// Delete
	int deleteLikeByUserId(int user_id);

}
