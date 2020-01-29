package com.latte.dto;

public class CafeDto {
	private int cafe_id;
	private String cafe_name;
	private String cafe_address;
	private String cafe_phone;
	private double latitude;
	private double longitude;
	private int like_count;
	private String tag;

	public CafeDto() {
		super();
	}

	public CafeDto(int cafe_id, String cafe_name, String cafe_address, String cafe_phone, double latitude,
			double longitude, int like_count, String tag) {
		super();
		this.cafe_id = cafe_id;
		this.cafe_name = cafe_name;
		this.cafe_address = cafe_address;
		this.cafe_phone = cafe_phone;
		this.latitude = latitude;
		this.longitude = longitude;
		this.like_count = like_count;
		this.tag = tag;
	}

	@Override
	public String toString() {
		return "CafeDto [cafe_id=" + cafe_id + ", cafe_name=" + cafe_name + ", cafe_address=" + cafe_address
				+ ", cafe_phone=" + cafe_phone + ", latitude=" + latitude + ", longitude=" + longitude + ", like_count="
				+ like_count + ", tag=" + tag + "]";
	}

	public int getCafe_id() {
		return cafe_id;
	}

	public void setCafe_id(int cafe_id) {
		this.cafe_id = cafe_id;
	}

	public String getCafe_name() {
		return cafe_name;
	}

	public void setCafe_name(String cafe_name) {
		this.cafe_name = cafe_name;
	}

	public String getCafe_address() {
		return cafe_address;
	}

	public void setCafe_address(String cafe_address) {
		this.cafe_address = cafe_address;
	}

	public String getCafe_phone() {
		return cafe_phone;
	}

	public void setCafe_phone(String cafe_phone) {
		this.cafe_phone = cafe_phone;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public int getLike_count() {
		return like_count;
	}

	public void setLike_count(int like_count) {
		this.like_count = like_count;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

}
