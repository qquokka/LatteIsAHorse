package com.latte.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.latte.dto.CafeDto;
import com.latte.model.UserLocation;
import com.latte.payload.MapHashtagResponse;

@Repository
public class MapDaoImpl {
	String ns = "mapper.map."; // ns : namespace

	@Autowired
	private SqlSession sqlSession;

	public List<CafeDto> getCafesByUserLocation(UserLocation location) {
		return sqlSession.selectList(ns + "getCafesByUserLocation", location);
	}

	public List<CafeDto> getCafesByUserLocationLimit(UserLocation location) {
		return sqlSession.selectList(ns + "getCafesByUserLocationLimit", location);
	}

	public List<MapHashtagResponse> getHashtagsByCafeIds(List<Integer> cafe_ids) {
		Map<String, Object> param = new HashMap<>();
		param.put("cafe_ids", cafe_ids);
		return sqlSession.selectList(ns + "getHashtagsByCafeIds", param);
	}
}
