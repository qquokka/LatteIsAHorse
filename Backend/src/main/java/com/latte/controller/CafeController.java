package com.latte.controller;

import java.time.Instant;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.latte.dto.CafeDto;
import com.latte.dto.MenuDto;
import com.latte.dto.ULMCUID;
import com.latte.model.post.Post;
import com.latte.security.JwtTokenProvider;
import com.latte.service.ICafeService;
import com.latte.service.IMenuService;
import com.latte.service.IPostService;
import com.latte.service.IUsersLikeMenuService;

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

	@Autowired
	IUsersLikeMenuService ulmservice;

	@Autowired
	JwtTokenProvider tokenProvider;

	@ApiOperation(value = "DB의 모든 Cafe 리스트 반환", response = List.class)
	@GetMapping("/cafe")
	public ResponseEntity<List<CafeDto>> getCafeList() throws Exception {
		logger.info("CafeController-------------getCafeList-------------" + new Date());

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
	public ResponseEntity<Map<String, Object>> getAllInfoByCafeId(@PathVariable("cafe_id") int cafe_id,
			HttpServletRequest request) throws Exception {
		logger.info("CafeController------------getAllInfoByCafeId-------------" + new Date());
		CafeDto cafeInfo = cafeservice.getCafeById(cafe_id);
		List<MenuDto> menuList = menuservice.getMenuListById(cafe_id);
		List<Post> postList = postservice.getPostListByCafeId(cafe_id);

		ULMCUID ulmcuid = new ULMCUID();
		ulmcuid.setCafe_id(cafe_id);
		Long users_id = getLoggedInUserId2(request);// 왜안대나여....
//		Long users_id = 8L;
		if (users_id != 0L) {
			ulmcuid.setUsers_id(users_id);
		}

		List<ULMCUID> ulmlist = ulmservice.getUsersLikeMenuByCafeIdNUserId(ulmcuid);
		
		
		Map<String, Object> response = new HashMap<>();

		if (cafeInfo != null) {
			response.put("cafeinfo", cafeInfo);
			// Generate Cafe's Time Table
			Instant[][] time = { { cafeInfo.getSun_open(), cafeInfo.getSun_close() },
					{ cafeInfo.getMon_open(), cafeInfo.getMon_close() },
					{ cafeInfo.getThu_open(), cafeInfo.getTue_close() },
					{ cafeInfo.getWed_open(), cafeInfo.getWed_close() },
					{ cafeInfo.getThu_open(), cafeInfo.getTue_close() },
					{ cafeInfo.getFri_open(), cafeInfo.getFri_close() },
					{ cafeInfo.getSat_open(), cafeInfo.getSat_close() } };
			response.put("time", time);
			if (menuList != null) {
				response.put("menu", menuList);
			}
			if (postList != null) {
				response.put("post", postList);
			}
			if (ulmlist != null) {
				response.put("like", ulmlist);
			}
		} else {
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

	@ApiOperation(value = "cafe 정보 업데이트", response = Map.class)
	@PatchMapping("/cafe")
	public ResponseEntity<Map<String, Object>> updateCafe(@RequestBody CafeDto cafedto) throws Exception {
		logger.info("PostController-------------Cafe Update-------------" + new Date());
		// 사용자 아이디 조회해서 수정하려는 카페가 로그인한 회원과 일치하는지 체크 구현안함.
		Map<String, Object> response = new HashMap<>();
		int result = cafeservice.updateCafe(cafedto);

		if (result < 1) { // 등록 실패
			response.put("state", "fail");
			return new ResponseEntity(null, HttpStatus.EXPECTATION_FAILED);
		}
		response.put("state", "success");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

	// ---------------------------------------------------
	// check header from request and parse JWT Token
	private Long getLoggedInUserId2(HttpServletRequest request) {
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
