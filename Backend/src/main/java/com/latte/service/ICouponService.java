package com.latte.service;

import java.util.List;

import com.latte.dto.Coupon;
import com.latte.payload.CouponUseRequest;
import com.latte.payload.CouponUseResponse;

public interface ICouponService {
	// DB에 저장된 모든 쿠폰을 반환[관리자용]
	List<Coupon> getCouponList();

	// 카페 ID로 등록된 쿠폰을 보여줍니다.
	List<Coupon> getCouponListByCafeId(Long cafe_id);

	// Creat
	int addCoupon(Coupon coupon);

	// READ
	Coupon getCouponByUserId(Long user_id);

	// Update
	int updateCoupon(Coupon coupon);

	// Delete
	int deleteCouponByUserId(int user_id);

	int isExist(Coupon coupon);

	// 쿠폰 사용 요청
	int useCoupon(Coupon coupon);

	int getCurrentCouponCount(Coupon coupon);
	
	List<CouponUseResponse> getCouponUseRequests(int cafe_id);

	int requestCouponUse(CouponUseRequest useRequest);
}
