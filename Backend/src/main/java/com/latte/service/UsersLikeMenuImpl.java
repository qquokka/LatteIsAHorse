package com.latte.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.latte.dao.UsersLikeMenuDaoImpl;
import com.latte.dto.UsersLikeMenu;

@Service
public class UsersLikeMenuImpl implements IUsersLikeMenuService {

	@Autowired
	UsersLikeMenuDaoImpl userslikemenuDao;

	@Override
	@Transactional(readOnly = true)
	public List<UsersLikeMenu> getUsersLikeMenuList() {
		return userslikemenuDao.getUsersLikeMenuList();
	}

	@Override
	@Transactional(readOnly = true)
	public List<UsersLikeMenu> getUsersLikeMenuByCafeIdNUserId(UsersLikeMenu userslikemenu) {
		return userslikemenuDao.getUsersLikeMenuByCafeIdNUserId(userslikemenu);
	}

	@Override
	@Transactional(readOnly = true)
	public UsersLikeMenu getUsersLikeMenuCountBymenuId(int mid) {
		return userslikemenuDao.getUsersLikeMenuCountBymenuId(mid);
	}

	@Override
	@Transactional
	public int addUsersLikeMenu(UsersLikeMenu userslikemenu) {
		return userslikemenuDao.addUsersLikeMenu(userslikemenu);
	}

	@Override
	@Transactional
	public int deleteUsersLikeMenuByUserIdNMenuId(UsersLikeMenu userslikemenu) {
		return userslikemenuDao.deleteLikeByUserIdNMenuId(userslikemenu);
	}

}
