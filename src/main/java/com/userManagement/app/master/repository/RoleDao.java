package com.userManagement.app.master.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.userManagement.app.master.entity.Role;

@Repository
public interface RoleDao extends CrudRepository<Role, Long> {

	public Role findById(long id);
	
}
