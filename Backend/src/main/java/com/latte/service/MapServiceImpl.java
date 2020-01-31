package com.latte.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.latte.dao.MapDaoImpl;
import com.latte.dto.CafeDto;
import com.latte.model.UserLocation;

@Service
public class MapServiceImpl implements IMapService {

	@Autowired
	MapDaoImpl mapDao;

	@Override
	@Transactional(readOnly = true)
	public List<CafeDto> getCafesByUserLocation(UserLocation location) {
		return mapDao.getCafesByUserLocation(location);
	}

}