package com.userManagement.app.page.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity()
@Table(name = "role_has_page_component")
public class RoleHasPageComoponent {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne(targetEntity = Page.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "page_id")
	private Page page;

	@ManyToOne(targetEntity = PageComponent.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "page_component_id")
	private PageComponent pageComponent;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public PageComponent getPageComponent() {
		return pageComponent;
	}

	public void setPageComponent(PageComponent pageComponent) {
		this.pageComponent = pageComponent;
	}

}
