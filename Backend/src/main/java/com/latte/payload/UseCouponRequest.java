package com.latte.payload;

public class UseCouponRequest {
	private Integer cafe_id;
	private Integer num_coupon;

	public UseCouponRequest() {
		super();
	}

	public UseCouponRequest(Integer cafe_id, Integer num_coupon) {
		super();
		this.cafe_id = cafe_id;
		this.num_coupon = num_coupon;
	}

	public Integer getCafe_id() {
		return cafe_id;
	}

	public void setCafe_id(Integer cafe_id) {
		this.cafe_id = cafe_id;
	}

	public Integer getNum_coupon() {
		return num_coupon;
	}

	public void setNum_coupon(Integer num_coupon) {
		this.num_coupon = num_coupon;
	}

}
