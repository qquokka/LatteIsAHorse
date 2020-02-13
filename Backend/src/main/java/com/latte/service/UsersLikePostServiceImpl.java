package com.latte.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.latte.dao.UsersLikePostDaoImpl;
import com.latte.dto.UsersLikePost;

@Service
public class UsersLikePostServiceImpl implements IUsersLikePostService {

	@Autowired
	UsersLikePostDaoImpl userslikepostDao;

	@Override
	@Transactional
	public int userLikedPost(UsersLikePost ulp) {
		return userslikepostDao.userLikedPost(ulp);
	}

	@Override
	@Transactional
	public int userDislikedPost(UsersLikePost ulp) {
		return userslikepostDao.userDislikedPost(ulp);
	}

	@Override
	@Transactional(readOnly = true)
	public List<UsersLikePost> getUsersLikePostList() {
		return userslikepostDao.getUsersLikePostList();
	}

	@Override
	@Transactional(readOnly = true)
	public int getNumberOfUsersLikedPost(Long post_id) {
		return userslikepostDao.getNumberOfUsersLikedPost(post_id);
	}

}
