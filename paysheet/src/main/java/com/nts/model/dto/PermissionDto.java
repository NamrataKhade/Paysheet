package com.nts.model.dto;

import lombok.Data;

@Data
public class PermissionDto {

private String permissionId;
	
	private String type;
	
	private String name;
	
	private boolean status=false;

}
