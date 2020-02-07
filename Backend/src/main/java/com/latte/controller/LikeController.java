package com.latte.controller;

import java.util.Date;
import java.util.List;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.latte.dto.UsersLikeMenu;
import com.latte.security.JwtTokenProvider;
import com.latte.service.IUsersLikeMenuService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/v1")
@Api(value = "LATTE", description = "Like APIs")
public class LikeController {

	private static final Logger logger = LoggerFactory.getLogger(LikeController.class);

	@Autowired
	IUsersLikeMenuService ulmservice;

	@Autowired
	JwtTokenProvider tokenProvider;

//	@Autowired
//	IUsersLikeCafeService userlikecafeservice;

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

	@ApiOperation(value = " Menu_id로 좋아요 삽입(User_id 자동 삽입)", response = UsersLikeMenu.class)
	@PostMapping("/userslikemenu/{mid}")
//	@PreAuthorize("hasAnyRole({'USER','OWNER','ADMIN','EDITOR'})")
	public ResponseEntity<UsersLikeMenu> addUsersLikeMenu(@PathVariable("mid") int mid, HttpServletRequest request)
			throws Exception {
		logger.info("LikeController-------------addUsersLikeMenu-------------" + new Date());

		UsersLikeMenu userslikemenu = new UsersLikeMenu();
		userslikemenu.setMenu_id(mid);

		Long userId = getLoggedInUserId(request);
		if (userId != 0L) {
		userslikemenu.setUsers_id(userId);
		}

		int result = ulmservice.addUsersLikeMenu(userslikemenu);

		UsersLikeMenu ulm = ulmservice.getUsersLikeMenuCountBymenuId(mid);// 업데이트 해줌.

		if (result < 1) { // 등록 실패
			return new ResponseEntity<UsersLikeMenu>(userslikemenu, HttpStatus.EXPECTATION_FAILED);
		}
		return new ResponseEntity<UsersLikeMenu>(ulm, HttpStatus.OK);
	}

	@ApiOperation(value = " Menu_id로 좋아요 삭제(User_id 자동 삽입)", response = UsersLikeMenu.class)
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

}
