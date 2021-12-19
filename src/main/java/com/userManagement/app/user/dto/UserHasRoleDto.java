package com.userManagement.app.user.dto;

import com.userManagement.app.enums.Status;
import com.userManagement.app.support.dto.CrudDto;

public class UserHasRoleDto extends CrudDto {

	private int id;

	private UserDto user;

	private int role;

	private Status status;
	
	public UserHasRoleDto() {
	// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public UserDto getUser() {
		return user;
	}

	public void setUser(UserDto user) {
		this.user = user;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

}
