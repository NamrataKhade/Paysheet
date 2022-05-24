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

import com.nts.model.dto.TasksDto;
import com.nts.model.response.PaginationResponse;
import com.nts.service.impl.TasksServiceImpl;

@RestController
@RequestMapping(value = "/tasks")
public class TasksController {

	private static final Logger logger = LoggerFactory.getLogger(TasksController.class);
	@Autowired
	private TasksServiceImpl tasksService;

	// CREATE
	@PostMapping()
	public ResponseEntity<Object> createEmployee(@Valid @RequestBody TasksDto tasksDto) {
		logger.info("Tasks Controller | Create Tasks API");
		TasksDto createTask = this.tasksService.createTasks(tasksDto);
		return new ResponseEntity<>(createTask, HttpStatus.CREATED);

		// return tasksService.createTasks(tasks);
	}

	// UPDATE
	@PutMapping("/{tasksId}")
	public ResponseEntity<TasksDto> updateTasks(@Valid @RequestBody TasksDto tasks, @PathVariable String tasksId) {
		logger.info("Tasks Controller | Update Tasks API");
		TasksDto updatedTasks = this.tasksService.updateTasks(tasks, tasksId);
		return ResponseEntity.ok(updatedTasks);
	}

	// GETALLTASKS&SINGLETASK
	@GetMapping()
	public ResponseEntity<Object> getAllTasks(@RequestParam(name = "tasksId", required = false) String tasksId,
			@RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "5", required = false) Integer pageSize,
			@RequestParam(value = "sortBy", defaultValue = "tasksId", required = false) String sortBy) {

		if (tasksId == null || tasksId.isBlank()) {
			logger.info("Tasks Controller | Get All Task");
			PaginationResponse paginationResponse = this.tasksService.getAllTasks(pageNumber, pageSize, sortBy,
					tasksId);
			return new ResponseEntity<Object>(paginationResponse, HttpStatus.OK);
		} else {
			logger.info("Tasks Controller | Get Single Task");
			return ResponseEntity.ok(this.tasksService.getTaskById(tasksId));
		}
	}

	// DELETE
	@DeleteMapping("/{tasksId}")
	public ResponseEntity<com.nts.model.response.ApiResponse> deleteTasks(@PathVariable String tasksId) {
		this.tasksService.deleteTasks(tasksId);
		return new ResponseEntity<com.nts.model.response.ApiResponse>(
				new com.nts.model.response.ApiResponse("Employee successfully deleted", true), HttpStatus.OK);
	}

}
