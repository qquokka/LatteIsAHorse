package com.latte.model;

import java.io.Serializable;

public class UserLocation implements Serializable {
	private static final long serialVersionUID = 1L;
	private Double longitude;
	private Double latitude;
	private Integer level;
	private double meter;
	private int limit;

	public UserLocation() {
		super();
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public UserLocation(Double longitude, Double latitude, Integer level, double meter, int limit) {
		super();
		this.longitude = longitude;
		this.latitude = latitude;
		this.level = level;
		this.meter = meter;
		this.limit = limit;
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
