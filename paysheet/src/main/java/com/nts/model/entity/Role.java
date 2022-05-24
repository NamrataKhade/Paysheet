package com.nts.model.entity;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "role")
public class Role {

	@Id
	private String roleId;

	@NotNull
	@NotEmpty(message = "Please Provide a Role Name")
	private String roleName;

	@NotNull
	@NotEmpty(message = "Please Provide the Status")
	private String status;

	@NotNull
	@NotEmpty(message = "Please Provide the Permission")
	private String permission;

}
