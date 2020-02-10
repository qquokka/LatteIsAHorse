package com.latte.controller;

import java.io.ByteArrayOutputStream;
import java.net.URI;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

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
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.latte.dto.QRCode;
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
import com.latte.service.ICouponService;
import com.latte.service.IQRCodeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/v1")
@Api(value = "Coupon APIs")
public class CouponController {
	private static final Logger logger = LoggerFactory.getLogger(CouponController.class);

	@Autowired
	ICouponService couponService;
	
	@Autowired
	IQRCodeService qrcodeService;
	
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	JwtTokenProvider tokenProvider;

	@GetMapping("/qrcode/{cafe_id}")
	@ApiOperation(value = "QR code 생성")
	public ResponseEntity<Map<String, Object>> generateQRcode(@PathVariable("cafe_id") Integer cafe_id) throws Exception{
		QRCode qrcode = new QRCode();
		Map<String, Object> response = new HashMap<String, Object>();

		qrcode.setCafe_id(cafe_id);
		QRCodeWriter qrCodeWriter = new QRCodeWriter();
		BitMatrix bitMatrix = qrCodeWriter.encode("asdfasdfs", BarcodeFormat.QR_CODE, 200, 300);
		
		ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
		MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutputStream);
		byte[] pngData = pngOutputStream.toByteArray();
		response.put("qrcode", pngData);
		//쿠폰 등록 요청 URL
		//쿠폰 등록 만료시간
		//cafe_id
		//쿠폰 발급 갯수
		//QRcode 생성 시간
		
		return null;
	}
}
