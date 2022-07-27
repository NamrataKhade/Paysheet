package com.nts.model.entity;

import javax.validation.constraints.NotEmpty;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "task")
public class Task {

	@Id
	private String taskId;

	@NotEmpty
	private String name;

	@NotEmpty
	private String status;

	@NotEmpty
	private String projectId;
}
