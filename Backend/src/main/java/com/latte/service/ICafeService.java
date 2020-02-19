package com.latte.service;

import java.util.List;

import com.latte.dto.CafeDto;

public interface ICafeService {
	// 모든 Cafe 검색
	List<CafeDto> getCafeList();

	// 카페 등록
	int addCafe(CafeDto cafe);

	// 카페 ID로 카페 검색
	CafeDto getCafeById(int cafe_id);

	// 카페명으로 카페 검색
	List<CafeDto> getCafeByName(String cafe_name);

	// 주소로 카페 검색
	List<CafeDto> getCafeByAddress(String cafe_address);

	// 카페 정보 업데이트
	int updateCafe(CafeDto cafedto);

	// 카페 삭제
	int deleteCafeById(int cafe_id);

	// 내가 좋아하는 카페 리스트
	List<CafeDto> getMyCafeList(Long user_id);

	// 사용자 ID(카페 사장님)로 카페 존재 여부 조회
	int isExist(Long cafe_owner_id);

	// 사용자 ID(카페 사장님)로 카페 정보 불러오기
	CafeDto getMyCafeInfo(Long cafe_owner_id);
}
