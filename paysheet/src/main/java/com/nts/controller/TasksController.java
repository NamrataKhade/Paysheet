package com.nts.controller;

import java.util.List;
import com.nts.model.response.*;


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
import org.springframework.web.bind.annotation.RestController;

import com.nts.model.dto.TasksDto;
import com.nts.model.entity.Tasks;
import com.nts.model.response.TasksResponse;
import com.nts.service.impl.TasksServiceImpl;
import com.nts.validatorgroups.OnCreate;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;

@RestController
@RequestMapping(value = "/tasks")
public class TasksController {

	private static final Logger logger = LoggerFactory.getLogger(TasksController.class);
	@Autowired
	private TasksServiceImpl tasksService;

	/**
	 * @PostMapping() @ApiOperation(value = "Create Tasks", nickname =
	 *                "CreateTasks")
	 * @ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully
	 *                     updated schema"),
	 * @ApiResponse(code = 404, message = "Schema not found"),
	 * @ApiResponse(code = 400, message = "Missing or invalid request body"),
	 * @ApiResponse(code = 500, message = "Internal error")
	 *                   }) @Validated(OnCreate.class)
	 **/

	// **********************CREATE********************************
	@PostMapping()
	public TasksResponse createTasks(@Valid @RequestBody Tasks tasks) {
		logger.info("Tasks Controller | Create Tasks API");
		return tasksService.createTasks(tasks);
	}

	// **********************UPDATE********************************
	@PutMapping("/{tasksId}")
	public ResponseEntity<TasksDto> updateTasks(@RequestBody TasksDto tasks, @PathVariable String tasksId) {
		TasksDto updatedTasks = this.tasksService.updateTasks(tasks, tasksId);
		return ResponseEntity.ok(updatedTasks);
	}
	// **********************GETALLTASKS********************************

	@GetMapping()
	public ResponseEntity<List<TasksDto>> getAllTasks() {
		return ResponseEntity.ok(this.tasksService.getAllTasks());

	}

	// **********************GETTASKSBYID********************************
	// getSingleEmployee
	@GetMapping("/{tasksId}")
	public ResponseEntity<TasksDto> getTaskById(@PathVariable String tasksId) {
		return ResponseEntity.ok(this.tasksService.getTaskById(tasksId));
	}

	// **********************DELETE********************************
	@DeleteMapping("/{tasksId}")
	public ResponseEntity<com.nts.model.response.ApiResponse> deleteTasks(@PathVariable String tasksId) {
		this.tasksService.deleteTasks(tasksId);
		return new ResponseEntity<com.nts.model.response.ApiResponse>(new com.nts.model.response.ApiResponse("Employee successfully deleted", true), HttpStatus.OK);
	}

}
