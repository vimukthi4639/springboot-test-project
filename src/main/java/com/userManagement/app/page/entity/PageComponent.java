package com.userManagement.app.page.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.userManagement.app.enums.Status;

@Entity
@Table(name = "page_component")
public class PageComponent {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String name;

	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	private String uuid;

	@Column()
	@Enumerated(EnumType.STRING)
	private Status status;

	@ManyToOne(targetEntity = Page.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "page_id")
	private Page page;

	@ManyToOne(targetEntity = PageAction.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "page_action_id")
	private PageAction pageAction;

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

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public PageAction getPageAction() {
		return pageAction;
	}

	public void setPageAction(PageAction pageAction) {
		this.pageAction = pageAction;
	}

}
