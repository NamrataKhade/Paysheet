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
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.nts.exception.ResourceNotFoundException;
import com.nts.model.dto.PermissionDto;
import com.nts.model.entity.Permission;
import com.nts.model.response.PermissionResponce;
import com.nts.repository.PermissionRepository;
import com.nts.service.PermissionService;

@Service
public class PermissionServiceImpl implements PermissionService {

	private static final Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);

	@Autowired
	private PermissionRepository permissionRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		return new User("admin", "password", new ArrayList<>());

	}

	@Override
	public PermissionDto createPermission(PermissionDto permissionDto) {
		

		logger.debug("PermissionServiceImpl | Create Permission Invoked...");

		if (permissionDto.getPermissionId() != null) {
			System.out.println("User Is already there");
			throw new ResourceNotFoundException("Permission is alredy", "PermissionId",
					permissionDto.getPermissionId());
		}
		Permission permission = this.modelMapper.map(permissionDto, Permission.class);
		Permission save = permissionRepository.save(permission);

		return this.modelMapper.map(save, PermissionDto.class);
	}

	@Override
	public PermissionDto getPermissionById(String permissionId) {

		Permission permission = permissionRepository.findById(permissionId)
				.orElseThrow(() -> new ResourceNotFoundException("Permission is Not", "PermissionId", permissionId));

		return this.modelMapper.map(permission, PermissionDto.class);

	}

	@Override
	public List<PermissionDto> getListOfPermission(Integer pageNumber, Integer pageSize, String sortBy,
			String sortDir) {

		Sort sort = (sortDir.equalsIgnoreCase("asc")) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

		Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);

		Page<Permission> permissions = this.permissionRepository.findAll(pageable);
		List<PermissionDto> permissionDtos = permissions.stream()
				.map((per) -> this.modelMapper.map(per, PermissionDto.class)).collect(Collectors.toList());

		List<Permission> allPages = permissions.getContent();

		PermissionResponce permissionResponce = new PermissionResponce();
		permissionResponce.setPermissions(allPages);
		permissionResponce.setPageNumber(permissions.getNumber());
		permissionResponce.setPageSize(permissions.getSize());
		permissionResponce.setTotalElements(permissions.getTotalElements());
		permissionResponce.setTotalPages(permissions.getTotalPages());
		permissionResponce.setLastpage(permissions.isLast());

		return permissionDtos;
	}

	@Override
	public PermissionDto updatePermission(PermissionDto permissionDto, String permissionId) {

		Permission permission = permissionRepository.findById(permissionId)
				.orElseThrow(() -> new ResourceNotFoundException("Permission is Not", "PerId", permissionId));

		permission.setType(permissionDto.getType());
		permission.setName(permissionDto.getName());
		permission.setStatus(permissionDto.isStatus());

		Permission updatePermission = this.permissionRepository.save(permission);

		return this.modelMapper.map(updatePermission, PermissionDto.class);
	}

	@Override
	public void deletedPermission(String permissionId) {

		permissionRepository.findById(permissionId)
				.orElseThrow(() -> new ResourceNotFoundException("Permission is Not", "PermissionId", permissionId));

		this.permissionRepository.deleteById(permissionId);

	}

}
