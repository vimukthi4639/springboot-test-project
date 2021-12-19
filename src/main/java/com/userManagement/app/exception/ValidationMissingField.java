package com.userManagement.app.exception;

import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

public class ValidationMissingField {

	private String message;

	private String missingField;

	private Object rejectedValue;
	
	public ValidationMissingField(FieldError fieldError) {
		
		this.message = fieldError.getDefaultMessage();
		this.missingField = fieldError.getField();
		this.rejectedValue = fieldError.getRejectedValue();
		
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMissingField() {
		return missingField;
	}

	public void setMissingField(String missingField) {
		this.missingField = missingField;
	}

	public Object getRejectedValue() {
		return rejectedValue;
	}

	public void setRejectedValue(Object rejectedValue) {
		this.rejectedValue = rejectedValue;
	}

}
