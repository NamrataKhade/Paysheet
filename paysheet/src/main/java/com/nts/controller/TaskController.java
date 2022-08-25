package com.nts.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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
import com.nts.validatorgroups.OnCreate;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "/task")
public class TaskController {

	private static final Logger logger = LoggerFactory.getLogger(TaskController.class);
	@Autowired
	private TaskServiceImpl taskService;

	// CREATE
	@PostMapping()
	@ApiOperation(value = "Create Task", nickname = "CreateTask")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully create schema"),
			@ApiResponse(code = 404, message = "Schema not found"),
			@ApiResponse(code = 400, message = "Missing or invalid request body"),
			@ApiResponse(code = 500, message = "Internal error") })

	@Validated(OnCreate.class)
	public ResponseEntity<Object> createTask(@Valid @RequestBody TaskDto taskDto) {
		logger.debug("Tasks Controller | Create Tasks API");
		return new ResponseEntity<Object>(taskService.createTask(taskDto), HttpStatus.CREATED);
	}

	// UPDATE
	@PutMapping("/{taskId}")
	@ApiOperation(value = "Create Task", nickname = "CreateTask")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully create schema"),
			@ApiResponse(code = 404, message = "Schema not found"),
			@ApiResponse(code = 400, message = "Missing or invalid request body"),
			@ApiResponse(code = 500, message = "Internal error") })

	@Validated(OnCreate.class)
	public ResponseEntity<Object> updateTask(@Valid @RequestBody TaskDto taskDto, @PathVariable String taskId) {
		logger.info("Tasks Controller | Update Tasks API");
		return new ResponseEntity<Object>(taskService.updateTask(taskDto, taskId), HttpStatus.OK);
	}

	// GETALLTASKS&SINGLETASK
	@GetMapping()
	@ApiOperation(value = "Get Task", nickname = "Gettask")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully create schema"),
			@ApiResponse(code = 404, message = "Schema not found"),
			@ApiResponse(code = 400, message = "Missing or invalid request body"),
			@ApiResponse(code = 500, message = "Internal error") })

	@Validated(OnCreate.class)
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
	@ApiOperation(value = "Delete  Task", nickname = "Deletetask")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully create schema"),
			@ApiResponse(code = 404, message = "Schema not found"),
			@ApiResponse(code = 400, message = "Missing or invalid request body"),
			@ApiResponse(code = 500, message = "Internal error") })

	@Validated(OnCreate.class)
	public Object deleteTask(@PathVariable String taskId) {
		logger.debug("TaskController | Delete Task API");
		return taskService.deleteTask(taskId);

	}
}
