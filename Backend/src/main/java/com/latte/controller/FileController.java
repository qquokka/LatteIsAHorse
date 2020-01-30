package com.latte.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.latte.payload.FileUploadResponse;
import com.latte.service.FileUploadDownloadService;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/v1")
@Api(value = "File Upload/Download APIs")
public class FileController {
	private static final Logger logger = LoggerFactory.getLogger(FileController.class);

	@Autowired
	private FileUploadDownloadService fileService;

//	@GetMapping("/file")
//	public String fileControllerMain() {
//		return "this is file controller";
//	}
	//Thumbnail

	@PostMapping("/uploadThumbnail") //파일 저장
	public FileUploadResponse uploadThumbnail(@RequestParam("file") MultipartFile file) {
		String fileName = fileService.storeFile(file, "thumbnail"); //파일 저장

		//저장한 파일 다운로드 URI 생성
		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/v1/downloadFile/").path(fileName)
				.toUriString();

		//response
		return new FileUploadResponse(fileName, fileDownloadUri, file.getContentType(), file.getSize());
	}
	
	@PostMapping("/uploadFile") //파일 저장
	public FileUploadResponse uploadFile(@RequestParam("file") MultipartFile file) {
		String fileName = fileService.storeFile(file, "images"); //파일 저장

		//저장한 파일 다운로드 URI 생성
		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/v1/downloadFile/").path(fileName)
				.toUriString();

		//response
		return new FileUploadResponse(fileName, fileDownloadUri, file.getContentType(), file.getSize());
	}

	@PostMapping("/uploadMultipleFiles") //여러 파일 저장
	public List<FileUploadResponse> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
		return Arrays.asList(files).stream().map(file -> uploadFile(file)).collect(Collectors.toList());
	}

	
	@GetMapping("/downloadFile/{fileName}") //파일 다운로드
	public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
		// Load file as Resource
		Resource resource = fileService.loadFileAsResource(fileName);

		// Try to determine file's content type
		String contentType = null;

		try {
			//파일 타입 추출
			contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
		} catch (IOException e) {
			logger.info("Could not determine file type.");
		}

		// Fallback to the default content type if type could not be determined
		if (contentType == null) {
			contentType = "application/octet-stream";
		}

		//response
		return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
				.body(resource);
	}
}
