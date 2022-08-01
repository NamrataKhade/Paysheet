package com.nts.exception;

import lombok.Data;

@Data
public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	String resourceName;

	String fieldName;

	String fieldValue;

	public ResourceNotFoundException(String resourceName1, String tyfieldNamepe1, String fieldValue1) {
		super(String.format("%s Found with %s:%s", resourceName1, tyfieldNamepe1, fieldValue1));
		this.resourceName = resourceName1;
		this.fieldName = tyfieldNamepe1;
		this.fieldValue = fieldValue1;
	}

}
