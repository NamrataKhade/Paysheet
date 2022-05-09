package com.nts.controller;

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

//	******************************* CREATE PERMISSION *********************
	
	@PostMapping()
	@ApiOperation(value = "Create Permission", nickname = "CreatePermission")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully updated schema"),
			@ApiResponse(code = 404, message = "Schema not found"),
			@ApiResponse(code = 400, message = "Missing or invalid request body"),
			@ApiResponse(code = 500, message = "Internal error") })
	@Validated(OnCreate.class)
	public ResponseEntity<Object> createPermission(@RequestBody Permission permission) {

		return ResponseEntity.ok().body(permissionService.createPermission(permission));
	}

//	******************************* GET PERMISSION *********************
	
	@GetMapping("/")
	@ApiOperation(value = "Create Permission", nickname = "CreatePermission")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully updated schema"),
			@ApiResponse(code = 404, message = "Schema not found"),
			@ApiResponse(code = 400, message = "Missing or invalid request body"),
			@ApiResponse(code = 500, message = "Internal error") })
	@Validated(OnCreate.class)
	public ResponseEntity<Object> getPermission(
			@RequestParam(name = "permissionId", required = false) String permissionId) {
		
		if (null == permissionId || StringUtils.isEmpty(permissionId)) {

			return ResponseEntity.ok().body(permissionService.getListOfPermission());
		} else {
			return ResponseEntity.ok().body(permissionService.getPermissionById(permissionId));
		}

	}

//	******************************* UPDATE PERMISSION *********************
	
	@PutMapping("/")
	@ApiOperation(value = "Create Permission", nickname = "CreatePermission")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully updated schema"),
			@ApiResponse(code = 404, message = "Schema not found"),
			@ApiResponse(code = 400, message = "Missing or invalid request body"),
			@ApiResponse(code = 500, message = "Internal error") })
	@Validated(OnCreate.class)
	public ResponseEntity<ApiResponce> updatePermissions(@RequestBody Permission permission) {
		
		this.permissionService.updatePermission(permission);
		return new ResponseEntity<ApiResponce>(new ApiResponce("Update successfully", true), HttpStatus.OK);

	}
	
//	******************************* DELETE PERMISSION *********************
	
	@DeleteMapping("/deleteById/{perId}")
	@ApiOperation(value = "Create Permission", nickname = "CreatePermission")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully Deleted schema"),
			@ApiResponse(code = 404, message = "Schema not found"),
			@ApiResponse(code = 400, message = "Missing or invalid request body"),
			@ApiResponse(code = 500, message = "Internal error") })
	@Validated(OnCreate.class)
	public ResponseEntity<Object> deleteById(@PathVariable String perId) {

		this.permissionService.deletedPermission(perId);
		return new ResponseEntity<Object>(new ApiResponce("Permission Deleted successfully", true), HttpStatus.OK);

	}

	


}
