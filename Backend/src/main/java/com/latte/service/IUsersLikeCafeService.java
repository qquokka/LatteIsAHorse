package com.latte.service;

import java.util.List;

import com.latte.dto.UsersLikeCafeDto;

public interface IUsersLikeCafeService {
	// user가 좋아요를 누른 모든 카페 반환
	List<UsersLikeCafeDto> getUsersLikeCafeList();

	// 카페의 전체 좋아요 수를 반환
	List<UsersLikeCafeDto> getUsersLikeCafeByCafeId(int cafe_id);

	// 좋아요 누름
	int addUsersLikeCafe(UsersLikeCafeDto users_id);

	// 좋아요 취소
	int deleteUsersLikeCafe(Long users_id);

}
