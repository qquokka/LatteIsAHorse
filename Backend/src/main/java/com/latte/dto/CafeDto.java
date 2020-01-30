package com.latte.dto;

import java.io.Serializable;

public class CafeDto implements Serializable{
	private int cafe_id;
	private String cafe_name;
	private String cafe_address;
	private String cafe_phone;
	private double latitude;
	private double longitude;
	private int like_count;
	private String tag;
	private String mon_open;
	private String mon_close;
	private String tue_open;
	private String tue_close;
	private String wed_open;
	private String wed_close;
	private String thu_open;
	private String thu_close;
	private String fri_open;
	private String fri_close;
	private String sat_open;
	private String sat_close;
	private String sun_open;
	private String sun_close;
	private int closed;

	public CafeDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CafeDto(int cafe_id, String cafe_name, String cafe_address, String cafe_phone, double latitude,
			double longitude, int like_count, String tag, String mon_open, String mon_close, String tue_open,
			String tue_close, String wed_open, String wed_close, String thu_open, String thu_close, String fri_open,
			String fri_close, String sat_open, String sat_close, String sun_open, String sun_close, int closed) {
		super();
		this.cafe_id = cafe_id;
		this.cafe_name = cafe_name;
		this.cafe_address = cafe_address;
		this.cafe_phone = cafe_phone;
		this.latitude = latitude;
		this.longitude = longitude;
		this.like_count = like_count;
		this.tag = tag;
		this.mon_open = mon_open;
		this.mon_close = mon_close;
		this.tue_open = tue_open;
		this.tue_close = tue_close;
		this.wed_open = wed_open;
		this.wed_close = wed_close;
		this.thu_open = thu_open;
		this.thu_close = thu_close;
		this.fri_open = fri_open;
		this.fri_close = fri_close;
		this.sat_open = sat_open;
		this.sat_close = sat_close;
		this.sun_open = sun_open;
		this.sun_close = sun_close;
		this.closed = closed;
	}

	@Override
	public String toString() {
		return "CafeDto [cafe_id=" + cafe_id + ", cafe_name=" + cafe_name + ", cafe_address=" + cafe_address
				+ ", cafe_phone=" + cafe_phone + ", latitude=" + latitude + ", longitude=" + longitude + ", like_count="
				+ like_count + ", tag=" + tag + ", mon_open=" + mon_open + ", mon_close=" + mon_close + ", tue_open="
				+ tue_open + ", tue_close=" + tue_close + ", wed_open=" + wed_open + ", wed_close=" + wed_close
				+ ", thu_open=" + thu_open + ", thu_close=" + thu_close + ", fri_open=" + fri_open + ", fri_close="
				+ fri_close + ", sat_open=" + sat_open + ", sat_close=" + sat_close + ", sun_open=" + sun_open
				+ ", sun_close=" + sun_close + ", closed=" + closed + "]";
	}

	public int getCafe_id() {
		return cafe_id;
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

	public String getMon_open() {
		return mon_open;
	}

	public void setMon_open(String mon_open) {
		this.mon_open = mon_open;
	}

	public String getMon_close() {
		return mon_close;
	}

	public void setMon_close(String mon_close) {
		this.mon_close = mon_close;
	}

	public String getTue_open() {
		return tue_open;
	}

	public void setTue_open(String tue_open) {
		this.tue_open = tue_open;
	}

	public String getTue_close() {
		return tue_close;
	}

	public void setTue_close(String tue_close) {
		this.tue_close = tue_close;
	}

	public String getWed_open() {
		return wed_open;
	}

	public void setWed_open(String wed_open) {
		this.wed_open = wed_open;
	}

	public String getWed_close() {
		return wed_close;
	}

	public void setWed_close(String wed_close) {
		this.wed_close = wed_close;
	}

	public String getThu_open() {
		return thu_open;
	}

	public void setThu_open(String thu_open) {
		this.thu_open = thu_open;
	}

	public String getThu_close() {
		return thu_close;
	}

	public void setThu_close(String thu_close) {
		this.thu_close = thu_close;
	}

	public String getFri_open() {
		return fri_open;
	}

	public void setFri_open(String fri_open) {
		this.fri_open = fri_open;
	}

	public String getFri_close() {
		return fri_close;
	}

	public void setFri_close(String fri_close) {
		this.fri_close = fri_close;
	}

	public String getSat_open() {
		return sat_open;
	}

	public void setSat_open(String sat_open) {
		this.sat_open = sat_open;
	}

	public String getSat_close() {
		return sat_close;
	}

	public void setSat_close(String sat_close) {
		this.sat_close = sat_close;
	}

	public String getSun_open() {
		return sun_open;
	}

	public void setSun_open(String sun_open) {
		this.sun_open = sun_open;
	}

	public String getSun_close() {
		return sun_close;
	}

	public void setSun_close(String sun_close) {
		this.sun_close = sun_close;
	}

	public int getClosed() {
		return closed;
	}

	public void setClosed(int closed) {
		this.closed = closed;
	}
}