package com.nts.model.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class EmployeeDto {

	private String empId;

	private String firstName;

	private String middleName;

	private String lastName;

	private String gender;

	private String email;

	private String password;

	private String status;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private Date dob;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private Date doj;

	private String reportingManager;

	private String mobileNumber;

}
