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

import com.latte.model.post.PostHashtag;
import com.latte.service.IPostService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/v1")
@Api(value = "Post Hashtag APIs")
public class PostHashtagController {
	private static final Logger logger = LoggerFactory.getLogger(PostHashtagController.class);
	
	@Autowired
	private IPostService postservice;

	// -------------Post Hashtag APIs----------------------
	@ApiOperation(value = "해당 post의 모든 hashtag 조회", response = List.class)
	@GetMapping("/hashtags/{post_id}")
	public ResponseEntity<List<PostHashtag>> getPostHashTags(@PathVariable("post_id") Long post_id) throws Exception {
		logger.info("PostController-------------Get Post's Hashtags-------------" + new Date());

		List<PostHashtag> hashTags = postservice.getPostHashTags(post_id);

		if (hashTags == null || hashTags.size() == 0) {
			return new ResponseEntity(null, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<PostHashtag>>(hashTags, HttpStatus.OK);
	}

	@ApiOperation(value = "DB에 등록된 모든 hashtag 조회", response = List.class)
	@GetMapping("/hashtags")
	public ResponseEntity<List<PostHashtag>> getAllHashTags() throws Exception {
		logger.info("PostController-------------All Hashtags-------------" + new Date());

		List<PostHashtag> hashTags = postservice.getAllHashTags();

		if (hashTags == null || hashTags.size() == 0) {
			return new ResponseEntity(null, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<PostHashtag>>(hashTags, HttpStatus.OK);
	}

	@ApiOperation(value = "post hashtag 등록", response = Map.class)
	@PostMapping("/hashtags")
//	@PreAuthorize("hasAnyRole({'USER','OWNER','ADMIN','EDITOR'})")
	public ResponseEntity<Map<String, Object>> addPostHashtag(@RequestBody PostHashtag hashTag) throws Exception {
		logger.info("PostController-------------Post Add-------------" + new Date());

		Map<String, Object> response = new HashMap<>();

		int result = postservice.addPostHashtag(hashTag);

		if (result < 1) { // 등록 실패
			response.put("state", "fail");
			return new ResponseEntity(null, HttpStatus.EXPECTATION_FAILED);
		}

		response.put("state", "success");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

	@ApiOperation(value = "post hashtag 정보 업데이트", response = Map.class)
	@PatchMapping("/hashtags")
	// @PreAuthorize("hasAnyRole({'USER','OWNER','ADMIN','EDITOR'})")
	public ResponseEntity<Map<String, Object>> updatePostHashtag(@RequestBody PostHashtag hashTag) throws Exception {
		logger.info("PostController-------------Post Hashtag Update-------------" + new Date());

		// 사용자 아이디 조회해서 수정하려는 글이 로그인한 회원과 일치하는지 체크

		Map<String, Object> response = new HashMap<>();

		int result = postservice.updatePostHashtag(hashTag);

		if (result < 1) { // 등록 실패
			response.put("state", "fail");
			return new ResponseEntity(null, HttpStatus.EXPECTATION_FAILED);
		}

		response.put("state", "success");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

	@ApiOperation(value = "post hasgtag 삭제", response = Map.class)
	@DeleteMapping("/hashtags/{id}")
	// @PreAuthorize("hasAnyRole({'USER','OWNER','ADMIN','EDITOR'})")
	public ResponseEntity<Map<String, Object>> deletePostHashtag(@PathVariable("id") Long id) throws Exception {
		logger.info("PostController-------------Post Hashtag Delete-------------" + new Date());

		Map<String, Object> response = new HashMap<>();

		int result = postservice.deletePostHashtag(id);

		if (result < 1) { // 삭제 실패
			response.put("state", "fail");
			return new ResponseEntity(null, HttpStatus.EXPECTATION_FAILED);
		}

		response.put("state", "success");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
}