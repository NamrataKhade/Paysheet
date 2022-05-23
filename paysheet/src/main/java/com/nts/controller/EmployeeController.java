package com.nts.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nts.model.entity.Employee;
import com.nts.model.response.EmployeeResponse;

import com.nts.service.impl.EmployeeServiceImpl;
import com.nts.validatorgroups.OnCreate;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "/employee")
public class EmployeeController {

	private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);
	@Autowired
	private EmployeeServiceImpl employeeService;

	@PostMapping()
	@ApiOperation(value = "Create Employee", nickname = "CreateEmployee")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully updated schema"),
			@ApiResponse(code = 404, message = "Schema not found"),
			@ApiResponse(code = 400, message = "Missing or invalid request body"),
			@ApiResponse(code = 500, message = "Internal error") })
	@Validated(OnCreate.class)
	public EmployeeResponse createEmployee(@Valid @RequestBody Employee employee) {
		logger.info("Employee Controller | Create Employee API");
		return employeeService.createEmployee(employee);
	}

	@GetMapping("/findAllEmployees/{empId}")
	public EmployeeResponse getEmployee(@PathVariable String empId) {

		return employeeService.getAllEmployeeById(empId);
	}

	@GetMapping("/findAllEmployees")
	public List<Employee> getEmployees() {
		return employeeService.getAllEmployee();
	}

	@DeleteMapping("/delete/{empid}")
	public EmployeeResponse deleteEmployee(@PathVariable String empid) {

		System.out.println("Delete Employee" + empid);
		return employeeService.deleteEmployeeById(empid);

	}

	@PutMapping("/update/{empid}")
	public EmployeeResponse updateEmployee(@RequestBody Employee employee, @PathVariable String empid) {
		return employeeService.updateEmployee(empid, employee);
	}

}
