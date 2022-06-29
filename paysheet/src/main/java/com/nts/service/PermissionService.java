package com.nts.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.nts.model.dto.PermissionDto;
import com.nts.model.response.PermissionResponce;

@Service
public interface PermissionService extends UserDetailsService {

	public PermissionDto createPermission(PermissionDto permissionDto);

	public PermissionDto getPermissionById(String permissionId);

	public PermissionResponce getListOfPermission(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);

	public PermissionDto updatePermission(PermissionDto permissionDto, String permissionId);

	public void deletedPermission(String permissionId);

}