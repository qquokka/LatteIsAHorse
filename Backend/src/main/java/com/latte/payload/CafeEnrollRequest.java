package com.latte.payload;

import java.time.Instant;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CafeEnrollRequest {
	@NotBlank
	private String cafe_name;
	@NotBlank
	private String cafe_address;
	@NotBlank
	private String cafe_phone;
	@NotNull
	private Instant[][] time;
	@NotNull
	private Boolean closed;
	@NotBlank
	private String description;
	@NotBlank
	private String thumbnail;

	// private double latitude;
	// private double longitude;
	public CafeEnrollRequest() {
		super();
	}

	public CafeEnrollRequest(@NotBlank String cafe_name, @NotBlank String cafe_address, @NotBlank String cafe_phone,
			@NotNull Instant[][] time, @NotNull Boolean closed, @NotBlank String description,
			@NotBlank String thumbnail) {
		super();
		this.cafe_name = cafe_name;
		this.cafe_address = cafe_address;
		this.cafe_phone = cafe_phone;
		this.time = time;
		this.closed = closed;
		this.description = description;
		this.thumbnail = thumbnail;
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

	public Instant[][] getTime() {
		return time;
	}

	public void setTime(Instant[][] time) {
		this.time = time;
	}

	public Boolean getClosed() {
		return closed;
	}

	public void setClosed(Boolean closed) {
		this.closed = closed;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

}
