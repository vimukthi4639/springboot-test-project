package com.userManagement.app.user.entity;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import com.userManagement.app.enums.Status;

@Entity
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column()
	private String fName;

	@Column()
	private String lName;

	@Column()
	private LocalDate dob;

	@Column()
	private String fullName;

	@Column()
	private String userName;

	@Column()
	private String password;

//	@GenericGenerator(name = "UUID2", strategy = "org.hibernate.id.UUIDGenerator")

	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name = "uuid")
	@Type(type = "uuid-char")
	private UUID uuid;

	@PrePersist
	protected void onCreate() {
		this.uuid = java.util.UUID.randomUUID();
	}

	@Column()
	@Enumerated(EnumType.STRING)
	@ColumnDefault("'online'")
	private Status status;

	@OneToMany(mappedBy = "user")
	private Set<UserHasRole> userHasRoles = new HashSet<>();

//	public int getId() {
//		return id;
//	}
//
//	public void setId(int id) {
//		this.id = id;
//	}

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

	public Set<UserHasRole> getUserHasRoles() {
		return userHasRoles;
	}

	public void setUserHasRoles(Set<UserHasRole> userHasRoles) {
		this.userHasRoles = userHasRoles;
	}

	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
