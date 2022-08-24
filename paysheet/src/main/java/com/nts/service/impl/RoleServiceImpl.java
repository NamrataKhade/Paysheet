package com.nts.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.nts.exception.ResourceNotFoundException;
import com.nts.model.dto.EmployeeDto;
import com.nts.model.dto.RoleDto;
import com.nts.model.entity.Employee;
import com.nts.model.entity.Role;
import com.nts.repository.RoleRepository;
import com.nts.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

	private static final Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);

	@Autowired
	private RoleRepository rolesRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return null;
	}

	@Override
	public RoleDto createRole(Role role) {
		logger.debug("RolesServiceImpl: createRole: Create a Role");
		rolesRepository.save(role);
		return entityToModelMapping(role);
	}

	@Override
	public RoleDto getRoleById(String roleId) {
		logger.debug("RolesServiceImpl: getRoleById: Get Role by Id");
		Optional<Role> findRoleById = rolesRepository.findById(roleId);
		if (findRoleById.isPresent()) {
			Role role = findRoleById.get();
			return entityToModelMapping(role);
		} else {
			throw new ResourceNotFoundException("Role", "Id", roleId);
		}
	}

	@Override
	public RoleDto getRoleByName(String roleName) {
		logger.debug("RolesServiceImpl: getRoleByName: Get Role by Name");
		Optional<Role> findRoleByName = rolesRepository.findByRoleName(roleName);
		if (findRoleByName.isPresent()) {
			Role role = findRoleByName.get();
			return entityToModelMapping(role);
		} else {
			throw new ResourceNotFoundException("Role", "Name", roleName);
		}
	}

	@Override
	public Map<String, Object> getAllRoles(int pageNumber, int pageSize, String sortBy) {
		logger.debug("RolesServiceImpl: getAllRoles: Get All Roles");
		Map<String, Object> response = new HashMap<>();
		Sort sort = Sort.by(sortBy);
		Pageable page = PageRequest.of(pageNumber, pageSize, sort);
		Page<Role> roleInPage = rolesRepository.findAll(page);
		response.put("data", roleInPage.getContent());
		response.put("Total no of page", roleInPage.getTotalPages());
		response.put("Total no of elements", roleInPage.getTotalElements());
		response.put("current page no", roleInPage.getNumber());
		return response;
	}

	
	@Override
	public RoleDto updateRole(RoleDto roleDto, String roleId) {
		logger.debug("RolesServiceImpl: updateRole: Update a Role");
		Role role = this.rolesRepository.findById(roleId)
				.orElseThrow(() -> new ResourceNotFoundException("Role", "Id", roleId));
		role.setRoleName(roleDto.getRoleName());
		role.setStatus(roleDto.getStatus());
		role.setPermission(roleDto.getPermission());		
		Role updatedRole = this.rolesRepository.save(role);
		RoleDto roleToDto = this.entityToModelMapping(updatedRole);
		return roleToDto;
	}

	@Override
	public void deleteRole(String roleId) {
		logger.debug("RolesServiceImpl: deleteRole: Delete a Role");
		Optional<Role> findRoleById = rolesRepository.findById(roleId);
		if (findRoleById.isPresent()) {
			Role role = findRoleById.get();
			rolesRepository.delete(role);
		} else {
			throw new ResourceNotFoundException("Role", "Id", roleId);
		}
	}

	public RoleDto entityToModelMapping(Role role) {
		return this.modelMapper.map(role, RoleDto.class);
	}
}
