package com.userManagement.app.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.userManagement.app.user.entity.User;

@Repository
public interface UserDao extends JpaRepository<User, Integer> {
	
	public User findByUserName(String userName);
	
}
