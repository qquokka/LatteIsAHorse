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

import com.latte.dto.MenuDto;
import com.latte.service.IMenuService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/v1")
@Api(value = "Menu APIs")
public class MenuController {
	private static final Logger logger = LoggerFactory.getLogger(MenuController.class);

	@Autowired
	IMenuService menuservice;

	@GetMapping("/menus/{cafe_id}")
	@ApiOperation(value = "카페에 등록된 메뉴 리스트 반환")
	public ResponseEntity<Map<String, Object>> getMyCafeInfo(@PathVariable("cafe_id") Integer cafe_id)
			throws Exception {
		logger.info("MenuController-------------getMyCafeInfo-------------" + new Date());
		Map<String, Object> response = new HashMap<>();

		List<MenuDto> menus = menuservice.getMenuListById(cafe_id.intValue());

		if (menus == null) {
			response.put("message", "메뉴 정보 가져오기 실패");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NO_CONTENT);
		}

		response.put("menus", menus);
		response.put("message", "메뉴 정보 가져오기 성공");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

}
