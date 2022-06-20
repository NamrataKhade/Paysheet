package com.nts.model.entity;

import javax.validation.constraints.NotEmpty;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "tasks")
public class Tasks {

	@Id
	private String tasksId;

	@NotEmpty
	private String name;

	@NotEmpty
	private String status;

	@NotEmpty
	private String projectId;
}
