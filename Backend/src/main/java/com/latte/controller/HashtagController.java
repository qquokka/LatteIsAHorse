package com.latte.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

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

import com.latte.model.Hashtag;
import com.latte.model.PostHashtag;
import com.latte.payload.PostHashtagRequest;
import com.latte.service.IHashTagService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/v1")
@Api(value = "Post Hashtag APIs", description = "Post Hashtag APIs")
public class HashtagController {
	private static final Logger logger = LoggerFactory.getLogger(HashtagController.class);

	@Autowired
	private IHashTagService hashtagService;

	@ApiOperation(value = "해쉬태그명 추가(여러개)", response = Map.class)
	@PostMapping("/hashtagname")
	public ResponseEntity<Map<String, Object>> addHashtagName(@RequestBody List<String> names) throws Exception {
		Map<String, Object> response = new HashMap<>();

		if (names.isEmpty()) {
			response.put("message", "추가할 해쉬태그명이 없습니다.");
			return new ResponseEntity<>(HttpStatus.OK);
		}

		for (String name : names) {
			// 해쉬태그명이 이미 존재하는지
			int result = hashtagService.isHashtagNameExist(name);

			if (result >= 1)
				continue;

			// 존재하지 않으면 새로운 해시태그명 등록
			result = hashtagService.addHashtagName(name);

			if (result < 1) {
				response.put("message", "해쉬태그명 추가 실패");
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
		}

		// 위에 코드까지 정상 동작 했다면 해시태그명들이 DB에 존재할 것이니 where in 구문으로 조회
		List<Hashtag> hashtags = hashtagService.getAllHashtagByNames(names);

		response.put("message", "해쉬태그명 추가 완료");
		response.put("hashtags", hashtags);
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
	@PostMapping("/hashtag")
	public ResponseEntity<Map<String, Object>> addHashtagId(@Valid @RequestBody PostHashtagRequest request)
			throws Exception {
		Map<String, Object> response = new HashMap<>();

		if (request.getHashtag_ids().isEmpty()) {
			response.put("message", "추가할 해쉬태그 ID가 없습니다.");
			return new ResponseEntity<>(HttpStatus.OK);
		}

		PostHashtag post_hashtag = new PostHashtag();
		post_hashtag.setPosts_id(request.getPost_id());

		for (Integer id : request.getHashtag_ids()) {
			post_hashtag.setHashtag_id(id);
			int result = hashtagService.addHashtagId(post_hashtag);

			if (result < 1)
				continue;
		}

		response.put("message", "PostHashtag 추가 완료");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

	@ApiOperation(value = "Post에 등록된 모든 해쉬태그명 반환 String List", response = Map.class)
	@GetMapping("/hashtag/{post_id}")
	public ResponseEntity<Map<String, Object>> hashtagsInThePost(@PathVariable("post_id") Long post_id)
			throws Exception {
		Map<String, Object> response = new HashMap<>();

		List<String> hashtagNames = hashtagService.hashtagsInThePost(post_id);

		if (hashtagNames == null || hashtagNames.isEmpty()) {
			response.put("message", "등록된 해시태그가 없습니다.");
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		response.put("names", hashtagNames);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
}
