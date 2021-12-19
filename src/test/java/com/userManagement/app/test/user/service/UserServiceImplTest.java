package com.userManagement.app.test.user.service;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.*;

import org.aspectj.lang.annotation.Before;
import org.junit.Rule;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.MockitoRule;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.mockito.ArgumentMatchers;

import com.mysql.cj.x.protobuf.MysqlxDatatypes.Any;
import com.userManagement.app.enums.Status;
import com.userManagement.app.exception.ValidationException;
import com.userManagement.app.exception.ValidationMissingField;
import com.userManagement.app.master.entity.Role;
import com.userManagement.app.master.repository.RoleDao;
import com.userManagement.app.user.dto.UserDto;
import com.userManagement.app.user.dto.UserHasRoleDto;
import com.userManagement.app.user.entity.User;
import com.userManagement.app.user.entity.UserHasRole;
import com.userManagement.app.user.repository.UserDao;
import com.userManagement.app.user.repository.UserRolDao;
import com.userManagement.app.user.service.UserService;
import com.userManagement.app.user.service.impl.UserServiceImpl;

class UserServiceImplTest {

	@Mock
	UserDao userDao;
	
	@Mock
	UserRolDao userRoleDao;
	
	@Mock
	RoleDao roleDao;
	
	@Mock
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@InjectMocks
	UserServiceImpl userService;

	@BeforeEach
	void setUp() throws Exception {
//		userService. = userDao

		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testSave() {

		UserDto userDto = new UserDto();
		userDto.setUserName("vimukthi");
		userDto.setPassword("123456789");
		userDto.setDob(LocalDate.now());
		userDto.setfName("vimukthi");
		userDto.setFullName("vimukthi kusal");
		userDto.setStatus(Status.online);

		UserHasRoleDto roleDto = new UserHasRoleDto();
		roleDto.setSave(true);

		// mock user object
		User user = new User();
		BeanUtils.copyProperties(userDto, user);

		// mock user save method
		when(userDao.save(any(User.class))).thenReturn(user);
		UserServiceImpl us = Mockito.spy(userService);
		Mockito.doReturn(false).when(us).testMe();
//		when(userService.testMe()).thenReturn(false);
		UserDto saveUserInfo = us.save(userDto);

		assertNotNull(saveUserInfo);
		assertEquals(user.getUserName(), user.getUserName());

	}

	@Test
	void testUserSaveWithWrongRole() {

		UserDto userDto = new UserDto();
		userDto.setUserName("vimukthi");
		userDto.setPassword("123456789");
		userDto.setDob(LocalDate.now());
		userDto.setfName("vimukthi");
		userDto.setFullName("vimukthi kusal");
		userDto.setStatus(Status.online);
		
		UserHasRoleDto roleDto = new UserHasRoleDto();
		roleDto.setSave(true);
		
		userDto.getUserHasRoles().add(roleDto);
		

		// mock user object
		User user = new User();
		BeanUtils.copyProperties(userDto, user);

		// mock user save method
		when(userDao.save(any(User.class))).thenReturn(user);
		when(roleDao.findById(anyLong())).thenReturn(null);
		
		

		assertThrows(ValidationException.class, ()->{
			 userService.save(userDto);	
		});
	}
	
	@Test
	void testUserSaveWithExsistsUserRole() {

		UserDto userDto = new UserDto();
		userDto.setUserName("vimukthi");
		userDto.setPassword("123456789");
		userDto.setDob(LocalDate.now());
		userDto.setfName("vimukthi");
		userDto.setFullName("vimukthi kusal");
		userDto.setStatus(Status.online);
		
		UserHasRoleDto roleDto = new UserHasRoleDto();
		roleDto.setSave(true);
		
		userDto.getUserHasRoles().add(roleDto);
		

		// mock user object
		User user = new User();
		BeanUtils.copyProperties(userDto, user);

		Role role = new Role();
		role.setId(1);
		
		// mock user save method
		when(userDao.save(any(User.class))).thenReturn(user);
		when(roleDao.findById(anyLong())).thenReturn(role);
		when(userRoleDao.findByRoleIdAndStatusAndUser(anyLong(), any(Status.class), any(User.class))).thenReturn(new UserHasRole());
		
		

		assertThrows(ValidationException.class, ()->{
			 userService.save(userDto);	
		});
	}
	
	@Test
	void testUserSaveWithUserRole() {

		UserDto userDto = new UserDto();
		userDto.setUserName("vimukthi");
		userDto.setPassword("123456789");
		userDto.setDob(LocalDate.now());
		userDto.setfName("vimukthi");
		userDto.setFullName("vimukthi kusal");
		userDto.setStatus(Status.online);
		
		UserHasRoleDto roleDto = new UserHasRoleDto();
		roleDto.setSave(true);
		
		userDto.getUserHasRoles().add(roleDto);
		

		// mock user object
		User user = new User();
		BeanUtils.copyProperties(userDto, user);

		Role role = new Role();
		role.setId(1);
		
		UserHasRole userHasRole = new UserHasRole();
		userHasRole.setId(1);
		userHasRole.setRole(role);
		userHasRole.setUser(user);
		
		// mock user save method
		when(userDao.save(any(User.class))).thenReturn(user);
		when(roleDao.findById(anyLong())).thenReturn(role);
		when(userRoleDao.findByRoleIdAndStatusAndUser(anyLong(), any(Status.class), any(User.class))).thenReturn(null);
		when(userRoleDao.save(any(UserHasRole.class))).thenReturn(userHasRole);
		

		 UserDto savedUser =  userService.save(userDto);
		 
		 savedUser.getUserHasRoles().forEach(userRole->{
			 assertNotNull(userRole);
			 assertEquals(userRole.getRole(), role.getId());
		 });
		 
		 
		 
	}

	@Test
	void testUpdateWithWronNotProvidingId() {
		UserDto userDto = new UserDto();
		userDto.setUserName("vimukthi");
		userDto.setPassword("123456789");
		userDto.setDob(LocalDate.now());
		userDto.setfName("vimukthi");
		userDto.setFullName("vimukthi kusal");
		userDto.setStatus(Status.online);

		assertThrows(ValidationException.class, () -> {
			userService.update(userDto);
		});

	}

	@Test
	void testUpdate() {

		UserDto userDto = new UserDto();
		userDto.setUserName("vimukthi");
		userDto.setPassword("123456789");
		userDto.setDob(LocalDate.now());
		userDto.setfName("vimukthi");
		userDto.setFullName("vimukthi kusal");
		userDto.setStatus(Status.online);
		userDto.setId(1);

		// mock user object
		User user = new User();
		BeanUtils.copyProperties(userDto, user);

		// mock user save method
		when(userDao.save(any(User.class))).thenReturn(user);

		UserDto saveUserInfo = userService.save(userDto);

		assertNotNull(saveUserInfo);
		assertEquals(user.getUserName(), user.getUserName());

	}
	
	@Test
	void testUserUpdateWithNoUserRole() throws Exception {

		UserDto userDto = new UserDto();
		userDto.setUserName("vimukthi");
		userDto.setPassword("123456789");
		userDto.setDob(LocalDate.now());
		userDto.setfName("vimukthi");
		userDto.setFullName("vimukthi kusal");
		userDto.setStatus(Status.online);
		userDto.setId(1);
		
		UserHasRoleDto roleDto = new UserHasRoleDto();
		roleDto.setSave(false);
		
		userDto.getUserHasRoles().add(roleDto);
		

		// mock user object
		User user = new User();
		BeanUtils.copyProperties(userDto, user);

		Role role = new Role();
		role.setId(1);
		
		UserHasRole userHasRole = new UserHasRole();
		userHasRole.setId(1);
		userHasRole.setRole(role);
		userHasRole.setUser(user);
		
		
		
		// mock user save method
		when(userDao.save(any(User.class))).thenReturn(user);
		when(roleDao.findById(anyLong())).thenReturn(role);
		when(userRoleDao.findByRoleIdAndStatusAndUser(anyLong(), any(Status.class), any(User.class))).thenReturn(null);
		when(userRoleDao.save(any(UserHasRole.class))).thenReturn(userHasRole);
		when(userRoleDao.findByRoleIdAndStatusAndUser(anyLong(), any(Status.class), any(User.class))).thenReturn(userHasRole);

//		 UserDto savedUser =  userService.update(userDto);
//		 
//		 savedUser.getUserHasRoles().forEach(userRole->{
//			 assertNotNull(userRole);
//			 assertEquals(userRole.getRole(), role.getId());
//		 });
		 
		ValidationException validationException = assertThrows(ValidationException.class,()->{
			 userService.update(userDto);
		});
		
		assertEquals(validationException.getMessage(), "User has role information not found for update");
		
		 
		 
	}
	
	@Test
	void testUserUpdateWithUserRole() throws Exception {

		UserDto userDto = new UserDto();
		userDto.setUserName("vimukthi");
		userDto.setPassword("123456789");
		userDto.setDob(LocalDate.now());
		userDto.setfName("vimukthi");
		userDto.setFullName("vimukthi kusal");
		userDto.setStatus(Status.online);
		userDto.setId(1);
		
		UserHasRoleDto roleDto = new UserHasRoleDto();
		roleDto.setSave(false);
		
		userDto.getUserHasRoles().add(roleDto);
		

		// mock user object
		User user = new User();
		BeanUtils.copyProperties(userDto, user);

		Role role = new Role();
		role.setId(1);
		
		UserHasRole userHasRole = new UserHasRole();
		userHasRole.setId(1);
		userHasRole.setRole(role);
		userHasRole.setUser(user);
		
		
		
		// mock user save method
		when(userDao.save(any(User.class))).thenReturn(user);
		when(roleDao.findById(anyLong())).thenReturn(role);
		when(userRoleDao.findByRoleIdAndStatusAndUser(anyLong(), any(Status.class), any(User.class))).thenReturn(null);
		when(userRoleDao.save(any(UserHasRole.class))).thenReturn(userHasRole);
		when(userRoleDao.findByRoleIdAndStatusAndUser(anyLong(), any(Status.class), any(User.class))).thenReturn(userHasRole);
		when(userRoleDao.findById(anyInt())).thenReturn(userHasRole);
		 UserDto savedUser =  userService.update(userDto);
		 
		 savedUser.getUserHasRoles().forEach(userRole->{
			 assertNotNull(userRole);
			 assertEquals(userRole.getRole(), role.getId());
		 });
		 
		
		
		 
		 
	}

}
