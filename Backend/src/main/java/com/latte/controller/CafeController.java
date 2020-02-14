package com.latte.controller;

import java.net.URLEncoder;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.latte.dto.CafeDto;
import com.latte.dto.MenuDto;
import com.latte.dto.UsersLikeMenu;
import com.latte.model.KaKaoGeoResponse;
import com.latte.model.User;
import com.latte.model.post.Post;
import com.latte.payload.CafeEnrollRequest;
import com.latte.repository.UserRepository;
import com.latte.security.JwtTokenProvider;
import com.latte.service.ICafeService;
import com.latte.service.IMenuService;
import com.latte.service.IPostService;
import com.latte.service.IUsersLikeMenuService;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/v1")
@Api(value = "Cafe APIs", description = "Cafe APIs")
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
	UserRepository userRepository;

	@Autowired
	JwtTokenProvider tokenProvider;


	@ApiOperation(value = "사장님의 카페 정보 반환")
	@GetMapping("/mycafe")
	public ResponseEntity<Map<String, Object>> getMyCafeInfo(HttpServletRequest request) throws Exception {
		Map<String, Object> response = new HashMap<>();

		Long cafe_owner_id = getLoggedInUserId(request);
		//what if user_id is not OWNER auth
		if(cafe_owner_id == 0L) {
			response.put("message", "토근 만료");
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.FORBIDDEN);
		}
		
		CafeDto cafe = cafeservice.getMyCafeInfo(cafe_owner_id);
		if (cafe == null) {
			response.put("message", "카페 페이지가 존재하지 않습니다.");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NO_CONTENT);
		}
		
		response.put("cafe", cafe);
		response.put("message", "카페 정보 불러오기 성공");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
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
		List<Post> postList = postservice.getPostListByCafeId(cafe_id);

		UsersLikeMenu userslikemenu = new UsersLikeMenu();

		userslikemenu.setCafe_id(cafe_id);

		Long users_id = getLoggedInUserId(request);
		Map<String, Object> response = new HashMap<>();

		if (users_id != 0L) {
			userslikemenu.setUsers_id(users_id);
		}

		List<MenuDto> menuList = ulmservice.getUsersLikeMenuByCafeIdNUserId(userslikemenu);

		if (cafeInfo != null) {
			Long cafe_owner_id = cafeInfo.getCafe_owner_id();
			User owner = null;
			if (cafe_owner_id != null && cafe_owner_id != 0L)
				owner = userRepository.findById(cafeInfo.getCafe_owner_id()).get();
			// Generate Cafe's Time Table
			Instant[][] time = { { cafeInfo.getSun_open(), cafeInfo.getSun_close() },
					{ cafeInfo.getMon_open(), cafeInfo.getMon_close() },
					{ cafeInfo.getTue_open(), cafeInfo.getTue_close() },
					{ cafeInfo.getWed_open(), cafeInfo.getWed_close() },
					{ cafeInfo.getThu_open(), cafeInfo.getThu_close() },
					{ cafeInfo.getFri_open(), cafeInfo.getFri_close() },
					{ cafeInfo.getSat_open(), cafeInfo.getSat_close() } };
			// response.put("time", time);
			cafeInfo.setTime(time);
			response.put("cafeinfo", cafeInfo);
			if (menuList != null) {
				response.put("menu", menuList);
			}
			if (postList != null) {
				response.put("post", postList);
			}
			if (owner != null) {
				response.put("owner_name", owner.getName());
			}

		} else {
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

	@ApiOperation(value = "cafe 정보 업데이트", response = Map.class)
	@PatchMapping("/cafe")
	public ResponseEntity<Map<String, Object>> updateCafe(@RequestBody CafeDto cafedto) throws Exception {
		logger.info("CafeController-------------Cafe Update-------------" + new Date());
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

	@ApiOperation(value = "내가 좋아하는  Cafe의 리스트 반환", response = List.class)

	@GetMapping("/cafe/my")
	public ResponseEntity<List<CafeDto>> getMyCafeList(HttpServletRequest request) throws Exception {
		logger.info("CafeController-------------getMyCafeList-------------" + new Date());
		List<CafeDto> cafes = null;
		Map<String, Object> response = new HashMap<>();
		Long userId = getLoggedInUserId(request);

		if (userId == 0L) {
			response.put("message", "토근 만료");
			return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
		}

		if (userId != 0L) {
			cafes = cafeservice.getMyCafeList(userId);
		}

		if (cafes == null || cafes.size() == 0) {
			return new ResponseEntity<List<CafeDto>>(cafes, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<CafeDto>>(cafes, HttpStatus.OK);
	}

	@ApiOperation(value = "해당 Cafe의 메뉴 리스트 반환", response = List.class)
	@GetMapping("/cafe/{cafe_id}/menu")
	public ResponseEntity<List<MenuDto>> getMenuCafeIdList(@PathVariable("cafe_id") int cafe_id,
			HttpServletRequest request) throws Exception {
		logger.info("CafeController-------------getMenuCafeIdList-------------" + new Date());
		UsersLikeMenu userslikemenu = new UsersLikeMenu();
		userslikemenu.setCafe_id(cafe_id);
		Long users_id = getLoggedInUserId(request);
		Map<String, Object> response = new HashMap<>();

		if (users_id == 0L) {
			response.put("message", "토근 만료");
			return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
		}

		if (users_id != 0L) {
			userslikemenu.setUsers_id(users_id);
		}
		List<MenuDto> menuList = ulmservice.getUsersLikeMenuByCafeIdNUserId(userslikemenu);
		if (menuList == null || menuList.size() == 0) {
			return new ResponseEntity<List<MenuDto>>(menuList, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<MenuDto>>(menuList, HttpStatus.OK);
	}

	@ApiOperation(value = "Cafe 등록")
	@PostMapping("/cafe")
	public ResponseEntity<Map<String, Object>> addCafe(@Valid @RequestBody CafeEnrollRequest cafe,
			HttpServletRequest request) throws Exception {
		Map<String, Object> response = new HashMap<>();

		Long cafe_owner_id = getLoggedInUserId(request);
		if (cafe_owner_id == 0L) {
			response.put("message", "토근 만료");
			return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
		}
		// 이미 DB에 등록된 카페인지 조회
		int result = cafeservice.isExist(cafe_owner_id);

		if (result >= 1) {
			response.put("message", "이미 등록된 카페입니다!");
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		CafeDto newCafe = settingNewCafeInfo(cafe);
		newCafe.setCafe_owner_id(cafe_owner_id); // 카페 사장의 id 등록
		result = cafeservice.addCafe(newCafe);

		if (result < 1) {
			response.put("message", "카페 등록 실패");
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} else {
			response.put("message", "카페 등록 성공");
			return new ResponseEntity<>(HttpStatus.OK);
		}
		// 이미 DB에 등록된 카페가 있는지 파악(무엇으로? cafe_owner_id? 카페 주소와 연락처와 기타 내용을 바탕으로 조회?
		// 카페 등록
		// 카페 주소를 위도 경도 변환하는 라이브러리 사용
	}

	// 등록할 카페에 대한 정보 셋팅
	private CafeDto settingNewCafeInfo(CafeEnrollRequest cafe) {
		CafeDto temp = new CafeDto(cafe.getCafe_name(), cafe.getCafe_address(), cafe.getCafe_phone(),
				cafe.getThumbnail(), cafe.getClosed(), cafe.getDescription());

		// 주소로 위도 및 경도 알아내기
		String address = cafe.getCafe_address();
		Map<String, Double> latlng = getLatLngByAddress(address);

		if (latlng.size() > 0) {
			temp.setLatitude(latlng.get("lat").doubleValue());
			temp.setLongitude(latlng.get("lng").doubleValue());
		}

		Instant[][] time = cafe.getTime();
		// 카페 시간 셋팅
		// 일, 월, 화, 수, 목, 금, 토, 일
		temp.setSun_open(time[0][0]);
		temp.setSun_close(time[0][1]);
		temp.setMon_open(time[1][0]);
		temp.setMon_close(time[1][1]);
		temp.setThu_open(time[2][0]);
		temp.setTue_close(time[2][1]);
		temp.setWed_open(time[3][0]);
		temp.setWed_close(time[3][1]);
		temp.setThu_open(time[4][0]);
		temp.setTue_close(time[4][1]);
		temp.setFri_open(time[5][0]);
		temp.setFri_close(time[5][1]);
		temp.setSat_open(time[6][0]);
		temp.setSat_close(time[6][1]);
		return temp;
	}

	private Map<String, Double> getLatLngByAddress(String address) {
		Map<String, Double> map = new HashMap<>();

		try {
			String apiURL = "https://dapi.kakao.com/v2/local/search/address.json?query="
					+ URLEncoder.encode(address, "UTF-8");
			HttpResponse<JsonNode> response = Unirest.get(apiURL).header("Authorization", restApiKey).asJson();

			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);

			KaKaoGeoResponse bodyJson = objectMapper.readValue(response.getBody().toString(), KaKaoGeoResponse.class);
			// what if Documents의 길이가 2 이상이면?
			if (bodyJson.getDocuments().size() > 0) {
				map.put("lng", bodyJson.getDocuments().get(0).getX());
				map.put("lat", bodyJson.getDocuments().get(0).getY());
				// private Double x; // longitude
				// private Double y; // latitude
			}
		} catch (Exception e) {
			return null;
		}

		return map;

	}

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
