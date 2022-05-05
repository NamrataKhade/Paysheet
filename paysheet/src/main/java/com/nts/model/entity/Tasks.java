package com.nts.model.entity;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import lombok.Data;

@Data
@Document(collection = "tasks")
public class Tasks{

	@Id
	private String tasksId;
	
	private String name;

	private String status;
	
	private String projectId;



}
