package com.latte.dto;

import java.io.Serializable;
import java.time.Instant;
import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_ABSENT)
public class CafeDto implements Serializable {
	private static final long serialVersionUID = 1L;
	private int cafe_id;
	private String cafe_name;
	private String cafe_address;
	private String cafe_phone;
	private double latitude;
	private double longitude;
	private String thumbnail; // varchar(500) default null, -- 썸네일
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private Instant mon_open;
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private Instant mon_close;
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private Instant tue_open;
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private Instant tue_close;
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private Instant wed_open;
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private Instant wed_close;
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private Instant thu_open;
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private Instant thu_close;
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private Instant fri_open;
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private Instant fri_close;
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private Instant sat_open;
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private Instant sat_close;
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private Instant sun_open;
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private Instant sun_close;
	private Boolean closed;
	private Long cafe_owner_id;
	private String description;
	private Instant[][] time;
	private int like_count;
	private String owner_name;

	public CafeDto() {
		super();
	}

	public CafeDto(int cafe_id, String cafe_name, String cafe_address, String cafe_phone, double latitude,
			double longitude, String thumbnail, Instant mon_open, Instant mon_close, Instant tue_open,
			Instant tue_close, Instant wed_open, Instant wed_close, Instant thu_open, Instant thu_close,
			Instant fri_open, Instant fri_close, Instant sat_open, Instant sat_close, Instant sun_open,
			Instant sun_close, Boolean closed, Long cafe_owner_id, String description, Instant[][] time, int like_count,
			String owner_name) {
		super();
		this.cafe_id = cafe_id;
		this.cafe_name = cafe_name;
		this.cafe_address = cafe_address;
		this.cafe_phone = cafe_phone;
		this.latitude = latitude;
		this.longitude = longitude;
		this.thumbnail = thumbnail;
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
		this.cafe_owner_id = cafe_owner_id;
		this.description = description;
		this.time = time;
		this.like_count = like_count;
		this.owner_name = owner_name;
	}

	public CafeDto(String cafe_name, String cafe_address, String cafe_phone, String thumbnail, Boolean closed,
			String description) {
		super();
		this.cafe_name = cafe_name;
		this.cafe_address = cafe_address;
		this.cafe_phone = cafe_phone;
		this.thumbnail = thumbnail;
		this.closed = closed;
		this.description = description;
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

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	public Instant getMon_open() {
		return mon_open;
	}

	public void setMon_open(Instant mon_open) {
		this.mon_open = mon_open;
	}

	public Instant getMon_close() {
		return mon_close;
	}

	public void setMon_close(Instant mon_close) {
		this.mon_close = mon_close;
	}

	public Instant getTue_open() {
		return tue_open;
	}

	public void setTue_open(Instant tue_open) {
		this.tue_open = tue_open;
	}

	public Instant getTue_close() {
		return tue_close;
	}

	public void setTue_close(Instant tue_close) {
		this.tue_close = tue_close;
	}

	public Instant getWed_open() {
		return wed_open;
	}

	public void setWed_open(Instant wed_open) {
		this.wed_open = wed_open;
	}

	public Instant getWed_close() {
		return wed_close;
	}

	public void setWed_close(Instant wed_close) {
		this.wed_close = wed_close;
	}

	public Instant getThu_open() {
		return thu_open;
	}

	public void setThu_open(Instant thu_open) {
		this.thu_open = thu_open;
	}

	public Instant getThu_close() {
		return thu_close;
	}

	public void setThu_close(Instant thu_close) {
		this.thu_close = thu_close;
	}

	public Instant getFri_open() {
		return fri_open;
	}

	public void setFri_open(Instant fri_open) {
		this.fri_open = fri_open;
	}

	public Instant getFri_close() {
		return fri_close;
	}

	public void setFri_close(Instant fri_close) {
		this.fri_close = fri_close;
	}

	public Instant getSat_open() {
		return sat_open;
	}

	public void setSat_open(Instant sat_open) {
		this.sat_open = sat_open;
	}

	public Instant getSat_close() {
		return sat_close;
	}

	public void setSat_close(Instant sat_close) {
		this.sat_close = sat_close;
	}

	public Instant getSun_open() {
		return sun_open;
	}

	public void setSun_open(Instant sun_open) {
		this.sun_open = sun_open;
	}

	public Instant getSun_close() {
		return sun_close;
	}

	public void setSun_close(Instant sun_close) {
		this.sun_close = sun_close;
	}

	public Boolean getClosed() {
		return closed;
	}

	public void setClosed(Boolean closed) {
		this.closed = closed;
	}

	public int getCafe_id() {
		return cafe_id;
	}

	public Long getCafe_owner_id() {
		return cafe_owner_id;
	}

	public void setCafe_owner_id(Long cafe_owner_id) {
		this.cafe_owner_id = cafe_owner_id;
	}

	public void setCafe_id(int cafe_id) {
		this.cafe_id = cafe_id;
	}

	public Instant[][] getTime() {
		return time;
	}

	public void setTime(Instant[][] time) {
		this.time = time;
	}

	public int getLike_count() {
		return like_count;
	}

	public void setLike_count(int like_count) {
		this.like_count = like_count;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getOwner_name() {
		return owner_name;
	}

	public void setOwner_name(String owner_name) {
		this.owner_name = owner_name;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
