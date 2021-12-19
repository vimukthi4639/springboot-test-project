package com.userManagement.app.exception;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ExceptionResponse {

	private LocalDateTime currentTime;

	private String message;

	private List<ValidationMissingField> validationMissingField = new ArrayList<>();

	public ExceptionResponse(String message) {
		this.currentTime = LocalDateTime.now();
		this.message = message;
	}

	public ExceptionResponse(String message, List<ValidationMissingField> validationMissingField) {
		this.currentTime = LocalDateTime.now();
		this.message = message;
		this.validationMissingField = validationMissingField;
	}

	public LocalDateTime getCurrentTime() {
		return currentTime;
	}

	public void setCurrentTime(LocalDateTime currentTime) {
		this.currentTime = currentTime;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<ValidationMissingField> getValidationMissingField() {
		return validationMissingField;
	}

	public void setValidationMissingField(List<ValidationMissingField> validationMissingField) {
		this.validationMissingField = validationMissingField;
	}

}
