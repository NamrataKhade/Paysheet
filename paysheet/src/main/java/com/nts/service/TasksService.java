package com.nts.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.nts.model.dto.TasksDto;
import com.nts.model.response.PaginationResponse;

@Service
public interface TasksService extends UserDetailsService {

	public TasksDto createTasks(TasksDto tasksDto);

	public TasksDto updateTasks(TasksDto tasksDto, String tasksId);

	public TasksDto getTaskById(String tasksId);

	PaginationResponse getAllTasks(Integer pageNumber, Integer pageSize, String sortBy, String tasksId);

	void deleteTasks(String tasksId);

}
