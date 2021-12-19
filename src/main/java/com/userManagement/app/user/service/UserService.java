package com.userManagement.app.user.service;

import java.util.List;

import com.userManagement.app.user.dto.UserDto;
import com.userManagement.app.user.dto.UserHasRoleDto;
import com.userManagement.app.user.entity.User;

public interface UserService  {

	public UserDto save(UserDto userDto);

	public UserDto update(UserDto userDto) throws Exception;

	public UserDto delete(UserDto userDto) throws Exception;

	public List<UserDto> findAllUsers();
	
}
