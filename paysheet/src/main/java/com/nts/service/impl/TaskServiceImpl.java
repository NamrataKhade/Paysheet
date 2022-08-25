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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.nts.exception.ResourceNotFoundException;
import com.nts.model.dto.TaskDto;
import com.nts.model.entity.Task;
import com.nts.model.response.PaginationResponse;
import com.nts.model.response.Response;
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

	@Override
	public ResponseEntity<Object> createTask(TaskDto taskDto) {
		logger.debug("TasksServiceImpl | Create Task Invoked...");
		try {
			if (taskDto.getTaskId() != null) {
				logger.info("TaskServiceImpl | Task Is already there");
				throw new ResourceNotFoundException("Task is already", "id", taskDto.getTaskId());
			}
			Task task = this.modelMapper.map(taskDto, Task.class);
			Task createTask = this.taskRepository.save(task);
			this.modelMapper.map(createTask, taskDto);
			return new ResponseEntity<Object>(new Response("Create Task", true), HttpStatus.CREATED);
		} catch (Exception e) {
			logger.error("ClentServiceImpl | Client Is already there");
			throw new ResourceNotFoundException("Client is already", "id", taskDto.getTaskId());
		}
	}

	@Override
	public ResponseEntity<Object> updateTask(TaskDto taskDto, String taskId) {
		try {
			logger.info("TaskServiceImpl | Update Task");
			Task task = taskRepository.findById(taskId)
					.orElseThrow(() -> new ResourceNotFoundException("Task is Not", "ClientId", taskId));
			task.setName(taskDto.getName());
			task.setStatus(taskDto.getStatus());
			task.setProjectId(taskDto.getProjectId());
			Task save = this.taskRepository.save(task);
			modelMapper.map(save, TaskDto.class);
			return new ResponseEntity<Object>(new Response("Update Task Success", true), HttpStatus.OK);
		} catch (Exception e) {
			logger.error("TaskServiceImpl | Something wrong in update Task");
			return new ResponseEntity<Object>(new Response("Somting Wrong", true), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public TaskDto getTaskById(String taskId) {
		Task task = this.taskRepository.findById(taskId)
				.orElseThrow(() -> new ResourceNotFoundException("Task", "id", taskId));
		return this.taskToDto(task);
	}

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

	@Override
	public ResponseEntity<Object> deleteTask(String taskId) {
		try {
			logger.info("TaskServiceImpl | Delete Task of specific taskId");
			taskRepository.findById(taskId).orElseThrow();
			this.taskRepository.deleteById(taskId);
		} catch (Exception e) {
			logger.error("TaskServiceImpl | Somting wrong in deleting Task");
			this.taskRepository.findById(taskId)
					.orElseThrow(() -> new ResourceNotFoundException("Task is Not", "id", taskId));
		}
		return new ResponseEntity<Object>(new Response("Deleted Sucessfully", true), HttpStatus.OK);

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
