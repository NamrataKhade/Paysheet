package com.nts.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nts.model.dto.EmployeeDto;
import com.nts.model.dto.RoleDto;
import com.nts.model.entity.Role;
import com.nts.service.impl.RoleServiceImpl;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "/role")
public class RoleController {

	private static final Logger logger = LoggerFactory.getLogger(RoleController.class);

	@Autowired
	private RoleServiceImpl roleService;

	@PostMapping()
	@ApiOperation(value = "Create Role", nickname = "CreateRole")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully updated schema"),
			@ApiResponse(code = 404, message = "Schema not found"),
			@ApiResponse(code = 400, message = "Missing or invalid request body"),
			@ApiResponse(code = 500, message = "Internal error") })
	public ResponseEntity<Object> createRole(@Valid @RequestBody Role role) {
		logger.info("RoleController: createRole: API to create a Role");
		return ResponseEntity.status(HttpStatus.CREATED).body(roleService.createRole(role));
	}

	@PutMapping()
	@ApiOperation(value = "Update Role", nickname = "UpdateRole")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully updated schema"),
			@ApiResponse(code = 404, message = "Schema not found"),
			@ApiResponse(code = 400, message = "Missing or invalid request body"),
			@ApiResponse(code = 500, message = "Internal error") })
	public ResponseEntity<Object> updateRole(@Valid @RequestBody RoleDto newRole, @RequestParam String roleId) {
		logger.info("RoleController: updateRole: API to update a Role");		
		RoleDto updatedRole = this.roleService.updateRole(newRole, roleId);
		return ResponseEntity.ok(updatedRole);
	}

	@DeleteMapping()
	@ApiOperation(value = "Delete Role", nickname = "DeleteRole")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully updated schema"),
			@ApiResponse(code = 404, message = "Schema not found"),
			@ApiResponse(code = 400, message = "Missing or invalid request body"),
			@ApiResponse(code = 500, message = "Internal error") })
	public void deleteRole(@RequestParam(name = "roleId") String roleId) {
		logger.info("RoleController: deleteRole: API to delete a Role");
		roleService.deleteRole(roleId);
	}

	@GetMapping()
	public ResponseEntity<Object> getRole(@RequestParam(name = "roleId", required = false) String roleId,
			@RequestParam(name = "roleName", required = false) String roleName,
			@RequestParam(name = "pageNumber", defaultValue = "0", required = false) int pageNumber,
			@RequestParam(name = "pageSize", defaultValue = "3", required = false) int pageSize,
			@RequestParam(name = "sortBy", defaultValue = "roleName") String sortBy) {
		if (roleId == null || roleId.isEmpty()) {
			if (roleName == null || roleName.isEmpty()) {
				logger.info("RoleController: getRole: API to find All Roles");
				return ResponseEntity.status(HttpStatus.FOUND)
						.body(roleService.getAllRoles(pageNumber, pageSize, sortBy));
			}
			logger.info("RoleController: getRole: API to find Role by Name");
			return ResponseEntity.status(HttpStatus.FOUND).body(roleService.getRoleByName(roleName));
		} else {
			logger.info("RoleController: getRole: API to find Role by Id");
			return ResponseEntity.status(HttpStatus.FOUND).body(roleService.getRoleById(roleId));
		}
	}
}
