package com.cybersoft.cineflix_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cybersoft.cineflix_api.entity.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long>{
	public Users findByUsername(String username);
}
