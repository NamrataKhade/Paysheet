package com.nts.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.commons.collections4.functors.FalsePredicate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.aggregation.SortByCountOperation;
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

import com.nts.model.dto.EmployeeDto;
import com.nts.model.response.ApiResponse;
import com.nts.model.response.PaginationResponse;
import com.nts.service.EmployeeService;

import io.swagger.models.auth.In;

@RestController
@RequestMapping(value = "/employee")
public class EmployeeController {

	private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

	@Autowired
	private EmployeeService employeeService;

//	@PostMapping()
//	@ApiOperation(value = "Create Employee", nickname = "CreateEmployee")
//	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully updated schema"),
//			@ApiResponse(code = 404, message = "Schema not found"),
//			@ApiResponse(code = 400, message = "Missing or invalid request body"),
//			@ApiResponse(code = 500, message = "Internal error") })
//	@Validated(OnCreate.class)
//	public EmployeeResponse createEmployee(@Valid @RequestBody Employee employee) {
//		logger.info("Employee Controller | Create Employee API");
//		return employeeService.createEmployee(employee);
//	}

//	Post
	@PostMapping()
	public ResponseEntity<Object> createEmployee(@Valid @RequestBody EmployeeDto employeeDto) {
		EmployeeDto createdEmployeeDto = this.employeeService.createEmployee(employeeDto);
		logger.info("EmployeeController:createEmployee: Create Employee API");
		return new ResponseEntity<>(createdEmployeeDto, HttpStatus.CREATED);
	}

//	Get
	@PutMapping("/{empId}")
	public ResponseEntity<Object> updateEmployee(@Valid @RequestBody EmployeeDto employeeDto,
			@PathVariable String empId) {
		logger.info("EmployeeController:updateEmployee: Update Employee");
		EmployeeDto updatedEmployee = this.employeeService.updateEmployee(employeeDto, empId);
		return ResponseEntity.ok(updatedEmployee);
	}

//	getAllEmployee/getSingleEmployee/pagination and sorting
	@GetMapping()
	public ResponseEntity<Object> getAllEmployee(
			@RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize,
			@RequestParam(value = "sortBy", defaultValue = "empId", required = false) String sortBy,
			@RequestParam(value = "empId", required = false) String empId) {
		if (empId == null || empId.isBlank()) {
			logger.info("EmployeeController:getAllEmployee: getAllEmployee");
			PaginationResponse paginationResponse = this.employeeService.getAllEmployee(pageNumber, pageSize, sortBy,
					empId);
			return new ResponseEntity<Object>(paginationResponse, HttpStatus.OK);
		} else {
			logger.info("EmployeeController:getSingleEmployee: getSingleEmployee");
			return ResponseEntity.ok(this.employeeService.getEmployeeById(empId));
		}

	}

//	getSingleEmployee
	 /**@GetMapping("/{empId}") public
	 ResponseEntity<EmployeeDto>getSingleEmployee(@PathVariable String empId) {
	 return ResponseEntity.ok(this.employeeService.getEmployeeById(empId)); }**/
	 

//	Delete
	@DeleteMapping("/{empId}")
	public ResponseEntity<ApiResponse> deleteEmployee(@PathVariable String empId) {
		this.employeeService.deleteEmployee(empId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Employee successfully deleted", true), HttpStatus.OK);
	}

}
