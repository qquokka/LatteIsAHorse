package com.latte.service;

import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.latte.exception.FileDownloadException;
import com.latte.exception.FileUploadException;
import com.latte.property.FileUploadProperties;

@Service
public class FileUploadDownloadService {
	private final Path fileLocation;
	private final Path thumbnailLocation;

	 private static final Logger logger = LoggerFactory.getLogger(FileUploadDownloadService.class);
	/*
	 * 다음으로 파일이 저장될 디렉토리를 설정하고 디렉토리를 생성하는 소스를 추가한다. Service가 실행될때 생성자에서 기존에 생성한
	 * 설정클래스인 FileUploadProperties 클래스로 기본 디렉토리를 설정하고 생성한다.
	 */
	@Autowired
	public FileUploadDownloadService(FileUploadProperties prop) {
		this.fileLocation = Paths.get(prop.getUploadDir()).toAbsolutePath().normalize();
		this.thumbnailLocation = Paths.get(prop.getThumbnailDir()).toAbsolutePath().normalize();
		// logger.info(this.fileLocation.toString());
		try {
			Files.createDirectories(this.fileLocation); // 일반 이미지 디렉토리
			Files.createDirectories(this.thumbnailLocation); // 썸네일 이미지 디렉토리
		} catch (Exception e) {
			throw new FileUploadException("파일을 업로드할 디렉토리를 생성하지 못했습니다.", e);
		}
	}

	// 파일을 저장
	public String storeFile(MultipartFile file, String requestType) {
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		SimpleDateFormat format1 = new SimpleDateFormat ("yyyyMMddHHmmss");
		String format_time1 = format1.format (System.currentTimeMillis());
		fileName = format_time1+"_"+fileName;
		try {
			// 파일명에 부적합 문자가 있는지 확인한다.
			if (fileName.contains(".."))
				throw new FileUploadException("파일명에 부적합 문자가 포함되어 있습니다. " + fileName);

			Path targetLocation = null;
			if (requestType.equals("images"))
				targetLocation = this.fileLocation.resolve(fileName);

			if (requestType.equals("thumbnail"))
				targetLocation = this.thumbnailLocation.resolve(fileName);

			Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

			return fileName;
		} catch (Exception e) {
			throw new FileUploadException("[" + fileName + "] 파일 업로드에 실패하였습니다. 다시 시도하십시오.", e);
		}
	}

	// 파일 다운로드
	public Resource loadFileAsResource(String fileName, String requestType) {
		try {
			
			Path filePath = null;;
			if (requestType.equals("images"))
				filePath = this.fileLocation.resolve(fileName).normalize();

			if (requestType.equals("thumbnail")) {
				filePath = this.thumbnailLocation.resolve(fileName).normalize();
				logger.info(filePath.toString());
			}
			
			Resource resource = new UrlResource(filePath.toUri());

			if (resource.exists()) {
				return resource;
			} else {
				throw new FileDownloadException(fileName + " 파일을 찾을 수 없습니다.");
			}
		} catch (MalformedURLException e) {
			throw new FileDownloadException(fileName + " 파일을 찾을 수 없습니다.", e);
		}
	}

}
