package com.latte.dto;

import java.io.Serializable;

public class CafePhotos implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id; // bigint(20) NOT NULL AUTO_INCREMENT,
	private Long cafe_id; // bigint(20) not null, -- cafe의 id
	private String path; // varchar(200) not null,
	private String file_type; // varchar(45) not null, -- 사진 파일의 확장자
	private Integer file_length;// int default 0, -- 사진 파일의 크기

	public CafePhotos() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CafePhotos(Long id, Long cafe_id, String path, String file_type, Integer file_length) {
		super();
		this.id = id;
		this.cafe_id = cafe_id;
		this.path = path;
		this.file_type = file_type;
		this.file_length = file_length;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCafe_id() {
		return cafe_id;
	}

	public void setCafe_id(Long cafe_id) {
		this.cafe_id = cafe_id;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getFile_type() {
		return file_type;
	}

	public void setFile_type(String file_type) {
		this.file_type = file_type;
	}

	public Integer getFile_length() {
		return file_length;
	}

	public void setFile_length(Integer file_length) {
		this.file_length = file_length;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
