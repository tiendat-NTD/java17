package com.cybersoft.cineflix_api.services;

import java.util.List;
import java.util.Map;

import com.cybersoft.cineflix_api.entity.RoleUser;

public interface RoleUserServiceImp {
	public List<Map<String,  ?>> getAllRoleByUserName(String username);
	public List<RoleUser> getAllByUserId(long userId);
}
