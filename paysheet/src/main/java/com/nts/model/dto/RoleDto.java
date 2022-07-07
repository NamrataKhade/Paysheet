package com.nts.model.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class RoleDto {

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
