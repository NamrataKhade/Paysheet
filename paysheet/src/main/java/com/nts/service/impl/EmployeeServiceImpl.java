package com.nts.service.impl;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.nts.model.entity.Employee;
import com.nts.model.response.EmployeeResponse;
import com.nts.repository.EmployeeRepository;
import com.nts.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private static final Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Override
	public EmployeeResponse createEmployee(Employee employee) {
		logger.debug("EmployeeServiceImpl | Create Employee Invoked...");
		EmployeeResponse employeeResponse =  new EmployeeResponse();
		employee.setFirstName(employee.getFirstName());
		employeeRepository.save(employee);
		return employeeResponse;

	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return new User("admin","password", new ArrayList<>());
	}
	
}
