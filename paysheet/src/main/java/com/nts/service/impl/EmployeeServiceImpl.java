package com.nts.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.nts.exception.ResourceNotFoundException;
import com.nts.model.dto.EmployeeDto;
import com.nts.model.entity.Employee;
import com.nts.model.response.PaginationResponse;
import com.nts.model.response.Response;
import com.nts.repository.EmployeeRepository;
import com.nts.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public ResponseEntity<Object> createEmployee(EmployeeDto employeeDto, String email) {
		try {
			Employee employee = modelMapper.map(employeeDto, Employee.class);
			Employee save = employeeRepository.save(employee);
			modelMapper.map(save, EmployeeDto.class);
			return new ResponseEntity<Object>(
					new Response("Create Employee with Email Id: " + employeeDto.getEmail(), true), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<Object>(new Response("Email Id exits :" + employeeDto.getEmail(), false),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public EmployeeDto updateEmployee(EmployeeDto employeeDto, String empId) {

		try {
			Employee employee = employeeRepository.findById(empId)
					.orElseThrow(() -> new ResourceNotFoundException("Employee", "Id", empId));
			modelMapper.map(employeeDto, employee);
			Employee updatedEmployee = employeeRepository.save(employee);
			return modelMapper.map(updatedEmployee, EmployeeDto.class);
		} catch (Exception e) {
			throw new ResourceNotFoundException("Something went wrong", "Id", empId);
		}
	}

	@Override
	public EmployeeDto getEmployeeById(String empId) {
		if (empId != null) {
			Employee employee = this.employeeRepository.findById(empId)
					.orElseThrow(() -> new ResourceNotFoundException("Employee", "Id", empId));
			return this.employeeToDto(employee);
		}
		throw new ResourceNotFoundException("empId NOT found ", "Id", empId);
	}

	@Override
	public PaginationResponse getAllEmployee(Integer pageNumber, Integer pageSize, String sortBy, String empId) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize, org.springframework.data.domain.Sort.by(sortBy));
		Page<Employee> employees = this.employeeRepository.findAll(pageable);
		List<Employee> allEmployee = employees.getContent();
		List<Object> employeeDtos = allEmployee.stream()
				.map(employee -> this.modelMapper.map(employee, EmployeeDto.class)).collect(Collectors.toList());
		PaginationResponse paginationResopnse = new PaginationResponse();
		paginationResopnse.setContent(employeeDtos);
		paginationResopnse.setPageNumber(employees.getNumber());
		paginationResopnse.setPageSize(employees.getSize());
		paginationResopnse.setTotalElement(employees.getTotalElements());
		paginationResopnse.setTotalPage(employees.getTotalPages());
		paginationResopnse.setLastPage(employees.isLast());

		return paginationResopnse;
	}

	@Override
	public void deleteEmployee(String empId) {
		Employee employee = this.employeeRepository.findById(empId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee", "Id", empId));
		employeeRepository.delete(employee);
	}

	public Employee dtoToEmployee(EmployeeDto employeeDto) {
		Employee employee = modelMapper.map(employeeDto, Employee.class);
		return employee;
	}

	public EmployeeDto employeeToDto(Employee employee) {
		EmployeeDto employeeDto = modelMapper.map(employee, EmployeeDto.class);
		return employeeDto;
	}

	public void copyObject(Employee employee, EmployeeDto employeeDto) {
		modelMapper.map(employeeDto, employee);
	}

}
