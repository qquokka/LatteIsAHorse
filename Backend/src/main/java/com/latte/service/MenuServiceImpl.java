package com.latte.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.latte.dao.MenuDaoImpl;
import com.latte.dto.MenuDto;

@Service
public class MenuServiceImpl implements IMenuService {

	@Autowired
	private MenuDaoImpl menuDao;

	@Override
	@Transactional(readOnly = true)
	public List<MenuDto> getMenuList() {
		return menuDao.getMenuList();
	}

	@Override
	public int addMenu(MenuDto menu) {
		return menuDao.addMenu(menu);
	}

	@Override
	@Transactional(readOnly = true)
	public MenuDto getMenuById(int mid) {
		return menuDao.getMenuById(mid);
	}

	@Override
	@Transactional(readOnly = true)
	public List<MenuDto> getMenuListById(int cafe_id) {
		return menuDao.getMenuListById(cafe_id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<MenuDto> getMenuByProduct(String product) {
		return menuDao.getMenuByProduct(product);
	}

	@Override
	public int updateMenuById(int mid) {
		return menuDao.updateMenuById(mid);
	}

	@Override
	public int deleteMenuById(int mid) {
		return menuDao.deleteMenuById(mid);
	}

}
