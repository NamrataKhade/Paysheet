package com.nts.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.nts.exception.ResourceNotFoundException;
import com.nts.model.dto.ClientDto;
import com.nts.model.entity.Client;
import com.nts.model.response.PaginationResponse;
import com.nts.model.response.Response;
import com.nts.repository.ClientRepository;
import com.nts.service.ClientService;

@Service
public class ClientServiceImpl implements ClientService {

	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private ModelMapper modelMapper;

	private static final Logger logger = LoggerFactory.getLogger(ClientServiceImpl.class);

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return new User("admin", "password", new ArrayList<>());

	}

	@Override
	public ResponseEntity<Object> createClient(ClientDto clientDto) {
		logger.debug("ClientServiceImpl | Create Client Invoked...");
		try {
			if (clientDto.getId() != null) {
				logger.info("ClientServiceImpl | Client Is already there");
				throw new ResourceNotFoundException("Client is alredy", "id", clientDto.getId());
			}
			Client client = this.modelMapper.map(clientDto, Client.class);
			Client createClient = this.clientRepository.save(client);
			this.modelMapper.map(createClient, clientDto);
			return new ResponseEntity<Object>(new Response("Create Client", true), HttpStatus.CREATED);
		} catch (Exception e) {
			logger.error("ClentServiceImpl | Client Is already there");
			throw new ResourceNotFoundException("Client is already", "id", clientDto.getId());
		}
	}

	@Override
	public ResponseEntity<Object> updateClient(ClientDto clientDto, String id) {
		try {
			logger.info("ClientServiceImpl | Update Client");
			Client client = clientRepository.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("Client is Not", "ClientId", id));
			client.setClientName(clientDto.getClientName());
			client.setClientCode(clientDto.getClientCode());
			client.setClientDetail(clientDto.getClientDetail());
			client.setAddress(clientDto.getAddress());
			client.setCountry(clientDto.getCountry());
			client.setState(clientDto.getState());
			client.setCity(clientDto.getCity());
			client.setPincode(clientDto.getPincode());

			Client save = this.clientRepository.save(client);
			modelMapper.map(save, ClientDto.class);
			return new ResponseEntity<Object>(new Response("Update Client Success", true), HttpStatus.OK);
		} catch (Exception e) {
			logger.error("PermissionServiceImpl | Something wrong in update Client");
			return new ResponseEntity<Object>(new Response("Somting Wrong", true), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public ClientDto getClientById(String id) {
		Client client = this.clientRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Client", "id", "id"));
		return this.clientToDto(client);
	}

	@Override
	public PaginationResponse getAllClient(Integer pageNumber, Integer pageSize, String sortBy, String id) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize, org.springframework.data.domain.Sort.by(sortBy));
		Page<Client> clients = this.clientRepository.findAll(pageable);
		List<Client> allClients = clients.getContent();
		List<Object> clientDtos = allClients.stream().map(client -> this.modelMapper.map(client, ClientDto.class))
				.collect(Collectors.toList());
		PaginationResponse paginationResopnse = new PaginationResponse();
		paginationResopnse.setContent(clientDtos);
		paginationResopnse.setPageNumber(clients.getNumber());
		paginationResopnse.setPageSize(clients.getSize());
		paginationResopnse.setTotalElement(clients.getTotalElements());
		paginationResopnse.setTotalPage(clients.getTotalPages());
		paginationResopnse.setLastPage(clients.isLast());
		return paginationResopnse;
	}

	@Override
	public ResponseEntity<Object> deleteClient(String id) {
		try {
			logger.info("ClientServiceImpl | Delete Client of specific clientId");
			clientRepository.findById(id).orElseThrow();
			this.clientRepository.deleteById(id);
		} catch (Exception e) {
			logger.error("ClientServiceImpl | Somting wrong in deleting Client");
			this.clientRepository.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("Client is Not", "id", id));
		}
		return new ResponseEntity<Object>(new Response("Deleted Sucessfully", true), HttpStatus.OK);
	}

	public Client dtoToClient(ClientDto clientDto) {
		Client client = this.modelMapper.map(clientDto, Client.class);
		return client;
	}

	public ClientDto clientToDto(Client client) {
		ClientDto clientDto = this.modelMapper.map(client, ClientDto.class);
		return clientDto;
	}
}
