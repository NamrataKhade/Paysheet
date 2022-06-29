package com.nts.model.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PermissionDto {

	private String permissionId;
	@NotEmpty(message = "type is required")
	private String type;
	@NotEmpty
	private String name;
	@NotNull
	private boolean status = false;

}
