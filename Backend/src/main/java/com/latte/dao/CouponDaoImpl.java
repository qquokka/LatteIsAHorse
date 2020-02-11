
package com.latte.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.latte.dto.Coupon;

@Repository
public class CouponDaoImpl {
	private final String ns = "mapper.coupon.";

	@Autowired
	private SqlSession sqlSession;

	// DB 에 저장된 모든 쿠폰을 반환[관리자용]
	public List<Coupon> getCouponList() {
		return sqlSession.selectList(ns + "getCouponList");
	}

	// 카페아이디로 등록된 쿠폰을 보여줍니다.
	public List<Coupon> getCouponListByCafeId(Long cafe_id) {
		return sqlSession.selectList(ns + "getCouponListByCafeId", cafe_id);
	}

	// Create
	public int addCoupon(Coupon coupon) {
		return sqlSession.insert(ns + "addCoupon", coupon);
	}

	// Read
	public Coupon getCouponByUserId(Long user_id) {
		return sqlSession.selectOne(ns + "getCouponByUserId", user_id);
	}

	// Update
	public int updateCoupon(Coupon coupon) {
		return sqlSession.update(ns + "updateCoupon", coupon);
	}

	// Delete
	public int deleteCouponByUserId(int user_id) {
		return sqlSession.delete(ns + "deleteCouponByUserId", user_id);
	}

	public int isExist(Coupon coupon) {
		return sqlSession.selectOne(ns + "isExist", coupon);
	}

	public int useCoupon(Coupon coupon) {
		return sqlSession.update(ns + "useCoupon", coupon);
	}

	public int getCurrentCouponCount(Coupon coupon) {
		return sqlSession.selectOne(ns + "getCurrentCouponCount", coupon);
	}

}
