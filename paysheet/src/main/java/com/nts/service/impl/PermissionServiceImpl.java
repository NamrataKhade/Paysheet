package com.nts.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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

	@Autowired
	private PermissionRepository permissionRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub

		return new User("admin", "password", new ArrayList<>());

	}
	
//	******************************* CREATE PERMISSION *********************

//	permission-->>permissionDto
	
	@Override
	public PermissionResponce createPermission(Permission permission) {
		// TODO Auto-generated method stub

		Permission save = this.permissionRepository.save(permission);
		if (save != null) {
			System.out.println("User Is already there");
			throw new ResourceNotFoundException(permission.getPerId(), "Permission is alredy", "PerId");
		}

		PermissionResponce permissionResponce = new PermissionResponce();

		PermissionDto permissionDto = this.modelMapper.map(permission, PermissionDto.class);
		permissionResponce.setPermissionDto(permissionDto);
		return permissionResponce;
	}

//	******************************* GET PERMISSION BY ID *********************
	
	@Override
	public PermissionResponce getPermissionById(String perId) {
		// TODO Auto-generated method stub

		Permission permission = permissionRepository.findById(perId)
				.orElseThrow(() -> new ResourceNotFoundException(perId, "Permission is Not", "PerId"));

		PermissionResponce permissionResponce = new PermissionResponce();
		PermissionDto permissionDto = this.modelMapper.map(permission, PermissionDto.class);
		permissionResponce.setPermissionDto(permissionDto);
		return permissionResponce;

	}
	
//	******************************* GET PERMISSION LIST *********************

	@Override
	public List<Permission> getListOfPermission() {
		// TODO Auto-generated method stub
		return permissionRepository.findAll();
	}
	
//	******************************* UPDATE PERMISSION *********************

	@Override
	public PermissionResponce updatePermission(Permission permission) {

		 this.permissionRepository.save(permission);

		PermissionDto permissionDto = this.modelMapper.map(permission, PermissionDto.class);
		PermissionResponce permissionResponce = new PermissionResponce();
		permissionResponce.setPermissionDto(permissionDto);

		return permissionResponce;
	}

//	******************************* DELETE PERMISSION *********************
	
	@Override
	public void deletedPermission(String permissionId) {
		// TODO Auto-generated method stub
		 permissionRepository.findById(permissionId)
				.orElseThrow(() -> new ResourceNotFoundException(permissionId, "Permission is Not", "PerId"));

		this.permissionRepository.deleteById(permissionId);

	}

}
