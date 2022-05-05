package com.nts.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.nts.exception.ResourceNotFoundException;
import com.nts.model.dto.TasksDto;
import com.nts.model.entity.Tasks;
import com.nts.model.response.TasksResponse;
import com.nts.repository.TasksRepository;
import com.nts.service.TasksService;

@Service
public class TasksServiceImpl implements TasksService {
	@Autowired
	TasksRepository tasksRepository;

	private static final Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return null;
	}
	// ************************CREATE*************************
	@Override
	public TasksResponse createTasks(Tasks tasks) {
		logger.debug("TasksServiceImpl | Create Tasks Invoked...");
		TasksResponse tasksResponse = new TasksResponse();
		tasks.setName(tasks.getName());
		tasksRepository.save(tasks);
		TasksDto tasksDto = new TasksDto();
		tasksDto.setTasksId(tasks.getTasksId());
		tasksDto.setName(tasks.getName());
		tasksDto.setProjectId(tasks.getProjectId());
		tasksDto.setStatus(tasks.getStatus());
		tasksResponse.setTasksDto(tasksDto);
		return tasksResponse;
	}
	// **********************UPDATE********************************
	@Override
	public TasksDto updateTasks(TasksDto tasksDto, String tasksId) {
		Tasks tasks = this.tasksRepository.findById(tasksId).orElse(null);
		tasks.setName(tasksDto.getName());
		tasks.setStatus(tasksDto.getStatus());
		Tasks save = this.tasksRepository.save(tasks);

		TasksDto tasksToDto = this.tasksToDto(save);
		return tasksToDto;
	}
	// **********************GETTASKSBYID********************************
	@Override
	public TasksDto getTaskById(String tasksId) {
		Tasks tasks = this.tasksRepository.findById(tasksId)
				.orElseThrow(() -> new ResourceNotFoundException("Tasks", "id", tasksId));
		return this.tasksToDto(tasks);
	}
	// **********************GETALLTASKS********************************

	@Override
	public List<TasksDto> getAllTasks() {
		List<Tasks> taskss = this.tasksRepository.findAll();
		List<TasksDto> tasksDtos = taskss.stream().map(tasks -> this.tasksToDto(tasks)).collect(Collectors.toList());
		return tasksDtos;
	}

	// **********************DELETE********************************
	@Override
	public void deleteTasks(String tasksId) {
		Tasks tasks = this.tasksRepository.findById(tasksId)
				.orElseThrow(() -> new ResourceNotFoundException("Tasks", "id", tasksId));
		this.tasksRepository.delete(tasks);

	}

	public Tasks dtoToTasks(TasksDto tasksDto) {
		Tasks tasks = new Tasks();
		tasks.setTasksId(tasksDto.getTasksId());
		tasks.setProjectId(tasksDto.getProjectId());
		tasks.setStatus(tasksDto.getStatus());
		tasks.setName(tasksDto.getName());
		return tasks;
	}

	public TasksDto tasksToDto(Tasks tasks) {
		TasksDto tasksDto = new TasksDto();
		tasksDto.setTasksId(tasks.getTasksId());
		tasksDto.setName(tasks.getName());
		tasksDto.setProjectId(tasks.getProjectId());
		tasksDto.setStatus(tasks.getStatus());
		return tasksDto;
	}
}
