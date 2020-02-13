package com.latte.service;

import java.util.List;

import com.latte.dto.CafeDto;

public interface ICafeService {
	// DB에 저장된 모든 Cafe를 반환
	List<CafeDto> getCafeList();

	// Create
	int addCafe(CafeDto cafe);

	// Read
	CafeDto getCafeById(int cafe_id);

	// Read
	List<CafeDto> getCafeByName(String cafe_name);

	// Read
	List<CafeDto> getCafeByAddress(String cafe_address);

	// Update
	int updateCafe(CafeDto cafedto);

	// Delete
	int deleteCafeById(int cafe_id);

	// ---------------------------------------------------
	// ---------------------------------------------------
	List<CafeDto> getMyCafeList(Long user_id);

	int isExist(Long cafe_owner_id);

}
