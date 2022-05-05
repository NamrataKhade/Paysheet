package com.nts.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nts.model.entity.Permission;
import com.nts.model.response.EmployeeResponse;
import com.nts.model.response.PermissionResponce;
import com.nts.service.PermissionService;
import com.nts.validatorgroups.OnCreate;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "/permission")
public class PermissionController {

	@Autowired
	private PermissionService permissionService;

	@PostMapping()
	@ApiOperation(value = "Create Permission", nickname = "CreatePermission")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully updated schema"),
			@ApiResponse(code = 404, message = "Schema not found"),
			@ApiResponse(code = 400, message = "Missing or invalid request body"),
			@ApiResponse(code = 500, message = "Internal error") })
	@Validated(OnCreate.class)
	public PermissionResponce createPermission(@RequestBody Permission permission) {
		return permissionService.createPermission(permission);
	}

	@GetMapping("/getPermission/{perId}")
	@ApiOperation(value = "Create Permission", nickname = "CreatePermission")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully get schema"),
			@ApiResponse(code = 404, message = "Schema not found"),
			@ApiResponse(code = 400, message = "Missing or invalid request body"),
			@ApiResponse(code = 500, message = "Internal error") })
	@Validated(OnCreate.class)
	public PermissionResponce getPerissionById(@PathVariable String perId) {

		return permissionService.getPermissionById(perId);
	}

	@GetMapping("/getAllPermissionList")
	@ApiOperation(value = "Create Permission", nickname = "CreatePermission")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully get schema"),
			@ApiResponse(code = 404, message = "Schema not found"),
			@ApiResponse(code = 400, message = "Missing or invalid request body"),
			@ApiResponse(code = 500, message = "Internal error") })
	@Validated(OnCreate.class)
	public List<Permission> getAllPermissionList() {

		return permissionService.getListOfPermission();
	}

	@GetMapping
	@ApiOperation(value = "Create Permission", nickname = "CreatePermission")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully get schema"),
			@ApiResponse(code = 404, message = "Schema not found"),
			@ApiResponse(code = 400, message = "Missing or invalid request body"),
			@ApiResponse(code = 500, message = "Internal error") })
	@Validated(OnCreate.class)
	public ResponseEntity<Object> getPermissionDetails(@RequestParam(required = false) String permissionId) {

		return permissionService.getPermissionDetails(permissionId);
	}

	@DeleteMapping("/deleteById/{perId}")
	@ApiOperation(value = "Create Permission", nickname = "CreatePermission")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully Deleted schema"),
			@ApiResponse(code = 404, message = "Schema not found"),
			@ApiResponse(code = 400, message = "Missing or invalid request body"),
			@ApiResponse(code = 500, message = "Internal error") })
	@Validated(OnCreate.class)
	public PermissionResponce deleteById(@PathVariable String perId) {
		return permissionService.deletePermissionById(perId);
	}

	@PutMapping("/update/{perId}")
	@ApiOperation(value = "Create Permission", nickname = "CreatePermission")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully Updated schema"),
			@ApiResponse(code = 404, message = "Schema not found"),
			@ApiResponse(code = 400, message = "Missing or invalid request body"),
			@ApiResponse(code = 500, message = "Internal error") })
	@Validated(OnCreate.class)
	public PermissionResponce updetePermission(@PathVariable String perId, @RequestBody Permission permission) {

		return permissionService.updatepermission(perId, permission);
	}

}
