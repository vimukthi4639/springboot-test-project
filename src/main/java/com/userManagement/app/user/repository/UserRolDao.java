package com.userManagement.app.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.userManagement.app.enums.Status;
import com.userManagement.app.master.entity.Role;
import com.userManagement.app.user.entity.User;
import com.userManagement.app.user.entity.UserHasRole;

@Repository
public interface UserRolDao extends JpaRepository<UserHasRole, Integer> {

	public UserHasRole findById(int id);

	public UserHasRole findByRoleAndStatus(int roleId, Status status);

	public UserHasRole findByRoleIdAndStatusAndUser(long role, Status status,User userId);
	
	@Modifying()
	@Query("update UserHasRole u set u.status='offline' where id=:id ")
	public int updateById(@Param("id") int id);
	
}
