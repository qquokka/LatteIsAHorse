package com.latte.payload;

import javax.validation.constraints.NotNull;

public class QRCodeGenerateRequest {
	@NotNull
	private Integer cafe_id;
	
	@NotNull
	private Integer count;

	public QRCodeGenerateRequest() {
		super();
	}

	public QRCodeGenerateRequest(Integer cafe_id, Integer count) {
		super();
		this.cafe_id = cafe_id;
		this.count = count;
	}

	public Integer getCafe_id() {
		return cafe_id;
	}

	public void setCafe_id(Integer cafe_id) {
		this.cafe_id = cafe_id;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

}
