package com.latte.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.latte.dao.UsersLikeCafeDaoImpl;
import com.latte.dto.UsersLikeCafeDto;

@Service
public class UsersLikeCafeServiceImpl implements IUsersLikeCafeService {

	@Autowired
	UsersLikeCafeDaoImpl userslikecafeDao;

	@Override
	@Transactional(readOnly = true)
	public List<UsersLikeCafeDto> getUsersLikeCafeList() {
		return userslikecafeDao.getUsersLikeCafeList();
	}

	@Override
	@Transactional(readOnly = true)
	public UsersLikeCafeDto getUsersLikeCafeByCafeId(int cafe_id) {
		return userslikecafeDao.getUsersLikeCafeByCafeId(cafe_id);
	}

	@Override
	public int addUsersLikeCafe(UsersLikeCafeDto userslikecafe) {
		return userslikecafeDao.addUsersLikeCafe(userslikecafe);
	}

	@Override
	public int deleteUsersLikeCafe(UsersLikeCafeDto userslikecafe) {
		return userslikecafeDao.deleteUsersLikeCafe(userslikecafe);
	}

}
