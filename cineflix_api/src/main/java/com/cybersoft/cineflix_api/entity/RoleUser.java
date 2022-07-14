package com.cybersoft.cineflix_api.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

@Entity(name = "role_user")
public class RoleUser {
	
	@EmbeddedId
	private RoleUserId id;
	
	@ManyToOne
	@MapsId("user_id")
	@JoinColumn(name = "user_id")
	private Users users;
	
	@ManyToOne
	@MapsId("role_id")
	@JoinColumn(name = "role_id")
	private Roles roles;
	

	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public Roles getRoles() {
		return roles;
	}

	public void setRoles(Roles roles) {
		this.roles = roles;
	}

	public RoleUserId getId() {
		return id;
	}

	public void setId(RoleUserId id) {
		this.id = id;
	}
	
	

	
}
