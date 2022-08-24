package com.nts.controller;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
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

import com.nts.model.dto.EmployeeDto;
import com.nts.model.response.Response;
import com.nts.service.EmployeeService;
import com.nts.validatorgroups.OnCreate;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "/employee")
public class EmployeeController {

	private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

	@Autowired
	private EmployeeService employeeService;

//	createEmployee
	@PostMapping()
	@PreAuthorize("hasAnyAuthority('admin')")
	@RolesAllowed({ "admin" })
	@Secured({ "admin" })
	@ApiOperation(value = "Create Employee", nickname = "CreateEmployee")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully create schema"),
			@ApiResponse(code = 404, message = "Schema not found"),
			@ApiResponse(code = 400, message = "Missing or invalid request body"),
			@ApiResponse(code = 500, message = "Internal error") })
	@Validated(OnCreate.class)
	public ResponseEntity<Object> createEmployee(@Valid @RequestBody EmployeeDto employeeDto, String email) {
		logger.info("EmployeeController:createEmployee: Create Employee API");
		return new ResponseEntity<>(employeeService.createEmployee(employeeDto, email), HttpStatus.CREATED);
	}

//	updateEmployee
	@PutMapping("/{empId}")
	@PreAuthorize("hasAnyAuthority('admin')")
	@RolesAllowed({ "admin" })
	@Secured({ "admin" })
	@ApiOperation(value = "Update Employee", nickname = "UpdateEmployee")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully updated schema"),
			@ApiResponse(code = 404, message = "Schema not found"),
			@ApiResponse(code = 400, message = "Missing or invalid request body"),
			@ApiResponse(code = 500, message = "Internal error") })
	@Validated(OnCreate.class)
	public ResponseEntity<Object> updateEmployee(@Valid @RequestBody EmployeeDto employeeDto,
			@PathVariable String empId) {
		logger.info("EmployeeController:updateEmployee: Update Employee");
		return ResponseEntity.ok(employeeService.updateEmployee(employeeDto, empId));
	}

//	getAllEmployee/getSingleEmployee/pagination and sorting
	@GetMapping()
	@PreAuthorize("hasAnyAuthority('admin','user')")
	@ApiOperation(value = "Get Employee", nickname = "GetEmployee")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully Get schema"),
			@ApiResponse(code = 404, message = "Schema not found"),
			@ApiResponse(code = 400, message = "Missing or invalid request body"),
			@ApiResponse(code = 500, message = "Internal error") })
	@Validated(OnCreate.class)
	public ResponseEntity<Object> getAllEmployee(
			@RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize,
			@RequestParam(value = "sortBy", defaultValue = "empId", required = false) String sortBy,
			@RequestParam(value = "empId", required = false) String empId) {
		if (empId == null || empId.isBlank()) {
			logger.info("EmployeeController:getAllEmployee: getAllEmployee");
			return new ResponseEntity<Object>(employeeService.getAllEmployee(pageNumber, pageSize, sortBy, empId),
					HttpStatus.OK);
		} else {
			logger.info("EmployeeController:getSingleEmployee: getSingleEmployee");
			return ResponseEntity.ok(this.employeeService.getEmployeeById(empId));
		}

	}

//	Delete
	@DeleteMapping("/{empId}")
	@PreAuthorize("hasAnyAuthority('admin')")
	@RolesAllowed({ "admin" })
	@Secured({ "admin" })
	public ResponseEntity<Response> deleteEmployee(@PathVariable String empId) {
		employeeService.deleteEmployee(empId);
		return new ResponseEntity<Response>(new Response("Employee successfully deleted", true), HttpStatus.OK);
	}

}
