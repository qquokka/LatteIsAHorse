package com.latte.service;

import java.util.List;

import com.latte.dto.MenuDto;

public interface IMenuService {
	// DB에 저장된 모든 Menu를 반환
	List<MenuDto> getMenuList();

	// Create
	int addMenu(MenuDto menu);

	// Read //메뉴 로트번호로 검색된 메뉴
	MenuDto getMenuById(int mid);

	// Read //지정된 카페에 대한 메뉴 리스트를 반환
	List<MenuDto> getMenuListById(int cafe_id);

	// Read //이름이 같거나 일부 같은 메뉴 이름을 갖고 리스트를 반환
	List<MenuDto> getMenuByProduct(String product);

	// Update
	int updateMenuById(int mid);

	// Delete
	int deleteMenuById(int mid);

}
