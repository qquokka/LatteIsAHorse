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
	public List<CafeDto> getCafeList(CafeDto cafe) {
		return cafeDao.getCafeList(cafe);
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
	@Transactional
	public int addCafe(CafeDto cafe) {
		return cafeDao.addCafe(cafe);
	}

	@Override
	@Transactional
	public int updateCafe(CafeDto cafedto) {
		return cafeDao.updateCafe(cafedto);
	}

	@Override
	@Transactional
	public int deleteCafeById(int cafe_id) {
		return cafeDao.deleteCafeById(cafe_id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<CafeDto> getMyCafeList(Long user_id) {
		return cafeDao.getMyCafeList(user_id);
	}

	@Override
	@Transactional(readOnly = true)
	public int isExist(Long cafe_owner_id) {
		return cafeDao.isExist(cafe_owner_id);
	}

}
