package com.latte.service;

import java.util.List;

import com.latte.dto.CafeDto;
import com.latte.model.UserLocation;
import com.latte.payload.MapHashtagResponse;

public interface IMapService {
	List<CafeDto> getCafesByUserLocation(UserLocation location);

	List<CafeDto> getCafesByUserLocationLimit(UserLocation location);

	List<MapHashtagResponse> getHashtagsByCafeIds(List<Integer> cafe_ids);
}
