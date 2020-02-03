package com.latte.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.latte.dao.CouponDaoImpl;
import com.latte.dto.Coupon;

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
	public int addCoupon(Coupon coupon) {
		return couponDao.addCoupon(coupon);
	}

	@Override
	@Transactional(readOnly = true)
	public Coupon getCouponByUserId(Long user_id) {
		return couponDao.getCouponByUserId(user_id);
	}

	@Override
	public int updateCoupon(Coupon coupon) {
		return couponDao.updateCoupon(coupon);
	}

	@Override
	public int deleteCouponByUserId(int user_id) {
		return couponDao.deleteCouponByUserId(user_id);
	}

}
