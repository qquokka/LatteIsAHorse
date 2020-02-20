package com.latte.property;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "file")
public class FileUploadProperties {
	private String uploadDir; // 파일 업로드할 디렉토리 경로
	private String thumbnailDir; // 썸네일 업로드할 디렉토리 경로

	public String getUploadDir() {
		return uploadDir;
	}

	public void setUploadDir(String uploadDir) {
		this.uploadDir = uploadDir;
	}

	public String getThumbnailDir() {
		return thumbnailDir;
	}

	public void setThumbnailDir(String thumbnailDir) {
		this.thumbnailDir = thumbnailDir;
	}

}

/*
 * Spring Boot는 @ConfigurationProperties Annotation을 사용하면 자동으로
 * application.properties 파일과 POJO class를 연결 해준다.
 * 
 * POJO class로 FileUploadProperties class를 생성하고 @CongifurationProperties
 * Annotation을 사용하여 application.properties 파일과 연결했다.
 * 
 * @CongifurationProperties Annotation 에 prefix = "file" 로 선언 된 부분은
 * application.properties 에 선언한
 * 
 * file.upload-dir=path
 * 
 * file 필드에 접근한다는 말이다.
 * 
 * upload-dir 의 값에 자동으로 설정값이 연결된다.
 * 
 * 추가적으로 설정값을 추가 하고 싶다면 application.properties에 추가하고 FileUploadProperties class에
 * 필드를 추가하고 getter setter를 추가하면된다.
 * 
 * 필드명은 camel case 로 연결되기때문에 이름을 작성할때 주의하자.
 */
