package com.nts.model.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {
	
	
	
	private String empId;
	private String email;
	private String Username;
	private String jwtToken;
	private List<String> roles;
	private List<String> permissions;
	private String firstName;
	

	
}
