package com.userManagement.app.user.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.userManagement.app.SpringApplicationContext;
import com.userManagement.app.security.AppPropertise;
import com.userManagement.app.user.dto.UserDto;
import com.userManagement.app.user.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService userService;

	@PostMapping("/saveUser")
	public UserDto saveUser(@Valid @RequestBody UserDto userDto) {
		UserDto returnUser = userService.save(userDto);

		return returnUser;
	}

	@PostMapping("/updateUser")
	public UserDto updateUser(@Valid @RequestBody UserDto userDto) throws Exception {
		UserDto returnUser = userService.update(userDto);

		return returnUser;
	}

	@GetMapping("/getUsers")
	public List<UserDto> getUsers() throws Exception {

//		 System.out.println(SecurityContextHolder.getContext().getAuthentication() );
		System.out.println(((AppPropertise)SpringApplicationContext.getBean("appPro")).getTokenService());
		
		List<UserDto> userList = userService.findAllUsers();

		return userList;
	}

}
