package com.userManagement.app.user.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.userManagement.app.enums.ErrorMessage;
import com.userManagement.app.enums.Status;
import com.userManagement.app.exception.ValidationException;
import com.userManagement.app.master.entity.Role;
import com.userManagement.app.master.repository.RoleDao;
import com.userManagement.app.user.dto.UserDto;
import com.userManagement.app.user.dto.UserHasRoleDto;
import com.userManagement.app.user.entity.User;
import com.userManagement.app.user.entity.UserHasRole;
import com.userManagement.app.user.repository.UserDao;
import com.userManagement.app.user.repository.UserRolDao;
import com.userManagement.app.user.service.UserService;

@Service
@Transactional()
public class UserServiceImpl implements UserService , UserDetailsService {

	@Autowired
	UserDao userDao;

	@Autowired
	UserRolDao userRolDao;

	@Autowired
	RoleDao roleDao;

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public UserDto save(UserDto userDto) {

		System.out.println(testMe());
		User user = new User();
		BeanUtils.copyProperties(userDto, user);
		user.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
		User savedUser = userDao.save(user);
		UserDto returnUserInfo = new UserDto();
		BeanUtils.copyProperties(savedUser, returnUserInfo);

		for (UserHasRoleDto userHasDto : userDto.getUserHasRoles()) {
			UserHasRole userRole = mangageUserRole(userHasDto, savedUser);
			UserHasRoleDto userRoleDto = new UserHasRoleDto();
			BeanUtils.copyProperties(userRole, userRoleDto);
			userRoleDto.setRole((int) userRole.getRole().getId());
			returnUserInfo.getUserHasRoles().add(userRoleDto);

		}

		return returnUserInfo;

	}

	@Override
	public UserDto update(UserDto userDto) throws Exception {

		if (userDto.getId() == 0) {
			throw new ValidationException(ErrorMessage.DTO_VALIDATION_EXCEPTION.getErrorMessage());
		}

		User user = new User();
		BeanUtils.copyProperties(userDto, user);

		User savedUser = userDao.save(user);

		UserDto returnUserInfo = new UserDto();
		BeanUtils.copyProperties(savedUser, returnUserInfo);

		for (UserHasRoleDto userHasDto : userDto.getUserHasRoles()) {
			mangageUserRole(userHasDto, savedUser);
		}

		return returnUserInfo;

	}

	@Override
	public UserDto delete(UserDto userDto) throws Exception {
		if (userDto.getId() == 0) {
			throw new ValidationException("No User Id Found ");
		}

		userDao.deleteById(userDto.getId());
		UserDto returnUserInfo = new UserDto();
		return returnUserInfo;

	}

	@Override
	public List<UserDto> findAllUsers() {

		Pageable pr = PageRequest.ofSize(100);
		Page<User> page = userDao.findAll(pr);
		List<User> users = page.getContent();
//		System.out.println(page.getTotalPages());

		List<UserDto> userDtos = new ArrayList<>();

		for (User user : users) {
			UserDto userInfo = new UserDto();
			BeanUtils.copyProperties(user, userInfo, "userHasRoles");

			userDtos.add(userInfo);
		}

		return userDtos;

	}

	public UserHasRole mangageUserRole(UserHasRoleDto userHasRoleDto, User user) {

		UserHasRole userHasRole;
		// find user role
		Role role = roleDao.findById(userHasRoleDto.getRole());
		if (role == null) {
			throw new ValidationException("Role Not Found !");
		}
		if (userHasRoleDto.isSave()) {

			// validate this user has this role all ready
			UserHasRole exsistUserHasRol = userRolDao.findByRoleIdAndStatusAndUser(userHasRoleDto.getRole(),
					Status.online, user);

			if (exsistUserHasRol != null) {
				throw new ValidationException("User Role Allready exsists");
			}

			UserHasRole saveObject = new UserHasRole();
			saveObject.setUser(user);
			saveObject.setRole(role);
			saveObject.setStatus(Status.online);
			userRolDao.save(saveObject);

			userHasRole = saveObject;
		} else {

			// find user has role
			UserHasRole exsitsUserHasRole = userRolDao.findById(userHasRoleDto.getId());
			if (exsitsUserHasRole == null) {
				throw new ValidationException("User has role information not found for update");
			}

			if (userHasRoleDto.isDelete()) {
				int i = userRolDao.updateById(userHasRoleDto.getId());
				userHasRole = exsitsUserHasRole;
			} else {
				// update
				exsitsUserHasRole.setRole(role);
				userHasRole = userRolDao.save(exsitsUserHasRole);
			}

		}

		return userHasRole;

	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = userDao.findByUserName(username);
		if(user==null) throw new UsernameNotFoundException(username);
		
		
		
		return new org.springframework.security.core.userdetails.User(user.getUserName(),user.getPassword(),new ArrayList<>());
	}
	
	public boolean testMe() {
		return true;
	}

}
