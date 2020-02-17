package com.latte.payload;

import java.time.Instant;

import javax.validation.constraints.NotNull;

public class CouponUseRequest {
	private Long id; // PK
	private Long user_id; // 쿠폰 사용자의 id
	@NotNull
	private Integer cafe_id;
	private Integer count; // 사용한 쿠폰의 개수
	@NotNull
	private Integer mid; // menu id
	private Instant created_at;

	public CouponUseRequest() {
		super();
	}

	public CouponUseRequest(Long id, Long user_id, @NotNull Integer cafe_id, Integer count, @NotNull Integer mid,
			Instant created_at) {
		super();
		this.id = id;
		this.user_id = user_id;
		this.cafe_id = cafe_id;
		this.count = count;
		this.mid = mid;
		this.created_at = created_at;
	}

	public Instant getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Instant created_at) {
		this.created_at = created_at;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

	public Integer getCafe_id() {
		return cafe_id;
	}

	public void setCafe_id(Integer cafe_id) {
		this.cafe_id = cafe_id;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Integer getMid() {
		return mid;
	}

	public void setMid(Integer mid) {
		this.mid = mid;
	}

}
