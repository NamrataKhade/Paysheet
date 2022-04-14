package com.nts.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.nts.model.entity.Employee;
import com.nts.model.response.EmployeeResponse;

@Service
public interface EmployeeService extends UserDetailsService {

	public EmployeeResponse createEmployee(Employee employee);

}
