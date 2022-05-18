package com.nts.model.dto;




import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class TasksDto {
	
	private String tasksId;
	
	@NotEmpty
	@Size(min=5,max=30,message = "Tasks Name shouild be min 4 and max 30!!")
	private String name;

	@NotEmpty 
	private String status;
	
	private String projectId;

	
	

}
