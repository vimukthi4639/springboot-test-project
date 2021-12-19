package com.userManagement.app.page.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.userManagement.app.enums.Status;

@Entity()
@Table(name = "page")
public class Page {

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

	@OneToMany(mappedBy = "page", fetch = FetchType.LAZY)
	private Set<PageComponent> pageComponents = new HashSet<>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Set<PageComponent> getPageComponents() {
		return pageComponents;
	}

	public void setPageComponents(Set<PageComponent> pageComponents) {
		this.pageComponents = pageComponents;
	}

}
