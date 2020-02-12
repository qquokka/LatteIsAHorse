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
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.latte.model.User;
import com.latte.model.post.Post;
import com.latte.model.post.PostComments;
import com.latte.repository.UserRepository;
import com.latte.security.JwtTokenProvider;
import com.latte.service.IPostService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/v1")
@Api(value = "Post Comments APIs", description = "Post Comments APIs")
public class PostCommentsController {
	private static final Logger logger = LoggerFactory.getLogger(PostCommentsController.class);

	@Autowired
	private IPostService postservice;

	@Autowired
	JwtTokenProvider tokenProvider;

	@Autowired
	UserRepository userRepository;

	@ApiOperation(value = "해당 post에 등록된 모든 comments 조회", response = List.class)
	@GetMapping("/comments/{post_id}")
	public ResponseEntity<List<PostComments>> getPostCommentsByPostId(@PathVariable("post_id") Long post_id)
			throws Exception {
		logger.info("PostCommentsController-------------Get Post's Comments-------------" + new Date());

		List<PostComments> comments = postservice.getPostCommentsByPostId(post_id);

		if (comments == null || comments.size() == 0) {
			return new ResponseEntity(null, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<PostComments>>(comments, HttpStatus.OK);
	}

	@ApiOperation(value = "post에 comment 등록", response = Map.class)
	@PostMapping("/comments")
//	@PreAuthorize("hasAnyRole({'USER','OWNER','ADMIN','EDITOR'})")
	public ResponseEntity<Map<String, Object>> addPostComments(@RequestBody PostComments comment,
			HttpServletRequest request) throws Exception {
		logger.info("PostCommentsController-------------Post Comment Add-------------" + new Date());

		Map<String, Object> response = new HashMap<>();

		User user = userRepository.getOne(getLoggedInUserId(request));
		comment.setWriter_id(user.getId());

		int result = postservice.addPostComments(comment);

		if (result < 1) { // 등록 실패
			response.put("state", "fail");
			return new ResponseEntity(null, HttpStatus.EXPECTATION_FAILED);
		}

		response.put("state", "success");
		response.put("username", user.getUsername());
		response.put("user_id", user.getId());

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

	@ApiOperation(value = "post comment 정보 업데이트", response = Map.class)
	@PatchMapping("/comments")
	// @PreAuthorize("hasAnyRole({'USER','OWNER','ADMIN','EDITOR'})")
	public ResponseEntity<Map<String, Object>> updatePostComments(@RequestBody PostComments comment) throws Exception {
		logger.info("PostCommentsController-------------Post Comment Update-------------" + new Date());

		// 사용자 아이디 조회해서 수정하려는 글이 로그인한 회원과 일치하는지 체크

		Map<String, Object> response = new HashMap<>();

		int result = postservice.updatePostComments(comment);

		if (result < 1) { // 등록 실패
			response.put("state", "fail");
			return new ResponseEntity(null, HttpStatus.EXPECTATION_FAILED);
		}

		response.put("state", "success");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

	@ApiOperation(value = "post comment 삭제", response = Map.class)
	@DeleteMapping("/comments/{id}")
	// @PreAuthorize("hasAnyRole({'USER','OWNER','ADMIN','EDITOR'})")
	public ResponseEntity<Map<String, Object>> deletePostComments(@PathVariable("id") Long id) throws Exception {
		logger.info("PostCommentsController-------------Post Comment Delete-------------" + new Date());

		Map<String, Object> response = new HashMap<>();

		int result = postservice.deletePostComments(id);

		if (result < 1) { // 삭제 실패
			response.put("state", "fail");
			return new ResponseEntity(null, HttpStatus.EXPECTATION_FAILED);
		}

		response.put("state", "success");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

	@ApiOperation(value = "DB의 모든 comments 조회", response = List.class)
	@GetMapping("/comments")
	public ResponseEntity<List<PostComments>> getAllPostComments() throws Exception {
		logger.info("PostCommentsController-------------Get All Post's Comments List-------------" + new Date());

		List<PostComments> comments = postservice.getAllPostComments();

		if (comments == null || comments.size() == 0) {
			return new ResponseEntity(null, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<PostComments>>(comments, HttpStatus.OK);
	}

	@ApiOperation(value = "내가 쓴 comments의 리스트 반환", response = List.class)
	@GetMapping("/comments/my")
	public ResponseEntity<List<PostComments>> getMyPostCommentsList(HttpServletRequest request) throws Exception {
		logger.info("PostCommentsController-------------getMyPostCommentsList-------------" + new Date());
		List<PostComments> comments = null;
		Long userId = getLoggedInUserId(request);
		if (userId != 0L) {
			comments = postservice.getMyPostCommentsList(userId);
		}

		if (comments == null || comments.size() == 0) {
			return new ResponseEntity<List<PostComments>>(comments, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<PostComments>>(comments, HttpStatus.OK);
	}

	// ---------------------------------------------------
	// check header from request and parse JWT Token
	private Long getLoggedInUserId(HttpServletRequest request) {
		String bearerToken = request.getHeader("Authorization");
		if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
			String jwt = bearerToken.substring(7, bearerToken.length());
			if (StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt)) {
				return tokenProvider.getUserIdFromJWT(jwt);
			}
		}
		return 0L;
	}

}
