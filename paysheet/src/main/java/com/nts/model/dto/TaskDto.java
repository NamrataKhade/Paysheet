package com.nts.model.dto;

import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class TaskDto {
	@Id
	private String taskId;

	@NotEmpty
	@Size(min = 5, max = 30, message = "Task Name should be min 4 and max 30!!")
	private String name;

	@NotEmpty
	private String status;

	@NotEmpty
	private String projectId;

}
