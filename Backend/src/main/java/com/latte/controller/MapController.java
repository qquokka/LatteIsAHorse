package com.latte.controller;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.latte.model.post.Post;
import com.latte.service.ICafeService;
import com.latte.service.IMenuService;
import com.latte.service.IPostService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/v1")
@Api(value = "Map APIs")
public class MapController {
	private static final Logger logger = LoggerFactory.getLogger(MapController.class);
	
	@Autowired
	ICafeService cafeservice;

	@Autowired
	IMenuService menuservice;

	@Autowired
	IPostService postservice;
	
	@ApiOperation(value = "요청에 따른 Post 리스트 반환", response = List.class)
	@GetMapping("/map")
	// @PreAuthorize("hasRole('USER') or hasRole('OWNER') or hasRole('ADMIN')")
	// //API 사용권한 부여
	public ResponseEntity<List<Post>> getPostList() throws Exception {
		logger.info("PostController-------------Post List-------------" + new Date());

		List<Post> posts = postservice.getPostList();

		if (posts == null || posts.size() == 0) {
			return new ResponseEntity<List<Post>>(posts, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Post>>(posts, HttpStatus.OK);
	}
}
