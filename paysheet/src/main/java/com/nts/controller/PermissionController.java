package com.nts.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
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

import com.nts.model.dto.PermissionDto;
import com.nts.model.response.ApiResponce;
import com.nts.service.PermissionService;
import com.nts.service.impl.EmployeeServiceImpl;
import com.nts.validatorgroups.OnCreate;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "/permission")
public class PermissionController {

	private static final Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);

	@Autowired
	private PermissionService permissionService;

	@PostMapping("/")
	@ApiOperation(value = "Create Permission", nickname = "CreatePermission")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully create schema"),
			@ApiResponse(code = 404, message = "Schema not found"),
			@ApiResponse(code = 400, message = "Missing or invalid request body"),
			@ApiResponse(code = 500, message = "Internal error") })
	@Validated(OnCreate.class)
	public ResponseEntity<PermissionDto> createPermission(@Valid @RequestBody PermissionDto permissionDto) {
		logger.debug("PermissionServiceImpl | Create Permission API");
		PermissionDto createPermission = this.permissionService.createPermission(permissionDto);

		return new ResponseEntity<PermissionDto>(createPermission, HttpStatus.CREATED);
	}

	@GetMapping("/")
	@ApiOperation(value = "Get Permission", nickname = "GetPermission")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully Get schema"),
			@ApiResponse(code = 404, message = "Schema not found"),
			@ApiResponse(code = 400, message = "Missing or invalid request body"),
			@ApiResponse(code = 500, message = "Internal error") })
	@Validated(OnCreate.class)
	public ResponseEntity<Object> getPermission(
			@RequestParam(name = "permissionId", required = false) String permissionId,
			@RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "5", required = false) Integer pageSize,
			@RequestParam(value = "sortBy", defaultValue = "permissionId", required = false) String sortBy,
			@RequestParam(value = "sortDir", defaultValue = "permissionId", required = false) String sortDir) {

		logger.debug("PermissionServiceImpl | Get Permission API");
		if (null == permissionId || StringUtils.isEmpty(permissionId)) {
			List<PermissionDto> permission = this.permissionService.getListOfPermission(pageNumber, pageSize, sortBy,
					sortDir);

			return ResponseEntity.ok().body(permission);

		} else {
			return ResponseEntity.ok().body(permissionService.getPermissionById(permissionId));
		}

	}

	@PutMapping("/{permissionId}")
	@ApiOperation(value = "Update Permission", nickname = "UpdatePermission")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully updated schema"),
			@ApiResponse(code = 404, message = "Schema not found"),
			@ApiResponse(code = 400, message = "Missing or invalid request body"),
			@ApiResponse(code = 500, message = "Internal error") })
	@Validated(OnCreate.class)
	public ResponseEntity<PermissionDto> updatePermission(@RequestBody PermissionDto PermissionDto,
			@PathVariable String permissionId) {

		logger.debug("PermissionServiceImpl | Update Permission API");
		PermissionDto updatePermission = this.permissionService.updatePermission(PermissionDto, permissionId);
		return new ResponseEntity<PermissionDto>(updatePermission, HttpStatus.OK);

	}

	@DeleteMapping("/{permissionId}")
	@ApiOperation(value = "Delete Permission", nickname = "DeletePermission")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully Deleted schema"),
			@ApiResponse(code = 404, message = "Schema not found"),
			@ApiResponse(code = 400, message = "Missing or invalid request body"),
			@ApiResponse(code = 500, message = "Internal error") })
	@Validated(OnCreate.class)
	public ResponseEntity<Object> deleteById(@PathVariable String permissionId) {
		logger.debug("PermissionServiceImpl | Delete Permission API");
		this.permissionService.deletedPermission(permissionId);
		return new ResponseEntity<Object>(new ApiResponce("Permission Deleted successfully", true), HttpStatus.OK);

	}

}
