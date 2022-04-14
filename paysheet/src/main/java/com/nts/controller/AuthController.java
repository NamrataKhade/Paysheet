package com.nts.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nts.model.request.LoginRequest;
import com.nts.model.response.LoginResponse;
import com.nts.service.EmployeeService;
import com.nts.utility.JwtUtils;

@RestController
@CrossOrigin
public class AuthController {

	@Autowired
	private JwtUtils jwtUtils;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private EmployeeService employeeService;

	@PostMapping("/authenticate")
	public LoginResponse authenticate(@RequestBody LoginRequest jwtRequest) throws Exception {
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(jwtRequest.getUserName(), jwtRequest.getPassword()));
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID CREDENTIALS ", e);
		}
		final UserDetails userDetails = employeeService.loadUserByUsername(jwtRequest.getUserName());

		final String token = jwtUtils.generateToken(userDetails);

		return new LoginResponse(token);
	}
}