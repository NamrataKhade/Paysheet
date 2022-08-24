package com.nts.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

import com.nts.model.dto.ClientDto;
import com.nts.model.response.PaginationResponse;
import com.nts.service.impl.ClientServiceImpl;
import com.nts.validatorgroups.OnCreate;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "/client")
public class ClientController {

	private static final Logger logger = LoggerFactory.getLogger(ClientController.class);
	@Autowired
	private ClientServiceImpl clientService;

	// CREATE
	@PostMapping()
	@ApiOperation(value = "Create Client", nickname = "CreateClient")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully create schema"),
			@ApiResponse(code = 404, message = "Schema not found"),
			@ApiResponse(code = 400, message = "Missing or invalid request body"),
			@ApiResponse(code = 500, message = "Internal error") })

	@Validated(OnCreate.class)
	public ResponseEntity<Object> createClient(@Valid @RequestBody ClientDto clientDto) {
		logger.info("Client Controller | Create Client API");
		return clientService.createClient(clientDto);
	}

	// UPDATE
	@PutMapping("/{id}")
	@ApiOperation(value = "Update Client", nickname = "UpdateClient")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully create schema"),
			@ApiResponse(code = 404, message = "Schema not found"),
			@ApiResponse(code = 400, message = "Missing or invalid request body"),
			@ApiResponse(code = 500, message = "Internal error") })

	@Validated(OnCreate.class)
	public ResponseEntity<Object> updateClient(@Valid @RequestBody ClientDto clientDto, @PathVariable String id) {
		logger.info("Client Controller | Update client API");

		return clientService.updateClient(clientDto, id);
	}

	// GETALLTASKS&SINGLETASK
	@GetMapping()
	@ApiOperation(value = "Get Client", nickname = "GetClient")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully create schema"),
			@ApiResponse(code = 404, message = "Schema not found"),
			@ApiResponse(code = 400, message = "Missing or invalid request body"),
			@ApiResponse(code = 500, message = "Internal error") })

	@Validated(OnCreate.class)
	public ResponseEntity<Object> getAllClient(@RequestParam(name = "id", required = false) String id,
			@RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "1000", required = false) Integer pageSize,
			@RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy) {

		if (id == null || id.isBlank()) {
			logger.info("Client Controller | Get All Client");
			PaginationResponse paginationResponse = this.clientService.getAllClient(pageNumber, pageSize, sortBy, id);
			return new ResponseEntity<Object>(paginationResponse, HttpStatus.OK);
		} else {
			logger.info("Client Controller | Get Single Client");
			return ResponseEntity.ok(this.clientService.getClientById(id));
		}
	}

	// DELETE
	@DeleteMapping("/{id}")
	@ApiOperation(value = "Delete  Client", nickname = "DeleteClient")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully create schema"),
			@ApiResponse(code = 404, message = "Schema not found"),
			@ApiResponse(code = 400, message = "Missing or invalid request body"),
			@ApiResponse(code = 500, message = "Internal error") })

	@Validated(OnCreate.class)
	public ResponseEntity<Object> deleteClient(@PathVariable String id) {
		logger.debug("ClientController | Delete Client API");
		return clientService.deleteClient(id);

	}

}
