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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.latte.dto.UsersLikeCafeDto;
import com.latte.dto.UsersLikeMenu;
import com.latte.dto.UsersLikePost;
import com.latte.security.JwtTokenProvider;
import com.latte.service.IUsersLikeCafeService;
import com.latte.service.IUsersLikeMenuService;
import com.latte.service.IUsersLikePostService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/v1")
@Api(value = "LATTE", description = "Like APIs")
public class LikeController {

	private static final Logger logger = LoggerFactory.getLogger(LikeController.class);

	@Autowired
	JwtTokenProvider tokenProvider;

	@Autowired
	IUsersLikeMenuService ulmservice; // menu

	@Autowired
	IUsersLikeCafeService userslikecafeservice; // cafe

	@Autowired
	IUsersLikePostService userslikepostService; // post

	@ApiOperation(value = "DB의 모든 UserLikeCafe 리스트 반환", response = List.class)
	@GetMapping("/userslikecafe")
	public ResponseEntity<List<UsersLikeCafeDto>> getUsersLikeCafeList() throws Exception {
		logger.info("LikeController-------------getUsersLikeCafeList-------------" + new Date());

		List<UsersLikeCafeDto> userslikecafelist = userslikecafeservice.getUsersLikeCafeList();
		if (userslikecafelist == null || userslikecafelist.size() == 0) {
			return new ResponseEntity<List<UsersLikeCafeDto>>(userslikecafelist, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<UsersLikeCafeDto>>(userslikecafelist, HttpStatus.OK);
	}

	@ApiOperation(value = "카페의 좋아요 수를 반환", response = UsersLikeCafeDto.class)
	@GetMapping("/userslikecafe/{cafe_id}")
	public ResponseEntity<UsersLikeCafeDto> getUsersLikeCafeByCafeId(@PathVariable("cafe_id") int cafe_id)
			throws Exception {
		logger.info("LikeController------------getUsersLikeCafeByCafeId-------------" + new Date());

		UsersLikeCafeDto userslikecafe = userslikecafeservice.getUsersLikeCafeByCafeId(cafe_id);
		if (userslikecafe == null) {
			return new ResponseEntity<UsersLikeCafeDto>(userslikecafe, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<UsersLikeCafeDto>(userslikecafe, HttpStatus.OK);
	}

	@ApiOperation(value = "Cafe_id로 좋아요 삽입 (User_id 자동 삽입)", response = UsersLikeCafeDto.class)
	@PostMapping("/userslikecafe")
	public ResponseEntity<UsersLikeCafeDto> addUsersLikeCafe(@RequestBody UsersLikeCafeDto userslikecafe,
			HttpServletRequest request) throws Exception {
		logger.info("LikeController-------------addUsersLikeCafe-------------" + new Date());
		Long userId = getLoggedInUserId(request);

		if (userId != 0L) {
			userslikecafe.setUsers_id(userId);
		}
		int result = userslikecafeservice.addUsersLikeCafe(userslikecafe);

		if (result < 1) { // 등록 실패
			return new ResponseEntity<UsersLikeCafeDto>(userslikecafe, HttpStatus.EXPECTATION_FAILED);
		}
		return new ResponseEntity<UsersLikeCafeDto>(userslikecafe, HttpStatus.OK);
	}

	@ApiOperation(value = "Cafe_id로 좋아요 삭제 (User_id 자동 삽입)", response = UsersLikeCafeDto.class)
	@DeleteMapping("/userslikecafe/{cafe_id}")
	public ResponseEntity<UsersLikeCafeDto> deleteUsersLikeCafe(@PathVariable("cafe_id") int cafe_id,
			HttpServletRequest request) throws Exception {
		logger.info("LikeController-------------deleteUsersLikeCafe-------------" + new Date());

		UsersLikeCafeDto userslikecafe = new UsersLikeCafeDto();
		userslikecafe.setCafe_id(cafe_id);
		Long userId = getLoggedInUserId(request);
		if (userId == 0L) {
			return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
		}
		if (userId != 0L) {
			userslikecafe.setUsers_id(userId);
		}
		int result = userslikecafeservice.deleteUsersLikeCafe(userslikecafe);

		UsersLikeCafeDto getuserslikecafe = userslikecafeservice.getUsersLikeCafeByCafeId(cafe_id); // 업데이트

		if (result < 1) { // 등록 실패
			return new ResponseEntity<UsersLikeCafeDto>(getuserslikecafe, HttpStatus.EXPECTATION_FAILED);
		}
		return new ResponseEntity<UsersLikeCafeDto>(getuserslikecafe, HttpStatus.OK);
	}

	@ApiOperation(value = "DB의 모든 UsersLikeMenu 리스트 반환", response = List.class)
	@GetMapping("/userslikemenu")
	public ResponseEntity<List<UsersLikeMenu>> getUsersLikeMenuList() throws Exception {
		logger.info("LikeController-------------getUsersLikeMenuList-------------" + new Date());

		List<UsersLikeMenu> ULMList = ulmservice.getUsersLikeMenuList();
		if (ULMList == null || ULMList.size() == 0) {
			return new ResponseEntity<List<UsersLikeMenu>>(ULMList, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<UsersLikeMenu>>(ULMList, HttpStatus.OK);
	}

//	메뉴 고유 id 에 대한 카운트 값을 돌려줌.
	@ApiOperation(value = "mid 로 메뉴의 좋아요 카운트를 반환", response = UsersLikeMenu.class)
	@GetMapping("/userslikemenu/{mid}")
	public ResponseEntity<UsersLikeMenu> getUsersLikeMenuCountBymenuId(@PathVariable("mid") int mid) throws Exception {
		logger.info("LikeController------------getUsersLikeMenuCountBymenuId-------------" + new Date());

		UsersLikeMenu ulm = ulmservice.getUsersLikeMenuCountBymenuId(mid);
		if (ulm == null) {
			return new ResponseEntity<UsersLikeMenu>(ulm, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<UsersLikeMenu>(ulm, HttpStatus.OK);
	}

	@ApiOperation(value = "Menu_id로 좋아요 삽입 (User_id 자동 삽입)", response = UsersLikeMenu.class)
	@PostMapping("/userslikemenu")
//	@PreAuthorize("hasAnyRole({'USER','OWNER','ADMIN','EDITOR'})")
	public ResponseEntity<UsersLikeMenu> addUsersLikeMenu(@RequestBody UsersLikeMenu userslikemenu,
			HttpServletRequest request) throws Exception {
		logger.info("LikeController-------------addUsersLikeMenu-------------" + new Date());

		Long users_Id = getLoggedInUserId(request);
		if (users_Id != 0L) {
			userslikemenu.setUsers_id(users_Id);
		}

		int result = ulmservice.addUsersLikeMenu(userslikemenu);
		// UsersLikeMenu ulm = ulmservice.getUsersLikeMenuCountBymenuId(mid);// 업데이트 해줌.
		if (result < 1) { // 등록 실패
			return new ResponseEntity<UsersLikeMenu>(userslikemenu, HttpStatus.EXPECTATION_FAILED);
		}
		return new ResponseEntity<UsersLikeMenu>(userslikemenu, HttpStatus.OK);
	}

	@ApiOperation(value = "Menu_id로 좋아요 삭제 (User_id 자동 삽입)", response = UsersLikeMenu.class)
	@DeleteMapping("/userslikemenu/{mid}")
//	@PreAuthorize("hasAnyRole({'USER','OWNER','ADMIN','EDITOR'})")
	public ResponseEntity<UsersLikeMenu> deleteUsersLikeMenuByUserIdNMenuId(@PathVariable("mid") int mid,
			HttpServletRequest request) throws Exception {
		logger.info("LikeController-------------deleteUsersLikeMenuByUserIdNMenuId-------------" + new Date());

		UsersLikeMenu userslikemenu = new UsersLikeMenu();
		userslikemenu.setMenu_id(mid);
		Long userId = getLoggedInUserId(request);

		if (userId != 0L) {
			userslikemenu.setUsers_id(userId);
		}

		int result = ulmservice.deleteUsersLikeMenuByUserIdNMenuId(userslikemenu);
		UsersLikeMenu ulm = ulmservice.getUsersLikeMenuCountBymenuId(mid);// 업데이트 해줌.

		if (result < 1) { // 등록 실패
			return new ResponseEntity<UsersLikeMenu>(userslikemenu, HttpStatus.EXPECTATION_FAILED);
		}
		return new ResponseEntity<UsersLikeMenu>(ulm, HttpStatus.OK);
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

	// ------------------ User Liked Post ------------------
	@ApiOperation(value = "해당 게시물에 대한 사용자의 좋아요 추가")
	@PostMapping("/userslikepost")
//	@PreAuthorize("hasAnyRole({'USER','OWNER','ADMIN','EDITOR'})")
	public ResponseEntity<Map<String, Object>> userLikedPost(@RequestBody UsersLikePost ulp, HttpServletRequest request)
			throws Exception {
		logger.info("PostController-------------User Liked Post-------------" + new Date());
		Map<String, Object> response = new HashMap<>();
		Long user_id = getLoggedInUserId(request);

		if (user_id != 0L) {
			ulp.setUser_id(user_id);
		}
		int result = userslikepostService.userLikedPost(ulp);

		if (result < 1) { // 등록 실패
			response.put("state", "fail");
			return new ResponseEntity(null, HttpStatus.EXPECTATION_FAILED);
		}

		response.put("state", "success");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);

	}

	@ApiOperation(value = "해당 게시물에 대한 사용자의 좋아요 제거")
	@DeleteMapping("/userslikepost/{post_id}")
//	@PreAuthorize("hasAnyRole({'USER','OWNER','ADMIN','EDITOR'})")
	public ResponseEntity<Map<String, Object>> userDislikedPost(@PathVariable("post_id") Long post_id,
			HttpServletRequest request) throws Exception {
		logger.info("PostController-------------User Disliked Post-------------" + new Date());

		Map<String, Object> response = new HashMap<>();

		UsersLikePost ulp = new UsersLikePost();
		ulp.setPost_id(post_id);
		Long user_id = getLoggedInUserId(request);
		if (user_id == 0L) {
			response.put("message", "토근 만료");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.FORBIDDEN);
		}
		ulp.setUser_id(user_id);

		int result = userslikepostService.userDislikedPost(ulp);

		if (result < 1) { // 등록 실패
			response.put("state", "fail");
			return new ResponseEntity(null, HttpStatus.EXPECTATION_FAILED);
		}

		response.put("state", "success");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

	@ApiOperation(value = "해당 Post에 눌린 모든 좋아요 리스트 반환")
	@GetMapping("/userslikeposts")
//	@PreAuthorize("hasAnyRole({'USER','OWNER','ADMIN','EDITOR'})")
	public ResponseEntity<List<UsersLikePost>> getUsersLikePostList() throws Exception {
		logger.info("PostController-------------User Disliked Post-------------" + new Date());

		List<UsersLikePost> usersLikePosts = userslikepostService.getUsersLikePostList();

		if (usersLikePosts == null || usersLikePosts.isEmpty()) {
			return new ResponseEntity(null, HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<List<UsersLikePost>>(usersLikePosts, HttpStatus.OK);
	}

	@ApiOperation(value = "해당 Post의 전체 좋아요 수를 반환")
	@GetMapping("/userslikepost/{post_id}")
//	@PreAuthorize("hasAnyRole({'USER','OWNER','ADMIN','EDITOR'})")
	public ResponseEntity<Map<String, Object>> getNumberOfUsersLikedPost(@PathVariable("post_id") Long id)
			throws Exception {
		logger.info("PostController-------------User getNumberOfUsersLikedPost-------------" + new Date());
		Map<String, Object> response = new HashMap<>();

		int result = userslikepostService.getNumberOfUsersLikedPost(id);
		logger.info("Result : " + result);

		response.put("num", result);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

}
