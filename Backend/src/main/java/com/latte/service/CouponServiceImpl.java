package com.latte.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.latte.dao.CouponDaoImpl;
import com.latte.dto.Coupon;
import com.latte.payload.CouponUseRequest;
import com.latte.payload.CouponUseResponse;

@Service
public class CouponServiceImpl implements ICouponService {

	@Autowired
	CouponDaoImpl couponDao;

	@Override
	@Transactional(readOnly = true)
	public List<Coupon> getCouponList() {
		return couponDao.getCouponList();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Coupon> getCouponListByCafeId(Long cafe_id) {
		return couponDao.getCouponListByCafeId(cafe_id);
	}

	@Override
	@Transactional
	public int addCoupon(Coupon coupon) {
		return couponDao.addCoupon(coupon);
	}

	@Override
	@Transactional(readOnly = true)
	public Coupon getCouponByUserId(Long user_id) {
		return couponDao.getCouponByUserId(user_id);
	}

	@Override
	@Transactional
	public int updateCoupon(Coupon coupon) {
		return couponDao.updateCoupon(coupon);
	}

	@Override
	@Transactional
	public int deleteCouponByUserId(int user_id) {
		return couponDao.deleteCouponByUserId(user_id);
	}

	@Override
	@Transactional(readOnly = true)
	public int isExist(Coupon coupon) {
		return couponDao.isExist(coupon);
	}

	@Override
	@Transactional
	public int useCoupon(Coupon coupon) {
		return couponDao.useCoupon(coupon);
	}

	@Override
	@Transactional(readOnly = true)
	public int getCurrentCouponCount(Coupon coupon) {
		return couponDao.getCurrentCouponCount(coupon);
	}

	@Override
	@Transactional(readOnly = true)
	public List<CouponUseResponse> getCouponUseRequests(int cafe_id) {
		return couponDao.getCouponUseRequests(cafe_id);
	}

	@Override
	@Transactional
	public int requestCouponUse(CouponUseRequest useRequest) {
		return couponDao.requestCouponUse(useRequest);
	}

}
