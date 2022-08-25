package com.nts.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.nts.model.dto.EmployeeDto;
import com.nts.model.response.PaginationResponse;

@Service
public interface EmployeeService {

	ResponseEntity<Object> createEmployee(EmployeeDto employeeDto, String email);

	EmployeeDto updateEmployee(EmployeeDto employeeDto, String empId);

	EmployeeDto getEmployeeById(String empId);

	PaginationResponse getAllEmployee(Integer pageNumber, Integer pageSize, String sortBy, String empId);

	void deleteEmployee(String empId);

}
