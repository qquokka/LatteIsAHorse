package com.latte.dto;

import java.io.Serializable;

public class QRCode implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private Integer cafe_id;
	private Long time_stamp;
	private String code;

	public QRCode() {
		super();
	}

	public QRCode(Long id, Integer cafe_id, Long time_stamp, String code) {
		super();
		this.id = id;
		this.cafe_id = cafe_id;
		this.time_stamp = time_stamp;
		this.code = code;
	}

	public QRCode(int cafe_id, String code) {
		super();
		this.cafe_id = cafe_id;
		this.code = code;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getCafe_id() {
		return cafe_id;
	}

	public void setCafe_id(Integer cafe_id) {
		this.cafe_id = cafe_id;
	}

	public Long getTime_stamp() {
		return time_stamp;
	}

	public void setTime_stamp(Long time_stamp) {
		this.time_stamp = time_stamp;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
