package com.nts.model.response;

import java.util.List;

import com.nts.model.dto.EmployeeDto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
public class EmployeeResponse {

	private EmployeeDto employeeDto;
	private String status;
	
}
