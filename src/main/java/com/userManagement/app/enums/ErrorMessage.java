package com.userManagement.app.enums;

public enum ErrorMessage {

	DTO_VALIDATION_EXCEPTION("Missing Requeierd Field");

	private String errorMessage;
	
	ErrorMessage(String string) {
		this.errorMessage = string;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	
	
}
