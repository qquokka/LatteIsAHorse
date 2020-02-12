package com.latte.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.latte.dto.CafeDto;
import com.latte.model.PostHashtag;
import com.latte.service.IHashTagService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/v1")
@Api(value = "Post Hashtag APIs" , description = "Post Hashtag APIs")
public class HashtagController {
	private static final Logger logger = LoggerFactory.getLogger(HashtagController.class);
	
	@Autowired
	private IHashTagService hashtagService;

	@ApiOperation(value = "해쉬태그명 추가(여러개)", response = Map.class)
	@PostMapping("/hashtagname")
	public ResponseEntity<Map<String, Object>> addHashtagName(@RequestBody List<String> hashtags) throws Exception {
		Map<String, Object> response = new HashMap<>();
		
		for(String name : hashtags) {
			int result = hashtagService.isHashtagNameExist(name);
			
			if(result >= 1) continue;
			
			result = hashtagService.addHashtagName(name);
			
			if(result < 1) {
				response.put("message", "해쉬태그명 추가 실패");
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
		}
		
		response.put("message", "해쉬태그명 추가 완료");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Post에 등록된 해쉬태그의 id 변경 UPDATE", response = Map.class)
	@PatchMapping("/hashtag/{post_id}")
	public ResponseEntity<Map<String, Object>> updateHashtagId(@PathVariable("post_id") Long post_id) throws Exception {
		Map<String, Object> response = new HashMap<>();
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Post에 등록된 해쉬태그의 id 삭제 DELETE", response = Map.class)
	@DeleteMapping("/hashtag/{post_id}")
	public ResponseEntity<Map<String, Object>> deleteHashtagId(@PathVariable("post_id") Long post_id) throws Exception {
		Map<String, Object> response = new HashMap<>();
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Post에 등록된 해쉬태그의 id 추가 INSERT", response = Map.class)
	@PostMapping("/hashtag/{post_id}")
	public ResponseEntity<Map<String, Object>> addHashtagId(@PathVariable("post_id") Long post_id) throws Exception {
		Map<String, Object> response = new HashMap<>();
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Post에 등록된 모든 해쉬태그명 반환 String List", response = Map.class)
	@GetMapping("/hashtag")
	public ResponseEntity<Map<String, Object>> hashtagsInThePost() throws Exception {
		Map<String, Object> response = new HashMap<>();
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
}
