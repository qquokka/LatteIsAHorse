package com.latte.model;

import java.io.Serializable;

public class UserLocation implements Serializable {
	private Double longitude;
	private Double latitude;
	private Integer level;
	private double meter;

	public UserLocation() {
		super();
	}

	public UserLocation(Double longitude, Double latitude) {
		super();
		this.longitude = longitude;
		this.latitude = latitude;
	}

	public UserLocation(Double longitude, Double latitude, Integer level) {
		super();
		this.longitude = longitude;
		this.latitude = latitude;
		this.level = level;
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

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public double getMeter() {
		return meter;
	}

	public void setMeter(double meter) {
		this.meter = meter;
	}

}
