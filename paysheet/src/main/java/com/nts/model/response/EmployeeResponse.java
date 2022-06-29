package com.nts.model.response;

import com.nts.model.dto.EmployeeDto;

import lombok.Data;

@Data
public class EmployeeResponse {

	private EmployeeDto employeeDto;

	public EmployeeDto getEmployeeDto() {
		return employeeDto;
	}

	public void setEmployeeDto(EmployeeDto employeeDto) {
		this.employeeDto = employeeDto;
	}

}
