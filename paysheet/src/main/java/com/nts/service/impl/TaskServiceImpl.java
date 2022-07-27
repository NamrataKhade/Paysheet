package com.nts.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.nts.exception.ResourceNotFoundException;
import com.nts.model.dto.TaskDto;
import com.nts.model.entity.Task;
import com.nts.model.response.PaginationResponse;
import com.nts.repository.TaskRepository;
import com.nts.service.TaskService;

@Service
public class TaskServiceImpl implements TaskService {
	@Autowired
	private TaskRepository taskRepository;

	@Autowired
	private ModelMapper modelMapper;

	private static final Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return null;
	}

	// ************************CREATE*************************
	@Override
	public TaskDto createTask(TaskDto taskDto) {
		logger.debug("TasksServiceImpl | Create Task Invoked...");
		Task task = this.dtoToTask(taskDto);
		Task createTask = this.taskRepository.save(task);
		return this.taskToDto(createTask);
	}

	// **********************UPDATE********************************
	@Override
	public TaskDto updateTask(TaskDto taskDto, String taskId) {
		Task task = this.taskRepository.findById(taskId).orElse(null);
		task.setName(taskDto.getName());
		task.setStatus(taskDto.getStatus());
		task.setProjectId(taskDto.getProjectId());

		Task save = this.taskRepository.save(task);

		TaskDto taskToDto = this.taskToDto(save);
		return taskToDto;
	}

	// **********************GETTASKSBYID********************************
	@Override
	public TaskDto getTaskById(String taskId) {
		Task task = this.taskRepository.findById(taskId)
				.orElseThrow(() -> new ResourceNotFoundException("Task", "id", taskId));
		return this.taskToDto(task);
	}

	// **********************GETALLTASKS********************************

	@Override
	public PaginationResponse getAllTask(Integer pageNumber, Integer pageSize, String sortBy, String taskId) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize, org.springframework.data.domain.Sort.by(sortBy));
		Page<Task> tasks = this.taskRepository.findAll(pageable);

		List<Task> allTask = tasks.getContent();
		List<Object> taskDtos = allTask.stream().map(task -> this.modelMapper.map(task, TaskDto.class))
				.collect(Collectors.toList());
		PaginationResponse paginationResopnse = new PaginationResponse();
		paginationResopnse.setContent(taskDtos);
		paginationResopnse.setPageNumber(tasks.getNumber());
		paginationResopnse.setPageSize(tasks.getSize());
		paginationResopnse.setTotalElement(tasks.getTotalElements());
		paginationResopnse.setTotalPage(tasks.getTotalPages());
		paginationResopnse.setLastPage(tasks.isLast());

		return paginationResopnse;
	}

	// **********************DELETE********************************
	@Override
	public void deleteTask(String taskId) {
		Task task = this.taskRepository.findById(taskId)
				.orElseThrow(() -> new ResourceNotFoundException("Task", "id", taskId));
		this.taskRepository.delete(task);

	}

	public Task dtoToTask(TaskDto taskDto) {
		Task task = this.modelMapper.map(taskDto, Task.class);

		return task;
	}

	public TaskDto taskToDto(Task task) {
		TaskDto taskDto = this.modelMapper.map(task, TaskDto.class);

		return taskDto;
	}
}
