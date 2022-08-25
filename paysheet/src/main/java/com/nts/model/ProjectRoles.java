package com.nts.model;

import java.util.List;

import javax.validation.constraints.NotEmpty;

import com.nts.annotations.ValidStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectRoles {

	@NotEmpty(message = "role name must not be empty")
	private String role;

	@ValidStatus
	private String status;

	@NotEmpty(message = "users list must not be empty")
	private List<String> users;

}