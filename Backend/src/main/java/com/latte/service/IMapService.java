package com.latte.service;

import java.util.List;

import com.latte.dto.CafeDto;
import com.latte.model.UserLocation;

public interface IMapService {
	List<CafeDto> getCafesByUserLocation(UserLocation location);
}
