package com.defects_management.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotfoundException extends RuntimeException {
	private String resouceName;
	private String fieldName;
	private int fieldValue;

	public ResourceNotfoundException(String resourceName, int fieldValue, String fieldName) {
		super(String.format("%s not found with %s : '%s'", resourceName, fieldName, fieldValue));
		this.resouceName = resourceName;
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
	}

	public String getResouceName() {
		return this.resouceName;
	}

	public String getFieldName() {
		return this.fieldName;
	}

	public int getFieldValue() {
		return this.fieldValue;
	}
}
