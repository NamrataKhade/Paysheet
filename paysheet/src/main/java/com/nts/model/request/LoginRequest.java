package com.nts.model.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class LoginRequest {
	@Email(message = "Invalid userName")
	@NotEmpty(message = "userName must not be empty")
	private String userName;
	
	@NotEmpty(message = "password must not be empty")
	private String password;
}
