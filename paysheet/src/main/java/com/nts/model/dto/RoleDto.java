package com.nts.model.dto;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
public class RoleDto {

	@Id
	private String roleId;

	@NotNull
	@NotEmpty(message = "Please Provide a Role Name")
	private String roleName;

	@NotNull
	@NotEmpty(message = "Please Provide the Status")
	private String status;

	@NotEmpty(message = "permissions must not be empty")
	private List<String> permissions;
}
