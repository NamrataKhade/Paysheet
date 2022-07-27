package com.nts.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.nts.model.dto.TaskDto;
import com.nts.model.response.PaginationResponse;

@Service
public interface TaskService extends UserDetailsService {

	public TaskDto createTask(TaskDto taskDto);

	public TaskDto updateTask(TaskDto taskDto, String taskId);

	public TaskDto getTaskById(String taskId);

	PaginationResponse getAllTask(Integer pageNumber, Integer pageSize, String sortBy, String taskId);

	void deleteTask(String taskId);

}
