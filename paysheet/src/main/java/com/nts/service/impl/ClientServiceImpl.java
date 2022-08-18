package com.nts.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.nts.exception.ResourceNotFoundException;
import com.nts.model.dto.ClientDto;
import com.nts.model.entity.Client;
import com.nts.model.response.PaginationResponse;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ClientDto createClient(ClientDto clientDto) {
		logger.debug("ClientServiceImpl | Create Client Invoked...");
		Client client = this.dtoToClient(clientDto);
		Client createClient = this.clientRepository.save(client);
		return this.clientToDto(createClient);

	}

	@Override
	public ClientDto updateClient(ClientDto clientDto, String id) {
		Client client = this.clientRepository.findById(id).orElse(null);
		client.setClientName(clientDto.getClientName());
		client.setClientCode(clientDto.getClientCode());
		client.setClientDetail(clientDto.getClientDetail());
		client.setAddress(clientDto.getAddress());
		client.setCountry(clientDto.getCountry());
		client.setState(clientDto.getState());
		client.setCity(clientDto.getCity());
		client.setPincode(clientDto.getPincode());

		Client save = this.clientRepository.save(client);

		ClientDto clientToDto = this.clientToDto(save);
		return clientToDto;
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
	public void deleteClient(String id) {
		Client client = this.clientRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Client", "id", "id"));
		this.clientRepository.delete(client);
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
