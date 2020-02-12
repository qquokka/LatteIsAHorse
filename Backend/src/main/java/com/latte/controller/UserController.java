package com.latte.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.latte.exception.AppException;
import com.latte.help.AjaxResult;
import com.latte.model.Role;
import com.latte.model.RoleName;
import com.latte.model.User;
import com.latte.payload.ApiResponse;
import com.latte.payload.UserInfoUpdateRequest;
import com.latte.payload.UserRoleUpdateRequest;
import com.latte.repository.RoleRepository;
import com.latte.repository.UserRepository;
import com.latte.security.JwtTokenProvider;
import com.latte.service.IUserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/v1")
@Api(value = "Users APIs", description = "Users APIs")
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	IUserService userService;

	@Autowired
	UserRepository userRepository;

	@Autowired
	JwtTokenProvider tokenProvider;

	@ApiOperation(value = "회원 정보 수정(전화번호, 이름")
	@PatchMapping("/userinfo")
	public ResponseEntity<Map<String, Object>> updateUserInfo(@Valid @RequestBody UserInfoUpdateRequest request)
			throws Exception {
		logger.info("UserController-------------updateUserInfo-------------" + new Date());
		Map<String, Object> response = new HashMap<>();

		int result = userService.updateUserInfo(request);

		if (result < 1) { // 등록 실패
			response.put("state", "fail");
			return new ResponseEntity(null, HttpStatus.EXPECTATION_FAILED);
		}
		response.put("state", "success");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

	@ApiOperation(value = "회원 권한 수정")
	@PatchMapping("/userrole")
	public ResponseEntity<Map<String, Object>> updateUserRole(@Valid @RequestBody UserRoleUpdateRequest request)
			throws Exception {
		logger.info("UserController-------------updateUserRole-------------" + new Date());
		Map<String, Object> response = new HashMap<>();

		String role = request.getRole().toUpperCase();
		Role userRole = null;

		if (role.equals("ADMIN")) {
			userRole = roleRepository.findByName(RoleName.ROLE_ADMIN)
					.orElseThrow(() -> new AppException("User Role not set."));
		} else if (role.equals("OWNER")) {
			userRole = roleRepository.findByName(RoleName.ROLE_OWNER)
					.orElseThrow(() -> new AppException("User Role not set."));
		} else if (role.equals("EDITOR")) {
			userRole = roleRepository.findByName(RoleName.ROLE_EDITOR)
					.orElseThrow(() -> new AppException("User Role not set."));
		} else {
			userRole = roleRepository.findByName(RoleName.ROLE_USER)
					.orElseThrow(() -> new AppException("User Role not set."));
		}

		request.setRole_id(userRole.getId());

		int result = userService.updateUserRole(request);

		if (result < 1) { // 등록 실패
			response.put("state", "fail");
			return new ResponseEntity(null, HttpStatus.EXPECTATION_FAILED);
		}
		response.put("state", "success");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

	@ApiOperation(value = "회원 탈퇴 처리")
	@DeleteMapping("/withdrawal/{id}")
	public ResponseEntity<Map<String, Object>> withdrawalUserAccount(@PathVariable("id") Long id) throws Exception {
		logger.info("UserController-------------withdrawalUserAccount-------------" + new Date());
		Map<String, Object> response = new HashMap<>();

		int result = userService.withdrawalUserAccount(id);

		if (result < 1) { // 등록 실패
			response.put("state", "fail");
			return new ResponseEntity(null, HttpStatus.EXPECTATION_FAILED);
		}
		response.put("state", "success");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

	// 닉네임(unique), 패스워드, 비밀번호 찾기 기능(이메일로 임시주소 발급?)

	@GetMapping("/user/my")
	@ApiOperation(value = "나의 정보 가져오기")
	public ResponseEntity<User> getMyInfo(HttpServletRequest request) throws Exception {
		User user = null;
		Long userId = getLoggedInUserId(request);

		if (userId != 0L) {
			user = userRepository.findById(userId).get();
		}
		if (user == null)
			return new ResponseEntity(null, HttpStatus.NO_CONTENT);

		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@GetMapping("/user/checkusername/{username}")
	@ApiOperation(value = "username 중복 확인")
	public ResponseEntity<AjaxResult> getCheckUserName(@PathVariable String username) throws Exception {
		AjaxResult ajaxResult = new AjaxResult();
		if (userRepository.existsByUsername(username)) {
			ajaxResult.setResult("FAIL");
			return new ResponseEntity<AjaxResult>(ajaxResult, HttpStatus.BAD_REQUEST);
		} else {
			ajaxResult.setResult("SUCS");
			return new ResponseEntity<AjaxResult>(ajaxResult, HttpStatus.OK);
		}
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
