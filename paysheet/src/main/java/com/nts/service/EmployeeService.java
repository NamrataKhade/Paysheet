package com.nts.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.nts.model.dto.EmployeeDto;
import com.nts.model.response.PaginationResponse;

@Service
public interface EmployeeService extends UserDetailsService {

	EmployeeDto createEmployee(EmployeeDto employeeDto);

	EmployeeDto updateEmployee(EmployeeDto employeeDto, String empId);

	EmployeeDto getEmployeeById(String empId);

	PaginationResponse getAllEmployee(Integer pageNumber, Integer pageSize, String sortBy, String empId);

	void deleteEmployee(String empId);

}
