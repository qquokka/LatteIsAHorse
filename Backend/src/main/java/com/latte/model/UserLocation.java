package com.latte.model;

import java.io.Serializable;

public class UserLocation implements Serializable {
	private Double longitude;
	private Double latitude;
	private Integer ratio;

	public UserLocation() {
		super();
	}

	public UserLocation(Double longitude, Double latitude) {
		super();
		this.longitude = longitude;
		this.latitude = latitude;
	}

	public UserLocation(Double longitude, Double latitude, Integer ratio) {
		super();
		this.longitude = longitude;
		this.latitude = latitude;
		this.ratio = ratio;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Integer getRatio() {
		return ratio;
	}

	public void setRatio(Integer ratio) {
		this.ratio = ratio;
	}

}
