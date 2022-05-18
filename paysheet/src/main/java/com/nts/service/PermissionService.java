package com.nts.service;



import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import com.nts.model.entity.Permission;
import com.nts.model.response.PermissionResponce;

@Service
public interface PermissionService extends UserDetailsService {

	public PermissionResponce createPermission(Permission permission);

	public PermissionResponce getPermissionById(String permissionId);

	public PermissionResponce getListOfPermission(Integer pageNumber,Integer pageSize,String sortBy, String sortDir);

	public PermissionResponce updatePermission(Permission permission);
	
	public PermissionResponce updatePermission(Permission permission, String permissionId);

	public void deletedPermission(String permissionId);
	
	
	
	
}
