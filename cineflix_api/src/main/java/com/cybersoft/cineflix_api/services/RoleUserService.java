package com.cybersoft.cineflix_api.services;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cybersoft.cineflix_api.entity.RoleUser;
import com.cybersoft.cineflix_api.repository.RoleRepository;
import com.cybersoft.cineflix_api.repository.RoleUserRepository;

@Service
public class RoleUserService implements RoleUserServiceImp{
	
	@Autowired
	RoleUserRepository repository;

	@Override
	public List<Map<String, ?>> getAllRoleByUserName(String username) {
		// TODO Auto-generated method stub
		return repository.getAllRoleByUsername(username);
	}

	@Override
	public List<RoleUser> getAllByUserId(long userId) {
		// TODO Auto-generated method stub
		return repository.findByIdUserId(userId);
	}

}
