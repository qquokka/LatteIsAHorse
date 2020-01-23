package com.latte.service;

import com.latte.dto.CafeTimeDto;

public interface ICafeTimeService {

	// Create
	int addCafeTime(CafeTimeDto CafeTime);

	// Read
	CafeTimeDto getCafeTimeByctId(int ctid);

	// Update
	int updateCafeTimeInfo(CafeTimeDto CafeTime);

}
