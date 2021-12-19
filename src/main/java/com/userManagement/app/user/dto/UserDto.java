package com.userManagement.app.user.dto;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;

import com.userManagement.app.enums.Status;
import com.userManagement.app.user.entity.UserHasRole;

public class UserDto {

	private int id;

	@NotBlank
	private String fName;

	@NotBlank
	private String lName;

	@NotNull
	private LocalDate dob;

	private String fullName;

	@NotNull
	private String userName;

	@NotEmpty
	@Size(max = 8, min = 6)
	private String password;

	private UUID uuid;

	private Status status;

	private Set<UserHasRoleDto> userHasRoles = new HashSet<>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}

	public Set<UserHasRoleDto> getUserHasRoles() {
		return userHasRoles;
	}

	public void setUserHasRoles(Set<UserHasRoleDto> userHasRoles) {
		this.userHasRoles = userHasRoles;
	}

}
