package com.latte.controller;

import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import java.security.AlgorithmParameters;
import java.security.SecureRandom;
import java.time.Instant;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.latte.dto.Coupon;
import com.latte.dto.QRCode;
import com.latte.payload.QRCodeGenerateRequest;
import com.latte.payload.UseCouponRequest;
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
	private final String key = "latte coupon";
	private final long validMiliSeconde = 60000; // 1 min

	@Autowired
	ICouponService couponService;

	@Autowired
	IQRCodeService qrcodeService;

	@Autowired
	JwtTokenProvider tokenProvider;

	@PutMapping("/coupon/{code}")
	@ApiOperation(value = "쿠폰 등록하기")

//	@PreAuthorize("hasAnyRole({'USER','OWNER','ADMIN','EDITOR'})")
	public ResponseEntity<Map<String, Object>> enrollCoupon(@PathVariable("code") String code, HttpServletRequest request) throws Exception {

		String decryptedCode = decryptAES256(code);
		// format : "cafe_id,count,time_stamp"
		String[] data = decryptedCode.split(",");

		int cafe_id = Integer.parseInt(data[0]);
		int count = Integer.parseInt(data[1]);
		long time_stamp = Long.parseLong(data[2]);

		Map<String, Object> response = new HashMap<String, Object>();
		Long users_id = getLoggedInUserId(request);

		if (users_id == 0L) {
			response.put("message", "토근 만료");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.FORBIDDEN);
		}

		Coupon coupon = new Coupon();
		coupon.setCafe_id(cafe_id);
		coupon.setUsers_id(users_id);
		coupon.setCount(count);

		// 쿠픈 등록 시간 만료 체크
		long currentTime = Instant.now().toEpochMilli();
		if (currentTime > time_stamp + validMiliSeconde) {
			response.put("message", "쿠폰 등록 시간이 만료되었습니다.");
			// DB에 등록된 qrcode 삭제
			qrcodeService.deleteQRCode(new QRCode(cafe_id, decryptedCode));
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}

		int result = couponService.isExist(coupon);

		// 쿠폰 정보가 이미 DB에 존재 하는지
		result = (result < 1) ? couponService.addCoupon(coupon) : couponService.updateCoupon(coupon);

		if (result >= 1) {
			response.put("message", "쿠폰 등록 성공");
			qrcodeService.deleteQRCode(new QRCode(cafe_id, decryptedCode));
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		} else {
			response.put("message", "쿠폰 등록 실패");
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/coupon/{cafe_id}")
	@ApiOperation(value = "현재 로그인된 사용자가 보유한 쿠폰 갯수 반환")
//	@PreAuthorize("hasAnyRole({'USER','OWNER','ADMIN','EDITOR'})")
	public ResponseEntity<Map<String, Object>> getCurrentCouponCount(@PathVariable("cafe_id") Integer cafe_id,
			HttpServletRequest request) throws Exception {
		Long users_id = getLoggedInUserId(request);
		Map<String, Object> response = new HashMap<String, Object>();

		if (users_id == 0L) {
			response.put("message", "토근 만료");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.FORBIDDEN);
		}

		Coupon coupon = new Coupon();
		coupon.setCafe_id(cafe_id);
		coupon.setUsers_id(users_id);

		// 현재 보유한 쿠폰 갯수(등록되어 있지 않다면 기본값 = 0)
		int numberOfCoupon = couponService.getCurrentCouponCount(coupon);

		response.put("message", "보유하신 쿠폰은 " + numberOfCoupon + "개 입니다.");
		response.put("numberOfCoupon", numberOfCoupon);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PatchMapping("/coupon/{cafe_id}")
	@ApiOperation(value = "사용자가 보유한 쿠폰 사용")
//	@PreAuthorize("hasAnyRole({'USER','OWNER','ADMIN','EDITOR'})")
	public ResponseEntity<Map<String, Object>> useCoupon(@Valid @RequestBody UseCouponRequest ucr,
			HttpServletRequest request) throws Exception {
		Long users_id = getLoggedInUserId(request);
		Map<String, Object> response = new HashMap<String, Object>();

		if (users_id == 0L) {
			response.put("message", "토근 만료");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.FORBIDDEN);
		}

		Coupon coupon = new Coupon();
		coupon.setCafe_id(ucr.getCafe_id()); // 수정
		coupon.setUsers_id(users_id);

		// 현재 보유한 쿠폰 갯수(등록되어 있지 않다면 기본값 = 0)
		int numberOfCoupon = couponService.getCurrentCouponCount(coupon);

		if (numberOfCoupon - ucr.getNum_coupon() < 0) {
			response.put("message", "보유하신 쿠폰의 개수가 부족합니다.");
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		coupon.setCount(ucr.getNum_coupon());
		int result = couponService.useCoupon(coupon);

		if (result < 1) {
			response.put("message", "쿠폰을 사용하실 수 없습니다.");
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		} else {
			// response.put("numberOfCoupon", numberOfCoupon);
			response.put("message", "쿠폰이 완료되었습니다.");
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
	}

	@PostMapping("/qrcode")
	@ApiOperation(value = "QR code 생성")
//	@PreAuthorize("hasAnyRole({'OWNER','ADMIN'})")
	public ResponseEntity<Map<String, Object>> generateQRcode(@Valid @RequestBody QRCodeGenerateRequest request) throws Exception {
		Map<String, Object> response = new HashMap<String, Object>();

		// format : "cafe_id,count,time_stamp"
		StringBuilder sb = new StringBuilder();
		sb.append(Integer.toString(request.getCafe_id()) + ",");
		sb.append(Integer.toString(request.getCount()) + ",");
		long time_stamp = Instant.now().toEpochMilli(); // QRcode 생성 시간
		sb.append(Long.toString(time_stamp));

		// code 암호화
		String encryptedCode = encryptAES256(sb.toString().trim());

		// 암호회된 코드가 DB에 존재 하는지 확인
		int result = qrcodeService.isExist(encryptedCode);
		// 이미 등록되어 있다면 -> 이미 발급된 쿠폰 코드
		if (result >= 1) {
			response.put("message", "이미 발급된 쿠폰입니다!");
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		} else {
			// DB에 등록되어 있지 않다면 -> 등록 가능한 유효 쿠폰
			QRCode qrcode = new QRCode();
			qrcode.setCafe_id(request.getCafe_id());
			qrcode.setTime_stamp(time_stamp);
			qrcode.setCode(encryptedCode);

			result = qrcodeService.addQRCode(qrcode);

			if (result < 1) {
				response.put("message", "QR 코드 생성 실패!");
				return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
			} else {
				// 쿠폰 등록 요청 URL
				String uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/v1/coupon/")
						.path(encryptedCode).toUriString();

				QRCodeWriter qrCodeWriter = new QRCodeWriter();
				BitMatrix bitMatrix = qrCodeWriter.encode(uri, BarcodeFormat.QR_CODE, 200, 300);

				ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
				MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutputStream);
				byte[] pngData = pngOutputStream.toByteArray();
				response.put("qrcode", pngData);
				response.put("uri", uri);
				response.put("message", "QR 코드 생성 성공!");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
			}
		}

		// 쿠폰 등록 만료시간
		// cafe_id
		// 쿠폰 발급 갯수
	}

	// 암호화
	private String encryptAES256(String message) throws Exception {
		SecureRandom random = new SecureRandom();
		byte[] bytes = new byte[20];
		random.nextBytes(bytes);
		byte[] saltBytes = bytes;

		// Password-based key derivation function 2
		SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
		// Hashing 7만번 to create 256 bit key
		PBEKeySpec spec = new PBEKeySpec(key.toCharArray(), saltBytes, 70000, 256);

		SecretKey secretKey = factory.generateSecret(spec);
		SecretKeySpec secret = new SecretKeySpec(secretKey.getEncoded(), "AES");

		// 알고리즘/모드/패딩
		// CBC : Cipher Block Chaining Mode
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, secret);
		AlgorithmParameters params = cipher.getParameters();
		// Initial vector(1단계 암호화 블록용)
		byte[] ivBytes = params.getParameterSpec(IvParameterSpec.class).getIV();

		byte[] encryptedTextBytes = cipher.doFinal(message.getBytes("UTF-8"));

		byte[] buffer = new byte[saltBytes.length + ivBytes.length + encryptedTextBytes.length];
		System.arraycopy(saltBytes, 0, buffer, 0, saltBytes.length);
		System.arraycopy(ivBytes, 0, buffer, saltBytes.length, ivBytes.length);
		System.arraycopy(encryptedTextBytes, 0, buffer, saltBytes.length + ivBytes.length, encryptedTextBytes.length);

		return Base64.getEncoder().encodeToString(buffer);
	}

	// 복호화
	private String decryptAES256(String msg) throws Exception {

		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

		ByteBuffer buffer = ByteBuffer.wrap(Base64.getDecoder().decode(msg));

		byte[] saltBytes = new byte[20];

		buffer.get(saltBytes, 0, saltBytes.length);

		byte[] ivBytes = new byte[cipher.getBlockSize()];

		buffer.get(ivBytes, 0, ivBytes.length);

		byte[] encryoptedTextBytes = new byte[buffer.capacity() - saltBytes.length - ivBytes.length];

		buffer.get(encryoptedTextBytes);

		SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");

		PBEKeySpec spec = new PBEKeySpec(key.toCharArray(), saltBytes, 70000, 256);

		SecretKey secretKey = factory.generateSecret(spec);

		SecretKeySpec secret = new SecretKeySpec(secretKey.getEncoded(), "AES");

		cipher.init(Cipher.DECRYPT_MODE, secret, new IvParameterSpec(ivBytes));

		byte[] decryptedTextBytes = cipher.doFinal(encryoptedTextBytes);

		return new String(decryptedTextBytes);

	}

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
