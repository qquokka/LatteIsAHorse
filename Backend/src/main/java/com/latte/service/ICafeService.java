package com.latte.service;

import java.util.List;

import com.latte.dto.CafeDto;

public interface ICafeService {
	// 모든 카페를 보여준다.
	List<CafeDto> getAllCafeList();

	// 카페이름으로 검색한다.
	CafeDto getCafeBycafe_name(String cafe_name);

	// 카페 영업시간으로 검색한다. Open Time, Close Time, Day
	
	//

}
