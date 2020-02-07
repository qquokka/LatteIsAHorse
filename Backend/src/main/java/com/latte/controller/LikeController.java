package com.latte.controller;

import java.util.Date;
import java.util.List;

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

import com.latte.dto.UsersLikeMenu;
import com.latte.service.IUsersLikeMenuService;

import com.latte.dto.UsersLikeCafeDto;
import com.latte.service.IUsersLikeCafeService;

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
	IUsersLikeCafeService userslikecafeservice;

//	@Autowired
//	

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
	public ResponseEntity<UsersLikeCafeDto> getUsersLikeCafeByCafeId(@PathVariable("cafe_id") int cafe_id) throws Exception {
		logger.info("LikeController------------getUsersLikeCafeByCafeId-------------" + new Date());

		UsersLikeCafeDto userslikecafe = userslikecafeservice.getUsersLikeCafeByCafeId(cafe_id);
		if (userslikecafe == null) {
			return new ResponseEntity<UsersLikeCafeDto>(userslikecafe, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<UsersLikeCafeDto>(userslikecafe, HttpStatus.OK);
	}
}
