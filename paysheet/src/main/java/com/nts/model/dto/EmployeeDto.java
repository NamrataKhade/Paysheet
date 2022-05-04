package com.nts.model.dto;

import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class EmployeeDto {

	private String empId;
	@NotEmpty
	@Size(min=4, message="First name must be min 4 characters")
	private String firstName;

	private String middleName;
	@NotNull
	private String lastName;

	private String gender;
	@Email(message="Your Email is not valid...")
	private String email;
	@NotEmpty
	private String password;

	private String status;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private Date dob;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private Date doj;

	private String reportingManager;
	
	@NotEmpty
	@Size(min = 10,max=10, message="Incurrect mobile number... please try again...!")
	private String mobileNumber;

}
