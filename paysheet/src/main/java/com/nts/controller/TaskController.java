package com.nts.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nts.model.dto.TaskDto;
import com.nts.model.response.PaginationResponse;
import com.nts.service.impl.TaskServiceImpl;

@RestController
@RequestMapping(value = "/task")
public class TaskController {

	private static final Logger logger = LoggerFactory.getLogger(TaskController.class);
	@Autowired
	private TaskServiceImpl taskService;

	// CREATE
	@PostMapping()
	public ResponseEntity<Object> createEmployee(@Valid @RequestBody TaskDto taskDto) {
		logger.info("Tasks Controller | Create Tasks API");
		TaskDto createTask = this.taskService.createTask(taskDto);
		return new ResponseEntity<>(createTask, HttpStatus.CREATED);

		// return tasksService.createTasks(tasks);
	}

	// UPDATE
	@PutMapping("/{taskId}")
	public ResponseEntity<TaskDto> updateTask(@Valid @RequestBody TaskDto task, @PathVariable String taskId) {
		logger.info("Tasks Controller | Update Tasks API");
		TaskDto updatedTask = this.taskService.updateTask(task, taskId);
		return ResponseEntity.ok(updatedTask);
	}

	// GETALLTASKS&SINGLETASK
	@GetMapping()
	public ResponseEntity<Object> getAllTask(@RequestParam(name = "taskId", required = false) String taskId,
			@RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "1000", required = false) Integer pageSize,
			@RequestParam(value = "sortBy", defaultValue = "tasksId", required = false) String sortBy) {

		if (taskId == null || taskId.isBlank()) {
			logger.info("Tasks Controller | Get All Task");
			PaginationResponse paginationResponse = this.taskService.getAllTask(pageNumber, pageSize, sortBy, taskId);
			return new ResponseEntity<Object>(paginationResponse, HttpStatus.OK);
		} else {
			logger.info("Tasks Controller | Get Single Task");
			return ResponseEntity.ok(this.taskService.getTaskById(taskId));
		}
	}

	// DELETE
	@DeleteMapping("/{taskId}")
	public ResponseEntity<com.nts.model.response.ApiResponse> deleteTasks(@PathVariable String taskId) {
		this.taskService.deleteTask(taskId);
		return new ResponseEntity<com.nts.model.response.ApiResponse>(
				new com.nts.model.response.ApiResponse("Employee successfully deleted", true), HttpStatus.OK);
	}

}
