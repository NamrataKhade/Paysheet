package com.nts.exception;

import lombok.Getter;

import lombok.Setter;

@Getter
@Setter
public class ResourceNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	String permissionId;

	String type;

	String name;

	public ResourceNotFoundException(String type, String name, String permissionId2) {
		super(String.format("%s Found with %s:%s", type, name, permissionId2));
		this.permissionId = permissionId2;
		this.type = type;
		this.name = name;
	}

}
