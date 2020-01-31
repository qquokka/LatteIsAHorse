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

	public int updateMenuById(int mid) {
		return sqlSession.update(ns + "updateMenuById", mid);
	}

	public int deleteMenuById(int mid) {
		return sqlSession.update(ns + "deleteMenuById", mid);
	}

	// ----------------------
	public List<CafeDto> getCafeList() {
		return sqlSession.selectList(ns + "getCafeList");
	};

	// Create
	public int addCafe(CafeDto cafe) {
		return sqlSession.insert(ns + "addCafe", cafe);
	}

	// Read
	public CafeDto getCafeById(int cafe_id) {
		return sqlSession.selectOne(ns + "getCafeById", cafe_id);
	}

	// Read
	public List<CafeDto> getCafeByName(String cafe_name) {
		return sqlSession.selectList(ns + "getCafeByName", cafe_name);
	}

	// Read
	public List<CafeDto> getCafeByAddress(String cafe_address) {
		return sqlSession.selectList(ns + "getCafeByAddress", cafe_address);
	}

	// Update
	public int updateCafeById(int cafe_id) {
		return sqlSession.update(ns + "updateCafeById", cafe_id);
	}

	// Delete
	public int deleteCafeById(int cafe_id) {
		return sqlSession.delete(ns + "deleteCafeById", cafe_id);
	}
}
