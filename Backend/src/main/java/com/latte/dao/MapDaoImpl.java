package com.latte.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.latte.dto.CafeDto;
import com.latte.model.UserLocation;

@Repository
public class MapDaoImpl {
	String ns = "mapper.map."; // ns : namespace

	@Autowired
	private SqlSession sqlSession;

	public List<CafeDto> getCafesByUserLocation(UserLocation location) {
		return sqlSession.selectList(ns + "getCafesByUserLocation", location);
	}

}
