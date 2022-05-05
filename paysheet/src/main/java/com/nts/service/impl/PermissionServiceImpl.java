package com.nts.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.nts.model.dto.PermissionDto;
import com.nts.model.entity.Employee;
import com.nts.model.entity.Permission;
import com.nts.model.response.PermissionResponce;
import com.nts.repository.EmployeeRepository;
import com.nts.repository.PermissionRepository;
import com.nts.service.PermissionService;

@Service
public class PermissionServiceImpl implements PermissionService {

	@Autowired
	private PermissionRepository permissionRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub

		return new User("admin", "password", new ArrayList<>());
//		return null;
	}

	@Override
	public PermissionResponce createPermission(Permission permission) {
		// TODO Auto-generated method stub

		permissionRepository.save(permission);
		PermissionResponce permissionResponce = new PermissionResponce();
		PermissionDto permissionDto = new PermissionDto();
		permissionDto.setPerId(permission.getPerId());
		permissionDto.setName(permission.getName());
		permissionDto.setType(permission.getType());
		permissionDto.setStatus(permission.getStatus());
		permissionResponce.setPermissionDto(permissionDto);
		return permissionResponce;
	}

	@Override
	public PermissionResponce getPermissionById(String perId) {
		// TODO Auto-generated method stub

		Permission permission = permissionRepository.findById(perId)
				.orElseThrow(() -> new RuntimeException(String.format("Can not find Employee By Id", perId)));
//		 Permission permission=new Permission();
		PermissionResponce permissionResponce = new PermissionResponce();
		PermissionDto permissionDto = new PermissionDto();

		permissionDto.setPerId(permission.getPerId());
		permissionDto.setName(permission.getName());
		permissionDto.setType(permission.getType());
		permissionDto.setStatus(permission.getStatus());

		permissionResponce.setPermissionDto(permissionDto);
		return permissionResponce;

	}

	@Override
	public List<Permission> getListOfPermission() {
		// TODO Auto-generated method stub
		return permissionRepository.findAll();
	}

	@Override
	public PermissionResponce deletePermissionById(String perId) {
		// TODO Auto-generated method stub

		permissionRepository.deleteById(perId);
		Permission permission = new Permission();

		PermissionDto permissionDto = new PermissionDto();
		permissionDto.setPerId(permission.getPerId());
		permissionDto.setName(permission.getName());
		permissionDto.setType(permission.getType());
		permissionDto.setStatus(permission.getStatus());
		PermissionResponce permissionResponce = new PermissionResponce();
		permissionResponce.setPermissionDto(permissionDto);

		return permissionResponce;
	}

	@Override
	public PermissionResponce updatepermission(String perId, Permission permission) {
		// TODO Auto-generated method stub

		permission.setPerId(perId);
		permissionRepository.save(permission);

		PermissionDto permissionDto = new PermissionDto();
		permissionDto.setPerId(permission.getPerId());
		permissionDto.setName(permission.getName());
		permissionDto.setType(permission.getType());
		permissionDto.setStatus(permission.getStatus());
		PermissionResponce permissionResponce = new PermissionResponce();
		permissionResponce.setPermissionDto(permissionDto);

		return permissionResponce;
	}

	@Override
	public ResponseEntity<Object> getPermissionDetails(String permissionId) {
		if (null == permissionId && StringUtils.isEmpty(permissionId)) {
			// return new ResponseEntity(getListOfPermission(),)
		} else {

		}
		return null;
	}

}
