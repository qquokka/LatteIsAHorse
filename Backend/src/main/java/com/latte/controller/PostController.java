package com.latte.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.latte.model.post.Post;
import com.latte.model.post.PostHashtag;
import com.latte.payload.PostAddRequest;
import com.latte.payload.PostRequest;
import com.latte.security.JwtTokenProvider;
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
	JwtTokenProvider tokenProvider;

	@Autowired
	private IPostService postservice;

	@ApiOperation(value = "요청에 따른 Post 리스트 반환", response = List.class)
	@PostMapping("/post")
	// @PreAuthorize("hasRole('USER') or hasRole('OWNER') or hasRole('ADMIN')")
	// //API 사용권한 부여
	public ResponseEntity<List<Post>> getPostList(@RequestBody PostRequest request) throws Exception {
		logger.info("PostController-------------Post List-------------" + new Date());

		List<Post> posts = postservice.getPostList(request);

		if (posts == null || posts.size() == 0) {
			return new ResponseEntity<List<Post>>(posts, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Post>>(posts, HttpStatus.OK);
	}

	@ApiOperation(value = "post 등록", response = Map.class)
	@PostMapping("/addPost")
//	@PreAuthorize("permitAll")
	@PreAuthorize("hasAnyRole({'USER','OWNER','ADMIN','EDITOR'})")
	// //API 사용권한 부여
	public ResponseEntity<Map<String, Object>> addPost(@RequestBody PostAddRequest post, HttpServletRequest request) throws Exception {
		logger.info("PostController-------------Post add-------------" + new Date());

		Long userId = getLoggedInUserId(request);
		Long lastId = postservice.getLastPostedId();

		Map<String, Object> response = new HashMap<>();
		
		if(userId != 0L) {
			post.setWriter_id(userId);
		}
		// 사진 있다면 사진 등록하는 로직 추가
		// NumberResult response = new NumberResult();
		int result = postservice.addPost(post);

		if (result < 1) { // 등록 실패
			response.put("state", "fail");
			return new ResponseEntity(null, HttpStatus.EXPECTATION_FAILED);
		}

		response.put("state", "success");
		response.put("posted_id", lastId + 1);
		response.put("userId", userId);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

	@ApiOperation(value = "post의 title로 조회", response = List.class)
	@GetMapping("/post/title/{title}")
	public ResponseEntity<List<Post>> getPostByTitle(@PathVariable("title") String title) throws Exception {
		logger.info("PostController-------------Get Post by Title-------------" + new Date());

		List<Post> posts = postservice.getPostByTitle(title);

		if (posts == null || posts.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<List<Post>>(posts, HttpStatus.OK);
	}

	@ApiOperation(value = "post의 id로 조회", response = Post.class)
	@GetMapping("/post/{id}")
	//@JsonIgnoreProperties
	public ResponseEntity<Post> getPostById(@PathVariable("id") Long id) throws Exception {
		logger.info("PostController-------------Get Post by Id-------------" + new Date());

		Post post = null;
		post = postservice.getPostById(id);

		if (post == null) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<Post>(post, HttpStatus.OK);
	}

	
	@ApiOperation(value = "post 정보 업데이트", response = Map.class)
	@PatchMapping("/post/{id}")
	@PreAuthorize("hasAnyRole({'USER','OWNER','ADMIN','EDITOR'})")
	// 수정 필요
	public ResponseEntity<Map<String, Object>> updatePostById(@PathVariable("id") Long id) throws Exception {
		logger.info("PostController-------------Post Update-------------" + new Date());
		
		//사용자 아이디 조회해서 수정하려는 글이 로그인한 회원과 일치하는지 체크
		
		Map<String, Object> response = new HashMap<>();
		
		int result = postservice.updatePostById(id);

		if (result < 1) { // 등록 실패
			response.put("state", "fail");
			return new ResponseEntity(null, HttpStatus.EXPECTATION_FAILED);
		}

		response.put("state", "success");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	@ApiOperation(value = "post 삭제", response = Map.class)
	@DeleteMapping("/post/{id}")
	@PreAuthorize("hasAnyRole({'USER','OWNER','ADMIN','EDITOR'})")
	// //API 사용권한 부여
	public ResponseEntity<Map<String, Object>> deletePostById(@PathVariable("id") Long id) throws Exception {
		logger.info("PostController-------------Post Update-------------" + new Date());
		
		//사용자 아이디 조회해서 삭제하려는 글이 로그인한 회원과 일치하는지 체크
		
		Map<String, Object> response = new HashMap<>();
		
		int result = postservice.updatePostById(id);

		if (result < 1) { // 등록 실패
			response.put("state", "fail");
			return new ResponseEntity(null, HttpStatus.EXPECTATION_FAILED);
		}

		response.put("state", "success");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	
	//-------------Post Hashtag APIs----------------------
	@ApiOperation(value = "해당 post의 모든 hashtag 조회", response = List.class)
	@GetMapping("/hashtags/{post_id}")
	public ResponseEntity<List<PostHashtag>> getPostHashTags(@PathVariable("post_id") Long post_id) throws Exception{
		logger.info("PostController-------------Get Post's Hashtags-------------" + new Date());

		List<PostHashtag> hashTags = postservice.getPostHashTags(post_id);

		if (hashTags == null || hashTags.size() == 0) {
			return new ResponseEntity(null, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<PostHashtag>>(hashTags, HttpStatus.OK);
	}

	@ApiOperation(value = "DB에 등록된 모든 hashtag 조회", response = List.class)
	@GetMapping("/hashtags")
	public ResponseEntity<List<PostHashtag>> getAllHashTags() throws Exception{
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
	//@PreAuthorize("hasAnyRole({'USER','OWNER','ADMIN','EDITOR'})")
	public ResponseEntity<Map<String, Object>> updatePostHashtag(@RequestBody PostHashtag hashTag) throws Exception {
		logger.info("PostController-------------Post Hashtag Update-------------" + new Date());
		
		//사용자 아이디 조회해서 수정하려는 글이 로그인한 회원과 일치하는지 체크
		
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
	//@PreAuthorize("hasAnyRole({'USER','OWNER','ADMIN','EDITOR'})")
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
	
	// ---------------------------------------------------
	// check header from request and parse JWT Token
	private Long getLoggedInUserId(HttpServletRequest request) {
		String bearerToken = request.getHeader("Authorization");
		if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
			String jwt = bearerToken.substring(7, bearerToken.length());
			if(StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt)) {
				return tokenProvider.getUserIdFromJWT(jwt);
			}
		}
		return 0L;
	}
}
