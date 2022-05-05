package com.nts.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.nts.model.entity.Employee;
import com.nts.model.response.EmployeeResponse;

@Service
public interface EmployeeService extends UserDetailsService {

	public EmployeeResponse createEmployee(Employee employee);

	public EmployeeResponse getAllEmployeeById(String empId);

	public List<Employee> getAllEmployee();

	public EmployeeResponse deleteEmployeeById(String empId);

	public EmployeeResponse updateEmployee(String id, Employee employee);

}
