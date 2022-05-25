package com.nts.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nts.model.dto.ProjectsDto;
import com.nts.service.impl.ProjectsServiceImpl;
import com.nts.validatorgroups.OnCreate;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "/projects")
public class ProjectsController {

	private static final Logger logger = LoggerFactory.getLogger(ProjectsController.class);

	@Autowired
	private ProjectsServiceImpl projectsService;

	@PostMapping()
	@ApiOperation(value = "Create Projects", nickname = "CreateProjects")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully Updated Schema"),
			@ApiResponse(code = 404, message = "Schema not found"),
			@ApiResponse(code = 400, message = "Missing or invalid request body"),
			@ApiResponse(code = 500, message = "Internal error") })
	@Validated(OnCreate.class)
	public ResponseEntity<ProjectsDto> createProjects(@Valid @RequestBody ProjectsDto projectsDto) {
		logger.info("Projects Controller | Create Projects API ");

		return ResponseEntity.ok(projectsService.createProjects(projectsDto));
	}

	@GetMapping("/getAllProjects")
	@ApiOperation(value = "Get All Projects and Get Project By ID", nickname = "GetAllProjectsANDProjectByID")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully Get Data"),
			@ApiResponse(code = 404, message = "Schema not found"),
			@ApiResponse(code = 400, message = "Missing or invalid request body"),
			@ApiResponse(code = 500, message = "Internal error") })
	@Validated(OnCreate.class)
	public ResponseEntity<Object> getProjects(@Valid @RequestParam(name = "proId", required = false) String proId,
			@RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "5", required = false) Integer pageSize,
			@RequestParam(value = "sortBy", defaultValue = "projectId", required = false) String sortBy,
			@RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir) {
		logger.info("Projects Controller | Get  All Projects AND  Get projects by ID API ");
		if (null == proId || StringUtils.isEmpty(proId)) {
			return ResponseEntity.ok().body(projectsService.getAllProjects(pageNumber, pageSize, sortBy, sortDir));
		} else {
			return ResponseEntity.ok().body(projectsService.getProjectsById(proId));
		}
	}

	@PutMapping("/updateProjects")
	@ApiOperation(value = "Update Project By ID", nickname = "UpdateProject")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully Update Schema"),
			@ApiResponse(code = 404, message = "Schema not found"),
			@ApiResponse(code = 400, message = "Missing or invalid request body"),
			@ApiResponse(code = 500, message = "Internal error") })
	@Validated(OnCreate.class)
	public ResponseEntity<Object> updateProjects(@Valid @RequestParam(name = "proId", required = false) String proId,
			@RequestBody ProjectsDto projectsDto) {
		logger.info("Projects Controller | Update   Projects  API");

		return ResponseEntity.ok(projectsService.updateProjects(proId, projectsDto));

	}

	@DeleteMapping("/deleteProjectsById")
	@ApiOperation(value = "Delete By ID", nickname = "DeleteProject")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully Delete Data"),
			@ApiResponse(code = 404, message = "Schema not found"),
			@ApiResponse(code = 400, message = "Missing or invalid request body"),
			@ApiResponse(code = 500, message = "Internal error") })
	@Validated(OnCreate.class)
	public ResponseEntity<Object> deleteProjectsById(
			@Valid @RequestParam(name = "proId", required = false) String proId) {
		logger.info("Projects Controller | Delete   Projects  API");
		projectsService.deleteProjectsById(proId);
		return ResponseEntity.ok("Deleted Successfully");

	}

}
