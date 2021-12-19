package com.userManagement.app.page.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.userManagement.app.enums.Status;

@Entity()
@Table(name="page_action")
public class PageAction {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column()
	private String name;

	@Column()
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	private String uuid;

	@Column()
	@Enumerated(EnumType.STRING)
	private Status status;

}
