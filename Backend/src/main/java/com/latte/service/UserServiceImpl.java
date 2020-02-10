package com.latte.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.latte.dao.UserDaoImpl;
import com.latte.payload.UserInfoUpdateRequest;
import com.latte.payload.UserRoleUpdateRequest;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	UserDaoImpl userDao;

	@Override
	@Transactional
	public int updateUserInfo(UserInfoUpdateRequest request) {
		return userDao.updateUserInfo(request);
	}

	@Override
	@Transactional
	public int withdrawalUserAccount(Long id) {
		return userDao.withdrawalUserAccount(id);
	}

	@Override
	public int updateUserRole(UserRoleUpdateRequest request) {
		return userDao.updateUserRole(request);
	}

}
