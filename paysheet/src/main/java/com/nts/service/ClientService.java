package com.nts.service;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.nts.model.dto.ClientDto;
import com.nts.model.response.PaginationResponse;

@Service
public interface ClientService extends UserDetailsService {

	public ResponseEntity<Object> createClient(ClientDto clientDto);

	public ResponseEntity<Object> updateClient(ClientDto clientDto, String id);

	public ClientDto getClientById(String id);

	PaginationResponse getAllClient(Integer pageNumber, Integer pageSize, String sortBy, String id);

	ResponseEntity<Object> deleteClient(String id);

}
