package com.nts.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.nts.model.dto.TasksDto;
import com.nts.model.entity.Tasks;
import com.nts.model.response.TasksResponse;




public interface TasksService extends UserDetailsService {

	public TasksResponse createTasks(Tasks tasks);
	public TasksDto updateTasks(TasksDto tasks,String tasksId);
	public TasksDto getTaskById(String tasksId);
	List<TasksDto> getAllTasks();

	void deleteTasks(String tasksId);
	
	


}
