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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.latte.dto.CafeDto;
import com.latte.model.UserLocation;
import com.latte.payload.CafeIdsRequest;
import com.latte.payload.MapHashtagResponse;
import com.latte.service.IMapService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/v1")
@Api(value = "Map APIs", description = "Map APIs")
public class MapController {
	private static final Logger logger = LoggerFactory.getLogger(MapController.class);
	private final double limitRange = 7.0; // km
	@Autowired
	IMapService mapService;

	@ApiOperation(value = "사용자 현재 위치와 확대 수준에 따른 카페 리스트 반환", response = List.class)
	@PostMapping("/map")
	// @PreAuthorize("hasRole('USER') or hasRole('OWNER') or hasRole('ADMIN')")
	public ResponseEntity<List<CafeDto>> getCafesByUserLocation(@RequestBody UserLocation location) throws Exception {
		logger.info("MapController-------------Get near cafe list by current user location-------------" + new Date());

		// Converting level value to meter
		// location.setMeter(convertLeveltoKilioMeter(location.getLevel()));
		location.setMeter(limitRange);
		logger.info("Level : " + location.getLevel());
		logger.info("Latitude : " + location.getLatitude());
		logger.info("Longitude : " + location.getLongitude());
		List<CafeDto> cafes = mapService.getCafesByUserLocation(location);

		if (cafes == null || cafes.size() == 0) {
			return new ResponseEntity<List<CafeDto>>(cafes, HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<List<CafeDto>>(cafes, HttpStatus.OK);
	}

	@ApiOperation(value = "사용자 현재 위치와 확대 수준에 따른 카페 리스트 반환", response = List.class)
	@PostMapping("/map/limit")
	// @PreAuthorize("hasRole('USER') or hasRole('OWNER') or hasRole('ADMIN')")
	public ResponseEntity<List<CafeDto>> getCafesByUserLocationLimit(@RequestBody UserLocation location)
			throws Exception {
		logger.info("MapController-------------Get near cafe list by current user location-------------" + new Date());

		location.setMeter(limitRange);
		logger.info("Level : " + location.getLevel());
		logger.info("Latitude : " + location.getLatitude());
		logger.info("Longitude : " + location.getLongitude());
		List<CafeDto> cafes = mapService.getCafesByUserLocationLimit(location);

		if (cafes == null || cafes.size() == 0) {
			return new ResponseEntity<List<CafeDto>>(cafes, HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<List<CafeDto>>(cafes, HttpStatus.OK);
	}

	@ApiOperation(value = "카페 id들로 해시태그 찾아서 반환")
	@PostMapping("/map/hashtags")
	// @PreAuthorize("hasRole('USER') or hasRole('OWNER') or hasRole('ADMIN')")
	public ResponseEntity<Map<String, Object>> getHashtagsByCafeIds(@RequestBody CafeIdsRequest request)
			throws Exception {
		Map<String, Object> response = new HashMap<>();

		if (request.getCafe_ids().isEmpty()) {
			response.put("message", "처리할 cafe_id가 없습니다.");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		}

		List<MapHashtagResponse> mapHashtags = mapService.getHashtagsByCafeIds(request.getCafe_ids());
//		for(MapHashtagResponse obj : mapHashtags) {
//			logger.info(obj.toString());	
//		}

		if (mapHashtags == null || mapHashtags.isEmpty()) {
			response.put("message", "조회된 결과가 없거나 실패");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		response.put("message", "카페 아이디로 해시태그 조회 성공");
		response.put("map_hashtags", mapHashtags);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

	private double convertLeveltoKilioMeter(Integer level) {
		double meter = 0;

		switch (level.intValue()) {
		case (17):
			meter = 0.05;
			break;
		case (16):
			meter = 0.1;
			break;
		case (15):
			meter = 0.2;
			break;
		case (14):
			meter = 0.5;
			break;
		case (13):
			meter = 1;
			break;
		case (12):
			meter = 2;
			break;
		case (11):
			meter = 2;
			break;
		case (10):
			meter = 5;
			break;
		case (9):
			meter = 10;
			break;
		case (8):
			meter = 20;
			break;
		}

		return meter;
	}
}
//이미지 지도의 확대 수준을 반환한다.
//0부터 14까지의 정수이며, 지도가 확대되어 있을수록 작은 값을 반환한다.