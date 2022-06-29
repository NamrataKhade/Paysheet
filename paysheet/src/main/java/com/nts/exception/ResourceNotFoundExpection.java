package com.nts.exception;

import lombok.Data;

@Data
public class ResourceNotFoundExpection extends RuntimeException {

	String projectId;
	String name;
	String fieldValue;

	public ResourceNotFoundExpection(String projectId1, String name1, String fieldValue1) {
		super(String.format("%s  found with %s:%s", projectId1, name1, fieldValue1));
		this.projectId = projectId1;
		this.name = name1;
		this.fieldValue = fieldValue1;

	}
}
