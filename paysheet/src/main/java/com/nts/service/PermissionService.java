package com.nts.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import com.nts.model.entity.Permission;
import com.nts.model.response.PermissionResponce;

@Service
public interface PermissionService extends UserDetailsService {

	public PermissionResponce createPermission(Permission permission);

	public PermissionResponce getPermissionById(String perId);

	public List<Permission> getListOfPermission();

	public PermissionResponce updatePermission(Permission permission);

	public void deletedPermission(String permissionId);

}
