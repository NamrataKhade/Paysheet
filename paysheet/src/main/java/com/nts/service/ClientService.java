package com.nts.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.nts.model.dto.ClientDto;
import com.nts.model.response.PaginationResponse;

@Service
public interface ClientService extends UserDetailsService {

	ClientDto createClient(ClientDto clientDto);

	ClientDto updateClient(ClientDto clientDto, String id);

	ClientDto getClientById(String id);

	PaginationResponse getAllClient(Integer pageNumber, Integer pageSize, String sortBy, String id);

	void deleteClient(String id);

}
