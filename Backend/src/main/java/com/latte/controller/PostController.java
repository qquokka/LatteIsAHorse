package com.latte.controller;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.latte.help.NumberResult;
import com.latte.model.post.Post;
import com.latte.payload.PostRequest;
import com.latte.service.IPostService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/v1")
@Api(value = "LATTE", description = "Post APIs")
public class PostController {
	private static final Logger logger = LoggerFactory.getLogger(PostController.class);

	@Autowired
	private IPostService postservice;

	@ApiOperation(value = "Get post list", response = List.class)
	@PostMapping("/post")
	//@PreAuthorize("hasRole('USER') or hasRole('OWNER') or hasRole('ADMIN')")
	public ResponseEntity<List<Post>> getPostList(@RequestBody PostRequest request) throws Exception {

		List<Post> posts = postservice.getPostList(request);
		logger.info("-------------Post size -------------" + posts.size());

		if (posts == null || posts.size() == 0) {
			return new ResponseEntity<List<Post>>(posts, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Post>>(posts, HttpStatus.OK);
	}


}
