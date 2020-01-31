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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.latte.dto.CafeDto;
import com.latte.dto.MenuDto;
import com.latte.model.post.Post;
import com.latte.service.ICafeService;
import com.latte.service.IMenuService;
import com.latte.service.IPostService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/v1")

@Api(value = "LATTE", description = "Cafe APIs")
public class CafeController {
	private static final Logger logger = LoggerFactory.getLogger(CafeController.class);

	@Autowired
	ICafeService cafeservice;
	
	@Autowired
	IMenuService menuservice;
	
	@Autowired
	IPostService postservice;

	@ApiOperation(value = "요청에 따른 Cafe 리스트 반환", response = List.class)
	@GetMapping("/cafe")
	public ResponseEntity<List<CafeDto>> getCafeList() throws Exception {
		logger.info("CafeController-------------Cafe List-------------" + new Date());

		List<CafeDto> cafes = cafeservice.getCafeList();
		if (cafes == null || cafes.size() == 0) {
			return new ResponseEntity<List<CafeDto>>(cafes, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<CafeDto>>(cafes, HttpStatus.OK);
	}

	@ApiOperation(value = "해당 cafe_id 에 대한 기본 정보 반환", response = CafeDto.class)
	@GetMapping("/cafe/{cafe_id}")
	public ResponseEntity<CafeDto> getCafeById(@PathVariable("cafe_id") int cafe_id) throws Exception {
		logger.info("CafeController------------getCafeById-------------" + new Date());
		CafeDto cafe = cafeservice.getCafeById(cafe_id);
		if (cafe == null) {
			return new ResponseEntity<CafeDto>(cafe, HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<CafeDto>(cafe, HttpStatus.OK);
	}

	// Cafe info, menu, posts, comments return

	@ApiOperation(value = "해당 cafe_id 에 대한 모든 정보 반환", response = Map.class)
	@GetMapping("/cafe/detail/{cafe_id}")
	public ResponseEntity<Map<String, Object>> getAllInfoByCafeId(@PathVariable("cafe_id") int cafe_id)
			throws Exception {
		logger.info("CafeController------------getAllInfoByCafeId-------------" + new Date());
		CafeDto cafeInfo = cafeservice.getCafeById(cafe_id);
		List<MenuDto> menuList = menuservice.getMenuListById(cafe_id);
		List<Post> postList = postservice.getPostListByCafeId(cafe_id);
//		List<PostComments> postcommentList = postservice.
		Map<String, Object> response = new HashMap<>();
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

}
//--------------------------------------------------------
/*
 * @ApiOperation(value = "post 등록", response = Map.class)
 * 
 * @PostMapping("/addPost") // @PreAuthorize("permitAll")
 * 
 * @PreAuthorize("hasAnyRole({'USER','OWNER','ADMIN','EDITOR'})") // //API 사용권한
 * 부여 public ResponseEntity<Map<String, Object>> addPost(@RequestBody
 * PostAddRequest post, HttpServletRequest request) throws Exception {
 * logger.info("PostController-------------Post add-------------" + new Date());
 * 
 * Long userId = getLoggedInUserId(request); Long lastId =
 * postservice.getLastPostedId();
 * 
 * Map<String, Object> response = new HashMap<>();
 * 
 * if (userId != 0L) { post.setWriter_id(userId); } // 사진 있다면 사진 등록하는 로직 추가 //
 * NumberResult response = new NumberResult(); int result =
 * postservice.addPost(post);
 * 
 * if (result < 1) { // 등록 실패 response.put("state", "fail"); return new
 * ResponseEntity(null, HttpStatus.EXPECTATION_FAILED); }
 * 
 * response.put("state", "success"); response.put("posted_id", lastId + 1);
 * response.put("userId", userId);
 * 
 * return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK); }
 * 
 * @ApiOperation(value = "post의 title로 조회", response = List.class)
 * 
 * @GetMapping("/post/title/{title}") public ResponseEntity<List<Post>>
 * getPostByTitle(@PathVariable("title") String title) throws Exception {
 * logger.info("PostController-------------Get Post by Title-------------" + new
 * Date());
 * 
 * List<Post> posts = postservice.getPostByTitle(title);
 * 
 * if (posts == null || posts.isEmpty()) { return new
 * ResponseEntity(HttpStatus.NO_CONTENT); }
 * 
 * return new ResponseEntity<List<Post>>(posts, HttpStatus.OK); }
 * 
 * @ApiOperation(value = "post의 id로 조회", response = Post.class)
 * 
 * @GetMapping("/post/{id}") // @JsonIgnoreProperties public
 * ResponseEntity<Post> getPostById(@PathVariable("id") Long id) throws
 * Exception {
 * logger.info("PostController-------------Get Post by Id-------------" + new
 * Date());
 * 
 * Post post = null; post = postservice.getPostById(id);
 * 
 * if (post == null) { return new ResponseEntity(HttpStatus.NO_CONTENT); }
 * 
 * return new ResponseEntity<Post>(post, HttpStatus.OK); }
 * 
 * @ApiOperation(value = "post 정보 업데이트", response = Map.class)
 * 
 * @PatchMapping("/post/{id}")
 * 
 * @PreAuthorize("hasAnyRole({'USER','OWNER','ADMIN','EDITOR'})") // 수정 필요
 * public ResponseEntity<Map<String, Object>> updatePostById(@PathVariable("id")
 * Long id) throws Exception {
 * logger.info("PostController-------------Post Update-------------" + new
 * Date());
 * 
 * // 사용자 아이디 조회해서 수정하려는 글이 로그인한 회원과 일치하는지 체크
 * 
 * Map<String, Object> response = new HashMap<>();
 * 
 * int result = postservice.updatePostById(id);
 * 
 * if (result < 1) { // 등록 실패 response.put("state", "fail"); return new
 * ResponseEntity(null, HttpStatus.EXPECTATION_FAILED); }
 * 
 * response.put("state", "success"); return new ResponseEntity<Map<String,
 * Object>>(response, HttpStatus.OK); }
 * 
 * @ApiOperation(value = "post 삭제", response = Map.class)
 * 
 * @DeleteMapping("/post/{id}")
 * 
 * @PreAuthorize("hasAnyRole({'USER','OWNER','ADMIN','EDITOR'})") // //API 사용권한
 * 부여 public ResponseEntity<Map<String, Object>>
 * deletePostById(@PathVariable("id") Long id) throws Exception {
 * logger.info("PostController-------------Post Update-------------" + new
 * Date());
 * 
 * // 사용자 아이디 조회해서 삭제하려는 글이 로그인한 회원과 일치하는지 체크
 * 
 * Map<String, Object> response = new HashMap<>();
 * 
 * int result = postservice.updatePostById(id);
 * 
 * if (result < 1) { // 등록 실패 response.put("state", "fail"); return new
 * ResponseEntity(null, HttpStatus.EXPECTATION_FAILED); }
 * 
 * response.put("state", "success"); return new ResponseEntity<Map<String,
 * Object>>(response, HttpStatus.OK); }
 * 
 * // --------------------------------------------------- // check header from
 * request and parse JWT Token private Long getLoggedInUserId(HttpServletRequest
 * request) { String bearerToken = request.getHeader("Authorization"); if
 * (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
 * String jwt = bearerToken.substring(7, bearerToken.length());
 * if(StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt)) { return
 * tokenProvider.getUserIdFromJWT(jwt); } } return 0L; }
 */