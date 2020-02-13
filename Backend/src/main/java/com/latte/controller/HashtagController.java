package com.latte.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
import com.latte.payload.HashtagNameRequest;
import com.latte.payload.HashtagUpdateRequest;
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
	public ResponseEntity<Map<String, Object>> addHashtagName(@RequestBody HashtagNameRequest request) throws Exception {
		Map<String, Object> response = new HashMap<>();

		List<String> names = request.getNames();
		
		if (names.isEmpty()) {
			response.put("message", "추가할 해쉬태그명이 없습니다.");
			return new ResponseEntity<>(response, HttpStatus.OK);
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
				return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
			}
		}

		// 위에 코드까지 정상 동작 했다면 해시태그명들이 DB에 존재할 것이니 where in 구문으로 조회
		List<Hashtag> hashtags = hashtagService.getAllHashtagByNames(names);

		response.put("message", "해쉬태그명 추가 완료");
		response.put("hashtags", hashtags);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

	@ApiOperation(value = "Post에 등록된 해쉬태그의 id 변경 UPDATE", response = Map.class)
	@PatchMapping("/hashtag")
	public ResponseEntity<Map<String, Object>> updateHashtagId(@RequestBody HashtagUpdateRequest request)
			throws Exception {
		Map<String, Object> response = new HashMap<>();

		if (request.getNames().isEmpty()) {
			response.put("message", "수정할 해쉬태그명이 없습니다.");
			return new ResponseEntity<>(HttpStatus.OK);
		}

		Long post_id = request.getPost_id();
		List<String> names = request.getNames();

		// Add new hashtag name
		for (String name : names) {
			// 해쉬태그명이 이미 존재하는지
			int result = hashtagService.isHashtagNameExist(name);

			if (result >= 1)
				continue;

			// 존재하지 않으면 새로운 해시태그명 등록
			result = hashtagService.addHashtagName(name);

			if (result < 1) {
				response.put("message", "해쉬태그명 추가 실패");
				return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
			}
		} // 새로운 해쉬태그명들 DB에 추가됨

		// ex) 1,2,3,4,5 hashtag_id
		List<Integer> post_hashtags = hashtagService.getAllHashtagIdByPostId(post_id);
		// 2,3,4,5
		List<Hashtag> hashtags = hashtagService.getAllHashtagByNames(names);

		List<Integer> delCadidates = new ArrayList<>();

		for (Integer hashtag_id : post_hashtags) {
			for (Hashtag hashtag : hashtags) {
				if (hashtag_id.intValue() == hashtag.getHashtag_id().intValue())
					break;
			}
			// 삭제할 후보 hashtag_id
			delCadidates.add(hashtag_id);
		}

		for (Hashtag hashtag : hashtags) {
			// 이미 등록된 것이니 패스
			if (post_hashtags.contains(hashtag.getHashtag_id())) {
				continue;
			} else { // 새로운 post_hashtag 추가
				int result = hashtagService.addHashtagId(new PostHashtag(post_id, hashtag.getHashtag_id()));
				if (result < 1) {
					response.put("message", "Posthashtag 추가 실패");
					return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
				}
			}
		}

		// post_hashtag 제거
		for (Integer candi : delCadidates) {
			int result = hashtagService.deletePostHashtag(new PostHashtag(post_id, candi));
			if (result < 1) {
				response.put("message", "Posthashtag 제거 실패");
				return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
			}
		}

		response.put("message", "해쉬태그명 추가 완료");
		response.put("hashtags", hashtags);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

	@ApiOperation(value = "Post에 등록된 해쉬태그의 id 삭제 DELETE", response = Map.class)
	@DeleteMapping("/hashtag/{post_id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Map<String, Object>> deleteHashtagId(@PathVariable("post_id") Long post_id) throws Exception {
		Map<String, Object> response = new HashMap<>();
		//기능 구현 예정
		//관리자만 삭제 가능하게?
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
