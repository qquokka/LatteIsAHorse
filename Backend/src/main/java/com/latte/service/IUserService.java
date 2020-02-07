package com.latte.service;

import java.util.List;

import com.latte.payload.UserInfoUpdateRequest;
import com.latte.payload.UserRoleUpdateRequest;

public interface IUserService {
	int updateUserInfo(UserInfoUpdateRequest request);
	
	int withdrawalUserAccount(Long id); 
	
	int updateUserRole(UserRoleUpdateRequest request);
}
