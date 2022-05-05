package com.nts.exception;


import lombok.Data;

@Data
public class ResourceNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;	
	String tasksid;	
	String name;	
	String status;			
	public ResourceNotFoundException(String tasksid, String name, String status) {	
		super(String.format("%s not found with %s : %s",tasksid, name, status));	
		this.tasksid = tasksid;	
		this.name = name;		
		this.status = status;	
		}			
	}