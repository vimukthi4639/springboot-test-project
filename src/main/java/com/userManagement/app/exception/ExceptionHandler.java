package com.userManagement.app.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.beanvalidation.SpringValidatorAdapter;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.userManagement.app.enums.ErrorMessage;

@ControllerAdvice
public class ExceptionHandler {

	@org.springframework.web.bind.annotation.ExceptionHandler(value = ValidationException.class)
	public ResponseEntity<ExceptionResponse> handleValidationException(ValidationException validationException,
			WebRequest webRequest) {
		return new ResponseEntity<ExceptionResponse>(new ExceptionResponse(validationException.getMessage()),
				new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@org.springframework.web.bind.annotation.ExceptionHandler(value = MethodArgumentNotValidException.class)
	public ResponseEntity<ExceptionResponse> handleValidationException(MethodArgumentNotValidException exception,
			WebRequest webRequest) {

		List<FieldError> objectError = exception.getFieldErrors();
		List<ValidationMissingField> validationMissingFields = new ArrayList<>();
		for (FieldError err : objectError) {
			
			ValidationMissingField validationMissingField = new ValidationMissingField(err);
			validationMissingFields.add(validationMissingField);
		}
		String message = ErrorMessage.DTO_VALIDATION_EXCEPTION.getErrorMessage();
		return new ResponseEntity<ExceptionResponse>(new ExceptionResponse(message,validationMissingFields), new HttpHeaders(),
				HttpStatus.BAD_REQUEST);
	}

}
