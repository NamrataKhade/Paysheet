package com.nts.service;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.nts.model.dto.TaskDto;
import com.nts.model.response.PaginationResponse;

@Service
public interface TaskService extends UserDetailsService {

	public ResponseEntity<Object> createTask(TaskDto taskDto);

	public ResponseEntity<Object> updateTask(TaskDto taskDto, String taskId);

	public TaskDto getTaskById(String taskId);

	PaginationResponse getAllTask(Integer pageNumber, Integer pageSize, String sortBy, String taskId);

	ResponseEntity<Object> deleteTask(String taskId);

}
