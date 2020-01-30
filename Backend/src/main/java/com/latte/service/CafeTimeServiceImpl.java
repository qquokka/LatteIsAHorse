package com.latte.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.latte.dto.CafeTimeDto;

public class CafeTimeServiceImpl implements ICafeTimeService {
	@Autowired
	CafeTimeServiceImpl cafeDao;

	@Override
	public int addCafeTime(CafeTimeDto CafeTime) {
		return cafeDao.addCafeTime(CafeTime);
	}

	@Override
	public CafeTimeDto getCafeTimeByctId(int ctid) {
		return cafeDao.getCafeTimeByctId(ctid);
	}

	@Override
	public int updateCafeTimeInfo(CafeTimeDto CafeTime) {
		return cafeDao.updateCafeTimeInfo(CafeTime);
	}

}