package com.nts.service;

import java.util.Map;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.nts.model.dto.RoleDto;
import com.nts.model.entity.Role;

public interface RoleService extends UserDetailsService {

	public RoleDto createRole(Role role);
	public RoleDto getRoleById(String roleId);
	public RoleDto getRoleByName(String roleName);
	public Map<String, Object> getAllRoles(int pageNo, int pageSize, String sortBy);
	public RoleDto updateRole(RoleDto roleDto, String roleId);
	public void deleteRole(String roleId);
}
