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

import com.latte.dto.CafeDto;
import com.latte.dto.UsersLikeMenu;
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

//	@Autowired
//	IUsersLikeCafeService userlikecafeservice;
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

//	@ApiOperation(value = "해당 user_id 로 좋아하는 메뉴를 리스트로 반환", response = UsersLikeMenu.class)
//	@GetMapping("/userslikemenu/{user_id}")
//	public ResponseEntity<UsersLikeMenu> getUsersLikeMenuListByUserId(@PathVariable("user_id") Long user_id)
//			throws Exception {
//		logger.info("LikeController------------getUsersLikeMenuListByUserId-------------" + new Date());
//		UsersLikeMenu ULM = ulmservice.getUsersLikeMenuListByUserId(user_id);
//		if (ULM == null) {
//			return new ResponseEntity<UsersLikeMenu>(ULM, HttpStatus.NO_CONTENT);
//		}
//
//		return new ResponseEntity<UsersLikeMenu>(ULM, HttpStatus.OK);
//	}

}