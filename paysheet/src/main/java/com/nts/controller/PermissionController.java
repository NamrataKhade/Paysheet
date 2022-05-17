package com.nts.controller;



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

import com.nts.model.entity.Permission;
import com.nts.model.response.ApiResponce;
import com.nts.model.response.PermissionResponce;
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

//	******************************* CREATE PERMISSION *********************

	@PostMapping()
	@ApiOperation(value = "Create Permission", nickname = "CreatePermission")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully create schema"),
			@ApiResponse(code = 404, message = "Schema not found"),
			@ApiResponse(code = 400, message = "Missing or invalid request body"),
			@ApiResponse(code = 500, message = "Internal error") })
	@Validated(OnCreate.class)
	public ResponseEntity<Object> createPermission(@Valid @RequestBody Permission permission
			) {
		logger.debug("PermissionServiceImpl | Create Permission API");
		
		return ResponseEntity.ok().body(permissionService.createPermission(permission));
	}

//	******************************* GET PERMISSION *********************

	@GetMapping("/per")
	@ApiOperation(value = "Get Permission", nickname = "GetPermission")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully Get schema"),
			@ApiResponse(code = 404, message = "Schema not found"),
			@ApiResponse(code = 400, message = "Missing or invalid request body"),
			@ApiResponse(code = 500, message = "Internal error") })
	@Validated(OnCreate.class)
	public ResponseEntity<PermissionResponce> getPermission(
			@RequestParam(name = "permissionId", required = false) String permissionId,
			@RequestParam(value = "pageNumber",defaultValue = "0",required = false) Integer pageNumber,
			@RequestParam(value = "pageSize",defaultValue = "5",required = false)Integer pageSize,
			@RequestParam(value = "sortBy",defaultValue = "perId",required = false) String sortBy,
			@RequestParam(value = "sortDir",defaultValue = "perId",required = false) String sortDir
			) {

		logger.debug("PermissionServiceImpl | Get Permission API");
		if (null == permissionId || StringUtils.isEmpty(permissionId)) {
			  PermissionResponce permissionResponce = this.permissionService.getListOfPermission(pageNumber,pageSize,sortBy,sortDir);
//			return ResponseEntity.ok().body(permissionService.getListOfPermission());
			return new ResponseEntity<PermissionResponce>(permissionResponce,HttpStatus.OK);
			
		} else {
			return ResponseEntity.ok().body(permissionService.getPermissionById(permissionId));
		}

	}

//	******************************* UPDATE PERMISSION *********************

	@PutMapping("/")
	@ApiOperation(value = "Update Permission", nickname = "UpdatePermission")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully updated schema"),
			@ApiResponse(code = 404, message = "Schema not found"),
			@ApiResponse(code = 400, message = "Missing or invalid request body"),
			@ApiResponse(code = 500, message = "Internal error") })
	@Validated(OnCreate.class)
	public ResponseEntity<Object> updatePermissions(@Valid @RequestBody Permission permission) {

		return ResponseEntity.ok().body(permissionService.updatePermission(permission));
//		return new ResponseEntity<ApiResponce>(new ApiResponce("Update successfully", true), HttpStatus.OK);

	}

	@PutMapping("/{perId}")
	@ApiOperation(value = "Update Permission", nickname = "UpdatePermission")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully updated schema"),
			@ApiResponse(code = 404, message = "Schema not found"),
			@ApiResponse(code = 400, message = "Missing or invalid request body"),
			@ApiResponse(code = 500, message = "Internal error") })
	@Validated(OnCreate.class)
	public ResponseEntity<Object> updatePermission(@RequestBody Permission permission, @PathVariable String perId) {

		logger.debug("PermissionServiceImpl | Update Permission API");

		return ResponseEntity.ok().body(permissionService.updatePermission(permission, perId));
//		return new ResponseEntity<ApiResponce>(new ApiResponce("Update successfully", true), HttpStatus.OK);

	}
//	******************************* DELETE PERMISSION *********************

	@DeleteMapping("/deleteById/{perId}")
	@ApiOperation(value = "Delete Permission", nickname = "DeletePermission")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully Deleted schema"),
			@ApiResponse(code = 404, message = "Schema not found"),
			@ApiResponse(code = 400, message = "Missing or invalid request body"),
			@ApiResponse(code = 500, message = "Internal error") })
	@Validated(OnCreate.class)
	public ResponseEntity<Object> deleteById(@PathVariable String perId) {
		logger.debug("PermissionServiceImpl | Delete Permission API");
		this.permissionService.deletedPermission(perId);
		return new ResponseEntity<Object>(new ApiResponce("Permission Deleted successfully", true), HttpStatus.OK);

	}

}
