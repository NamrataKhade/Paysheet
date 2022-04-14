package com.nts.model;

import java.util.Set;

import lombok.Data;

@Data
public class RolesAndPermisssions {

	
	private Set<String> roles;

	private Set<String> permissions;
}
