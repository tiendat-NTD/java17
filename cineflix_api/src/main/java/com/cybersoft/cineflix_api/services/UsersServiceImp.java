package com.cybersoft.cineflix_api.services;

import com.cybersoft.cineflix_api.entity.Users;

public interface UsersServiceImp {
	public Users findByUsername(String username);
}
