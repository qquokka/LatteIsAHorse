package com.latte.service;

import java.util.List;

import com.latte.dto.UsersLikePost;

public interface IUsersLikePostService {
	// 좋아요 추가
	int userLikedPost(UsersLikePost ulp);

	// 좋아요 제거
	int userDislikedPost(UsersLikePost ulp);

	// Post의 좋아요 리스트 반환
	List<UsersLikePost> getUsersLikePostList();

	// 해당 Post의 좋아요 수 반환
	int getNumberOfUsersLikedPost(Long post_id);
}
