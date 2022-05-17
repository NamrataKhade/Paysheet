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

	String perId;

	String type;

	String name;

	public ResourceNotFoundException(String type, String name, String perId2) {
		super(String.format("%s Found with %s:%s", type, name, perId2));
		this.perId = perId2;
		this.type = type;
		this.name = name;
	}

}
