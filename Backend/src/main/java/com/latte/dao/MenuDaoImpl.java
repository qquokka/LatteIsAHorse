package com.latte.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.latte.dto.CafeDto;
import com.latte.dto.MenuDto;

@Repository
public class MenuDaoImpl {
	private final String ns = "mapper.menu.";

	@Autowired
	private SqlSession sqlSession;

	public List<MenuDto> getMenuList() {
		return sqlSession.selectList(ns + "getMenuList");
	}

	public int addMenu(MenuDto menu) {
		return sqlSession.insert(ns + "addMenu", menu);
	}

	public MenuDto getMenuById(int mid) {
		return sqlSession.selectOne(ns + "getMenuById", mid);
	}

	public List<MenuDto> getMenuListById(int cafe_id) {
		return sqlSession.selectList(ns + "getMenuListById", cafe_id);
	}

	public List<MenuDto> getMenuByProduct(String product) {
		return sqlSession.selectList(ns + "getMenuByProduct", product);
	}

	public int updateMenu(MenuDto menu) {
		return sqlSession.update(ns + "updateMenu", menu);
	}

	public int deleteMenuById(int mid) {
		return sqlSession.update(ns + "deleteMenuById", mid);
	}

}
