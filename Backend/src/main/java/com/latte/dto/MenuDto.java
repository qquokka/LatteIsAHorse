package com.latte.dto;

public class MenuDto {

	private int mid;
	private String product;
	private int price;
	private String description;
	private int cafe_id;
	private String tag;
	public MenuDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MenuDto(int mid, String product, int price, String description, int cafe_id, String tag) {
		super();
		this.mid = mid;
		this.product = product;
		this.price = price;
		this.description = description;
		this.cafe_id = cafe_id;
		this.tag = tag;
	}
	@Override
	public String toString() {
		return "MenuDto [mid=" + mid + ", product=" + product + ", price=" + price + ", description=" + description
				+ ", cafe_id=" + cafe_id + ", tag=" + tag + "]";
	}
	public int getMid() {
		return mid;
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
