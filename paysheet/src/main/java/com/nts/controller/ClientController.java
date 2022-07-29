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

import com.nts.model.dto.ClientDto;
import com.nts.model.response.PaginationResponse;
import com.nts.service.impl.ClientServiceImpl;

@RestController
@RequestMapping(value = "/client")
public class ClientController {

	private static final Logger logger = LoggerFactory.getLogger(ClientController.class);
	@Autowired
	private ClientServiceImpl clientService;

	// CREATE
	@PostMapping()
	public ResponseEntity<Object> createClient(@Valid @RequestBody ClientDto clientDto) {
		logger.info("Client Controller | Create Client API");
		ClientDto createClient = this.clientService.createClient(clientDto);
		return new ResponseEntity<>(createClient, HttpStatus.CREATED);

		// return tasksService.createTasks(tasks);
	}

	// UPDATE
	@PutMapping("/{id}")
	public ResponseEntity<Object> updateClient(@Valid @RequestBody ClientDto client, @PathVariable String id) {
		logger.info("Client Controller | Update client API");
		ClientDto updatedClient = this.clientService.updateClient(client, id);
		return ResponseEntity.ok(updatedClient);
	}

	// GETALLTASKS&SINGLETASK
	@GetMapping()
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
	public ResponseEntity<com.nts.model.response.ApiResponse> deleteClient(@PathVariable String id) {
		this.clientService.deleteClient(id);
		return new ResponseEntity<com.nts.model.response.ApiResponse>(
				new com.nts.model.response.ApiResponse("Client successfully deleted", true), HttpStatus.OK);
	}

}
