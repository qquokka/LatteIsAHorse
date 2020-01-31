package com.latte.dto;

import java.io.Serializable;

public class CafePhotos implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id; // bigint(20) NOT NULL AUTO_INCREMENT,
	private Long cafe_id; // bigint(20) not null, -- cafe의 id
	private String path; // varchar(200) not null,
	private String file_type; // varchar(45) not null, -- 사진 파일의 확장자
	private Integer file_length;// int default 0, -- 사진 파일의 크기

}
