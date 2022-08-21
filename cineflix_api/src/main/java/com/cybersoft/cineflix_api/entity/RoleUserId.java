package com.cybersoft.cineflix_api.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class RoleUserId implements Serializable{
	@Column(name = "role_id")
	private long roleId;
	
	@Column(name = "user_id")
	private long userId;
	
	public RoleUserId(long role_id, long user_id) {
		this.roleId = role_id;
		this.userId = user_id;
	}
	
	public RoleUserId() {
		
	}
	
}
