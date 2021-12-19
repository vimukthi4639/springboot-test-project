package com.userManagement.app.test.user.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.userManagement.app.enums.Status;
import com.userManagement.app.user.dto.UserDto;
import com.userManagement.app.user.entity.User;
import com.userManagement.app.user.repository.UserDao;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class UserDaoTest {

	@Autowired
	UserDao userDao;

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void save() {

		User user = new User();
		user.setUserName("vimukthi");
		user.setPassword("123456789");
		user.setDob(LocalDate.now());
		user.setfName("vimukthi");
		user.setFullName("vimukthi kusal");
		user.setStatus(Status.online);

		User savedUser = userDao.save(user);

		assertNotNull(savedUser);
//		assertNotNull(savedUser.getId());
//		assertNotNull(savedUser.getUuid());

	}

}
