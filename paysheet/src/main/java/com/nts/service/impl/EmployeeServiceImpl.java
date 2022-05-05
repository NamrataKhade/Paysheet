package com.nts.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.nts.model.dto.EmployeeDto;
import com.nts.model.entity.Employee;
import com.nts.model.response.EmployeeResponse;
import com.nts.repository.EmployeeRepository;
import com.nts.service.EmployeeService;

import lombok.Getter;

@Getter
@Service
public class EmployeeServiceImpl implements EmployeeService {

	private static final Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public EmployeeResponse createEmployee(Employee employee) {

		logger.debug("EmployeeServiceImpl | Create Employee Invoked...");
		EmployeeResponse employeeResponse = new EmployeeResponse();
		employee.setFirstName(employee.getFirstName());
		employeeRepository.save(employee);
		EmployeeDto employeeDto = new EmployeeDto();
		employeeDto.setEmpId(employee.getEmpId());
		employeeDto.setFirstName(employee.getFirstName());
		employeeDto.setMiddleName(employee.getMiddleName());
		employeeDto.setLastName(employee.getLastName());
		employeeDto.setGender(employee.getGender());
		employeeDto.setEmail(employee.getEmail());
		employeeDto.setStatus(employee.getStatus());
		employeeDto.setDob(employee.getDob());
		employeeDto.setDoj(employee.getDoj());
		employeeDto.setReportingManager(employee.getReportingManager());
		employeeResponse.setEmployeeDto(employeeDto);
		return employeeResponse;

	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return new User("admin", "password", new ArrayList<>());
	}

	@Override
	public EmployeeResponse getAllEmployeeById(String empId) {
		// TODO Auto-generated method stub
		// return employeeRepository.findById(empId).orElseThrow(()->new
		// RuntimeException(String.format("Can not find Employee By Id", empId)));
		Employee employee = employeeRepository.findById(empId)
				.orElseThrow(() -> new RuntimeException(String.format("Can not find Employee By Id", empId)));

		EmployeeDto employeeDto = new EmployeeDto();

		employeeDto.setEmpId(employee.getEmpId());
		employeeDto.setFirstName(employee.getFirstName());
		employeeDto.setMiddleName(employee.getMiddleName());
		employeeDto.setLastName(employee.getLastName());
		employeeDto.setGender(employee.getGender());
		employeeDto.setEmail(employee.getEmail());
		employeeDto.setStatus(employee.getStatus());
		employeeDto.setDob(employee.getDob());
		employeeDto.setDoj(employee.getDoj());

		EmployeeResponse employeeResponse = new EmployeeResponse();
		employeeResponse.setEmployeeDto(employeeDto);

		return employeeResponse;
	}

	@Override
	public List<Employee> getAllEmployee() {
		// TODO Auto-generated method stub

		return employeeRepository.findAll();
	}

	@Override
	public EmployeeResponse deleteEmployeeById(String empId) {
		// TODO Auto-generated method stub
//		 = employeeRepository.deleteById(empId).orElseThrow(()->new RuntimeException(String.format("Can not find Employee By Id", empId)));
		Employee employee = new Employee();

		employeeRepository.deleteById(empId);
		EmployeeDto employeeDto = new EmployeeDto();

		employeeDto.setEmpId(employee.getEmpId());
		employeeDto.setFirstName(employee.getFirstName());
		employeeDto.setMiddleName(employee.getMiddleName());
		employeeDto.setLastName(employee.getLastName());
		employeeDto.setGender(employee.getGender());
		employeeDto.setEmail(employee.getEmail());
		employeeDto.setStatus(employee.getStatus());
		employeeDto.setDob(employee.getDob());
		employeeDto.setDoj(employee.getDoj());

		EmployeeResponse employeeResponse = new EmployeeResponse();
		employeeResponse.setEmployeeDto(employeeDto);

		return employeeResponse;

	}

	@Override
	public EmployeeResponse updateEmployee(String empId, Employee employee) {
		// TODO Auto-generated method stub

		employee.setEmpId(empId);

		employeeRepository.save(employee);

		EmployeeDto employeeDto = new EmployeeDto();
		employeeDto.setEmpId(employee.getEmpId());
		employeeDto.setFirstName(employee.getFirstName());
		employeeDto.setMiddleName(employee.getMiddleName());
		employeeDto.setLastName(employee.getLastName());
		employeeDto.setGender(employee.getGender());
		employeeDto.setEmail(employee.getEmail());
		employeeDto.setStatus(employee.getStatus());
		employeeDto.setDob(employee.getDob());
		employeeDto.setDoj(employee.getDoj());
		employeeDto.setReportingManager(employee.getReportingManager());
		EmployeeResponse employeeResponse = new EmployeeResponse();
		employeeResponse.setEmployeeDto(employeeDto);
		return employeeResponse;
	}

}
