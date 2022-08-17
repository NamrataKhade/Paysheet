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
import org.springframework.stereotype.Service;

import com.nts.exception.ResourceNotFoundException;
import com.nts.model.dto.TasksDto;
import com.nts.model.entity.Tasks;
import com.nts.model.response.PaginationResponse;
import com.nts.repository.TasksRepository;
import com.nts.service.TasksService;

@Service
public class TasksServiceImpl implements TasksService {
	@Autowired
	private TasksRepository tasksRepository;

	@Autowired
	private ModelMapper modelMapper;

	private static final Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);

	@Override
	public TasksDto createTasks(TasksDto tasksDto) {
		logger.debug("TasksServiceImpl | Create Tasks Invoked...");
		Tasks tasks = this.dtoToTask(tasksDto);
		Tasks createTask = this.tasksRepository.save(tasks);
		return this.tasksToDto(createTask);
	}

	@Override
	public TasksDto updateTasks(TasksDto tasksDto, String tasksId) {
		Tasks tasks = this.tasksRepository.findById(tasksId).orElse(null);
		tasks.setName(tasksDto.getName());
		tasks.setStatus(tasksDto.getStatus());
		tasks.setProjectId(tasksDto.getProjectId());

		Tasks save = this.tasksRepository.save(tasks);

		TasksDto tasksToDto = this.tasksToDto(save);
		return tasksToDto;
	}

	@Override
	public TasksDto getTaskById(String tasksId) {
		Tasks tasks = this.tasksRepository.findById(tasksId)
				.orElseThrow(() -> new ResourceNotFoundException("Tasks", "id", tasksId));
		return this.tasksToDto(tasks);
	}

	@Override
	public PaginationResponse getAllTasks(Integer pageNumber, Integer pageSize, String sortBy, String tasksId) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize, org.springframework.data.domain.Sort.by(sortBy));
		Page<Tasks> taskss = this.tasksRepository.findAll(pageable);

		List<Tasks> allTasks = taskss.getContent();
		List<Object> tasksDtos = allTasks.stream().map(tasks -> this.modelMapper.map(tasks, TasksDto.class))
				.collect(Collectors.toList());
		PaginationResponse paginationResopnse = new PaginationResponse();
		paginationResopnse.setContent(tasksDtos);
		paginationResopnse.setPageNumber(taskss.getNumber());
		paginationResopnse.setPageSize(taskss.getSize());
		paginationResopnse.setTotalElement(taskss.getTotalElements());
		paginationResopnse.setTotalPage(taskss.getTotalPages());
		paginationResopnse.setLastPage(taskss.isLast());

		return paginationResopnse;
	}

	@Override
	public void deleteTasks(String tasksId) {
		Tasks tasks = this.tasksRepository.findById(tasksId)
				.orElseThrow(() -> new ResourceNotFoundException("Tasks", "id", tasksId));
		this.tasksRepository.delete(tasks);

	}

	public Tasks dtoToTask(TasksDto tasksDto) {
		Tasks tasks = this.modelMapper.map(tasksDto, Tasks.class);

		return tasks;
	}

	public TasksDto tasksToDto(Tasks tasks) {
		TasksDto tasksDto = this.modelMapper.map(tasks, TasksDto.class);

		return tasksDto;
	}
}
