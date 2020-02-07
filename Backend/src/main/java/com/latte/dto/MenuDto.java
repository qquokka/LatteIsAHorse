package com.latte.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class MenuDto {

	private int mid;
	private String product;
	private int price;
	private String description;
	private int cafe_id;
	private String tag;
	private int like_count;

	public MenuDto() {
		super();
	}

	public MenuDto(int mid, String product, int price, String description, int cafe_id, String tag, int like_count) {
		super();
		this.mid = mid;
		this.product = product;
		this.price = price;
		this.description = description;
		this.cafe_id = cafe_id;
		this.tag = tag;
		this.like_count = like_count;
	}

	public int getMid() {
		return mid;
	}

	public int getLike_count() {
		return like_count;
	}

	public void setLike_count(int like_count) {
		this.like_count = like_count;
	}

	public void setMid(int mid) {
		this.mid = mid;
	}

	public void setCafe_id(int cafe_id) {
		this.cafe_id = cafe_id;
	}

	@Override
	public String toString() {
		return "MenuDto [mid=" + mid + ", product=" + product + ", price=" + price + ", description=" + description
				+ ", cafe_id=" + cafe_id + ", tag=" + tag + ", like_count=" + like_count + "]";
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

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

}
