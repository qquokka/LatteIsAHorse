package com.latte.service;

import java.util.List;

import com.latte.dto.UsersLikeCafeDto;

public interface IUsersLikeCafeService {
	// DB의 모든 UserLikeCafe 리스트 반환
	List<UsersLikeCafeDto> getUsersLikeCafeList();

	// 카페의 좋아요 수를 반환
	UsersLikeCafeDto getUsersLikeCafeByCafeId(int cafe_id);

	// 좋아요 누름
	int addUsersLikeCafe(UsersLikeCafeDto userslikecafe);

	// 좋아요 취소
	int deleteUsersLikeCafe(UsersLikeCafeDto userslikecafe);

}
