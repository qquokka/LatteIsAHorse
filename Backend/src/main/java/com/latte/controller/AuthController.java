package com.latte.controller;

import com.latte.exception.AppException;
import com.latte.model.Role;
import com.latte.model.RoleName;
import com.latte.model.User;
import com.latte.payload.ApiResponse;
import com.latte.payload.JwtAuthenticationResponse;
import com.latte.payload.LoginRequest;
import com.latte.payload.SignUpRequest;
import com.latte.repository.RoleRepository;
import com.latte.repository.UserRepository;
import com.latte.security.JwtTokenProvider;

import io.swagger.annotations.ApiOperation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/v1")
public class AuthController {
	private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	JwtTokenProvider tokenProvider;

	@PostMapping("/signin")
	@ApiOperation(value = "로그인 처리")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
		
		//회원탈퇴 처리된 경우
		//User user = userRepository.findByUsernameOrEmail(loginRequest.getUsernameOrEmail(), loginRequest.getUsernameOrEmail());
		
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsernameOrEmail(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
	
		String jwt = tokenProvider.generateToken(authentication);
		
		User user = userRepository.findByUsernameOrEmail(loginRequest.getUsernameOrEmail(), loginRequest.getUsernameOrEmail()).get();
		
		JwtAuthenticationResponse response = new JwtAuthenticationResponse(jwt);
		response.setUsername(user.getUsername());
		
		return ResponseEntity.ok(response);
	}

	@PostMapping("/signup")
	@ApiOperation(value = "회원가입 처리")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
		logger.info("AuthController / registerUser --------------------" + new Date());

		
		//Username(Nickname)이 이미 존재할 때
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return new ResponseEntity(new ApiResponse(false, "Username is already taken!"), HttpStatus.BAD_REQUEST);
		}
		//Email이 이미 존재할 때
		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return new ResponseEntity(new ApiResponse(false, "Email Address already in use!"), HttpStatus.BAD_REQUEST);
		}
		
		// Creating user's account
		User user = new User(signUpRequest.getName(), signUpRequest.getUsername(), signUpRequest.getEmail(),
				signUpRequest.getPassword());
		
		user.setPassword(passwordEncoder.encode(user.getPassword()));

		//Default User Role is "ROLE_USER"
		Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
				.orElseThrow(() -> new AppException("User Role not set."));

		user.setRoles(Collections.singleton(userRole));

		User result = userRepository.save(user);

		URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/users/{username}")
				.buildAndExpand(result.getUsername()).toUri();

		return ResponseEntity.created(location).body(new ApiResponse(true, "User registered successfully"));
	}
	
	@GetMapping("/users")
	@ApiOperation(value = "모든 회원 정보 가져오기")
//	@PreAuthorize("hasAnyRole({'ADMIN'})")
	public ResponseEntity<List<User>> getUserList(){
		
		List<User> users = userRepository.findAll();
		
		if(users == null || users.isEmpty())
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}
//	@PostMapping("/logout")
//	public Resp
}
