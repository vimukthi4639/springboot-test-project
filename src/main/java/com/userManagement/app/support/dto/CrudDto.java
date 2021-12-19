package com.userManagement.app.support.dto;

public abstract class CrudDto {

	public boolean isSave;
	public boolean isDelete;
	public boolean isSave() {
		return isSave;
	}
	public void setSave(boolean isSave) {
		this.isSave = isSave;
	}
	public boolean isDelete() {
		return isDelete;
	}
	public void setDelete(boolean isDelete) {
		this.isDelete = isDelete;
	}

}
