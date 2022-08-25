package com.nts.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Sets;
import com.nts.model.EmployeeDetailsImpl;
import com.nts.model.request.LoginRequest;
import com.nts.model.response.LoginResponse;
import com.nts.utility.JwtUtils;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public class AuthController {

	@Autowired
	private JwtUtils jwtUtils;

	@Autowired
	private AuthenticationManager authenticationManager;

	@PostMapping("/authenticate")
	@ApiOperation(value = "Create Token", nickname = "Authentication")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 401, message = "Not authenticated"),
			@ApiResponse(code = 403, message = "Access token does not have the required scope") })
	public ResponseEntity<LoginResponse> authenticateUser(@RequestBody LoginRequest jwtRequest) throws Exception {
		try {
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(jwtRequest.getUserName(), jwtRequest.getPassword()));
			SecurityContextHolder.getContext().setAuthentication(authentication);
			String jwtToken = jwtUtils.generateToken(authentication);

			EmployeeDetailsImpl employeeDetailsImpl = (EmployeeDetailsImpl) authentication.getPrincipal();
			List<String> roles = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority)
					.collect(Collectors.toList());
			Set<String> permissionsSet = Sets.newHashSet(employeeDetailsImpl.getPermissions());
			List<String> permissions = new ArrayList<>(permissionsSet);

			return ResponseEntity.ok(new LoginResponse(employeeDetailsImpl.getEmpId(), employeeDetailsImpl.getEmail(),
					employeeDetailsImpl.getUsername(), jwtToken, roles, permissions,
					employeeDetailsImpl.getFirstName()));

		} catch (BadCredentialsException e) {
			throw new Exception("INVALID CREDENTIALS ", e);
		}

	}

}
