package com.nts.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.nts.exception.ResourceNotFoundExpection;
import com.nts.model.dto.EmployeeDto;
import com.nts.model.entity.Employee;
import com.nts.repository.EmployeeRepository;
import com.nts.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private static final Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return new User("admin","password", new ArrayList<>());
			
	}
	
	@Override
	public EmployeeDto createEmployee(EmployeeDto employeeDto) 
	{
		Employee employee = this.dtoToEmployee(employeeDto);
		logger.debug("EmployeeServiceImpl | Create Employee Invoked...");
		Employee savedEmployee = this.employeeRepository.save(employee);
		return this.employeeToDto(savedEmployee);
	}

	@Override
	public EmployeeDto updateEmployee(EmployeeDto employeeDto, String empId) {
		
		Employee employee = this.employeeRepository.findById(empId).orElseThrow(() -> new ResourceNotFoundExpection("Employee", "Id", empId));
		
		employee.setFirstName(employeeDto.getFirstName());
		employee.setMiddleName(employeeDto.getMiddleName());
		employee.setLastName(employeeDto.getLastName());
		employee.setGender(employeeDto.getGender());
		employee.setEmail(employeeDto.getEmail());
		employee.setStatus(employeeDto.getStatus());
		employee.setDob(employeeDto.getDob());
		employee.setDoj(employeeDto.getDoj());
		employee.setReportingManager(employeeDto.getReportingManager());
		employee.setMobileNumber(empId);
		
		Employee updatedEmployee = this.employeeRepository.save(employee);
		EmployeeDto employeeToDto = this.employeeToDto(updatedEmployee);
		
		return employeeToDto;
	}

	@Override
	public EmployeeDto getEmployeeById(String empId) {
		Employee employee = this.employeeRepository.findById(empId).orElseThrow(()-> new ResourceNotFoundExpection("Employee", "Id", empId));
		return this.employeeToDto(employee);
	}

	@Override
	public List<EmployeeDto> getAllEmployee() 
	{
		List<Employee> employees = this.employeeRepository.findAll();
		List<EmployeeDto> employeeDtos = employees.stream().map(employee ->this.employeeToDto(employee)).collect(Collectors.toList());
		
		return employeeDtos;
	}

	@Override
	public void deleteEmployee(String empId) 
	{
		Employee employee = this.employeeRepository.findById(empId).orElseThrow(()-> new ResourceNotFoundExpection("Employee", "Id", empId));
		this.employeeRepository.delete(employee);
	}
	
	public Employee dtoToEmployee(EmployeeDto employeeDto)
	{
		Employee employee = this.modelMapper.map(employeeDto, Employee.class);
		
	
		return employee;
	}
	
	public EmployeeDto employeeToDto(Employee employee)
	{
		EmployeeDto employeeDto = this.modelMapper.map(employee, EmployeeDto.class);

		
		return employeeDto;
	}

	



	
	
}
