package com.nts.service;

import java.util.List;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import com.nts.model.dto.EmployeeDto;
import com.nts.model.response.PaginationResponse;

import io.swagger.models.auth.In;


@Service
public interface EmployeeService extends UserDetailsService {

//	public EmployeeResponse createEmployee(Employee employee);
	
	EmployeeDto createEmployee(EmployeeDto employeeDto);
	
	EmployeeDto updateEmployee(EmployeeDto employeeDto, String empId);
	
	EmployeeDto getEmployeeById(String empId);
	
	PaginationResponse getAllEmployee(Integer pageNumber ,Integer pageSize, String sortBy, String empId);
	
//	List<EmployeeDto> findAll(Sort sort);
	
	void deleteEmployee(String empId);

}
