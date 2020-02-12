package com.latte.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

//@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class MenuDto {

	private int mid;
	private String product;
	private int price;
	private String description;
	private int cafe_id;
	private int like_count;
	private int num_coupon; // 해당 메뉴 구매 가능한 최대 쿠폰 개수

	private boolean user_like;

	public MenuDto() {
		super();
	}

	public MenuDto(int mid, String product, int price, String description, int cafe_id, int like_count, int num_coupon,
			boolean user_like) {
		super();
		this.mid = mid;
		this.product = product;
		this.price = price;
		this.description = description;
		this.cafe_id = cafe_id;
		this.like_count = like_count;
		this.num_coupon = num_coupon;
		this.user_like = user_like;
	}

	public int getMid() {
		return mid;
	}

	public void setMid(int mid) {
		this.mid = mid;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getCafe_id() {
		return cafe_id;
	}

	public void setCafe_id(int cafe_id) {
		this.cafe_id = cafe_id;
	}

	public int getLike_count() {
		return like_count;
	}

	public void setLike_count(int like_count) {
		this.like_count = like_count;
	}

	public int getNum_coupon() {
		return num_coupon;
	}

	public void setNum_coupon(int num_coupon) {
		this.num_coupon = num_coupon;
	}

	public boolean isUser_like() {
		return user_like;
	}

	public void setUser_like(boolean user_like) {
		this.user_like = user_like;
	}

	@Override
	public String toString() {
		return "MenuDto [mid=" + mid + ", product=" + product + ", price=" + price + ", description=" + description
				+ ", cafe_id=" + cafe_id + "like_count=" + like_count + ", num_coupon=" + num_coupon + ", user_like="
				+ user_like + "]";
	}

	public MenuDto(int mid, String product, int price, String description, int cafe_id, int like_count,
			int num_coupon) {
		super();
		this.mid = mid;
		this.product = product;
		this.price = price;
		this.description = description;
		this.cafe_id = cafe_id;
		this.like_count = like_count;
		this.num_coupon = num_coupon;
	}

}
