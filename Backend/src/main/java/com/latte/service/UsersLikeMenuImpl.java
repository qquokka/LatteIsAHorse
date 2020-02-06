package com.latte.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.latte.dao.UsersLikeMenuDaoImpl;
import com.latte.dto.ULM;
import com.latte.dto.ULMCUID;
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
	public List<ULMCUID> getUsersLikeMenuByCafeIdNUserId(ULMCUID ulmcuid) {
		return userslikemenuDao.getUsersLikeMenuByCafeIdNUserId(ulmcuid);
	}

	@Override
	@Transactional(readOnly = true)
	public ULM getUsersLikeMenuCountBymenuId(int mid) {
		return userslikemenuDao.getUsersLikeMenuCountBymenuId(mid);
	}

//	@Override
//	@Transactional
//	public UsersLikeMenu getUsersLikeMenuByUserIdNMenuId(UsersLikeMenu userslikemenu) {
//		return userslikemenuDao.getUsersLikeMenuByUserIdNMenuId(userslikemenu);
//	}
//
//	@Override
//	@Transactional
//	public int addUsersLikeMenu(UsersLikeMenu userslikemenu) {
//		return userslikemenuDao.addUsersLikeMenu(userslikemenu);
//	}
//
//	@Override
//	@Transactional
//	public int updateUsersLikeMenu(UsersLikeMenu userslikemenu) {
//		return userslikemenuDao.updateUsersLikeMenu(userslikemenu);
//	}
//
//	@Override
//	@Transactional
//	public int deleteLikeByUserIdNMenuId(UsersLikeMenu userslikemenu) {
//		return userslikemenuDao.deleteLikeByUserIdNMenuId(userslikemenu);
//	}

}
