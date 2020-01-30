package com.latte.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.latte.dao.CafeDaoImpl;
import com.latte.dto.CafeDto;

@Service
public class CafeServiceImpl implements ICafeService {

	@Autowired
	CafeDaoImpl cafeDao;

	@Override
	@Transactional(readOnly = true)
	public List<CafeDto> getCafeList() {
		return cafeDao.getCafeList();
	}

	@Override
	@Transactional(readOnly = true)
	public CafeDto getCafeById(int cafe_id) {
		return cafeDao.getCafeById(cafe_id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<CafeDto> getCafeByName(String cafe_name) {
		return cafeDao.getCafeByName(cafe_name);
	}

	@Override
	@Transactional(readOnly = true)
	public List<CafeDto> getCafeByAddress(String cafe_address) {
		return cafeDao.getCafeByAddress(cafe_address);
	}

	@Override
	public int addCafe(CafeDto cafe) {
		return cafeDao.addCafe(cafe);
	}

	@Override
	public int updateCafeById(int cafe_id) {
		return cafeDao.updateCafeById(cafe_id);
	}

	@Override
	public int deleteCafeById(int cafe_id) {
		return cafeDao.deleteCafeById(cafe_id);
	}

}