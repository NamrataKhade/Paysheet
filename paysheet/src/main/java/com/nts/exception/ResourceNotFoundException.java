package com.nts.exception;

import lombok.Getter;

import lombok.Setter;

@Getter
@Setter
public class ResourceNotFoundException extends RuntimeException {
	
	String perId;

	String type;

	String name;

	public ResourceNotFoundException(String perId2, String type, String name) {
		super(String.format("%s Found with %s:%s", type,name,perId2));
		this.perId = perId2;
		this.type = type;
		this.name = name;
	}


	
	 
//	 public ResourceNotFoundException(String perId,String type,String name) {
//		 
//		 super(String.format("%s %s Not Found with %s:%s", perId,type,name));
//		 this.name=name;
//		 this.perId=perId;
//		 this.type=type;
//	 }
	 

}
