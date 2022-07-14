package com.cybersoft.cineflix_api.security;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cybersoft.cineflix_api.entity.RoleUser;
import com.cybersoft.cineflix_api.entity.Users;
import com.cybersoft.cineflix_api.services.RoleUserServiceImp;
import com.cybersoft.cineflix_api.services.UsersServiceImp;


@Service
public class UserService implements UserDetailsService{
	
	@Autowired
	UsersServiceImp usersServiceImp; 
	
	@Autowired
	RoleUserServiceImp roleUserServiceImp;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		List<SimpleGrantedAuthority> roles = new ArrayList<SimpleGrantedAuthority>();
		
		
		Users users = usersServiceImp.findByUsername(username);
		//cách 1
//		List<Map<String, ?>> listRole = roleUserServiceImp.getAllRoleByUserName(username);
//		
//		for (Map<String, ?> map : listRole) {
//			SimpleGrantedAuthority role = new SimpleGrantedAuthority((String) map.get("name"));
//			roles.add(role);
//		}
		//cách 2
		List<RoleUser> listRole = roleUserServiceImp.getAllByUserId(users.getId());
		//System.out.println("số role: " + listRole.size());
		for (RoleUser roleUser : listRole) {
			System.out.println("Kiểm tra: " + roleUser.getRoles().getRoleName());
			SimpleGrantedAuthority role = new SimpleGrantedAuthority(roleUser.getRoles().getRoleName());
			roles.add(role);
		}
		User user = new User(users.getUsername(), users.getPassword(), roles);
		return user;
	}

}
