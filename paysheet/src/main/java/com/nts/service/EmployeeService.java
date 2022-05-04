package com.nts.service;

import java.util.List;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import com.nts.model.dto.EmployeeDto;


@Service
public interface EmployeeService extends UserDetailsService {

//	public EmployeeResponse createEmployee(Employee employee);
	
	
	
	EmployeeDto createEmployee(EmployeeDto employeeDto);
	
	EmployeeDto updateEmployee(EmployeeDto employeeDto, String empId);
	
	EmployeeDto getEmployeeById(String empId);
	
	List<EmployeeDto> getAllEmployee();
	
//	List<EmployeeDto> findAll(Sort sort);
	
	void deleteEmployee(String empId);

}
