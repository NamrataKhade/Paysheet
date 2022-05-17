package com.nts.service.impl;

import java.util.ArrayList;
import java.util.List;


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
		// TODO Auto-generated method stub

		return new User("admin", "password", new ArrayList<>());

	}

//	******************************* CREATE PERMISSION *********************

//	permission-->>permissionDto

	@Override
	public PermissionResponce createPermission(Permission permission) {
		// TODO Auto-generated method stub
		
		logger.debug("PermissionServiceImpl | Create Permission Invoked...");

		if (permission.getPerId() != null) {
			System.out.println("User Is already there");
			throw new ResourceNotFoundException("Permission is alredy", "PerId", permission.getPerId());
		} else {
			permissionRepository.save(permission);
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
				.orElseThrow(() -> new ResourceNotFoundException("Permission is Not", "PerId", perId));

		PermissionResponce permissionResponce = new PermissionResponce();
		PermissionDto permissionDto = this.modelMapper.map(permission, PermissionDto.class);
		permissionResponce.setPermissionDto(permissionDto);
		return permissionResponce;

	}

//	******************************* GET PERMISSION LIST *********************

	
	@Override
	public PermissionResponce getListOfPermission(Integer pageNumber,Integer pageSize,String sortBy,String sortDir) {
		// TODO Auto-generated method stub
//		PageRequest of = PageRequest.of(1, 1, Sort.by(sortBy));
		
//		int pageSixe=5;
//		int pageNo=1;
		
		Sort sort=(sortDir.equalsIgnoreCase("asc"))?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
		
//		if(sortDir.equalsIgnoreCase("asc")) {
//			sort=Sort.by(sortBy).ascending();
//		}
//		else {
//			sort=Sort.by(sortBy).descending();
//		}
		Pageable pageable=PageRequest.of(pageNumber, pageSize,sort);
		
	 Page<Permission> pagePermission = this.permissionRepository.findAll(pageable);
	 
	 List<Permission> allPages = pagePermission.getContent();
//	 List<Object> collect = allPages.stream().map(null).collect(Collectors.toList());
	 
	PermissionResponce permissionResponce=new PermissionResponce();
	permissionResponce.setPermissions(allPages);
	 permissionResponce.setPageNumber(pagePermission.getNumber());
	 permissionResponce.setPageSize(pagePermission.getSize());
	 permissionResponce.setTotalElements(pagePermission.getTotalElements());
	 permissionResponce.setTotalPages(pagePermission.getTotalPages());
	 permissionResponce.setLastpage(pagePermission.isLast());
	 
		return permissionResponce;
	}

//	******************************* UPDATE PERMISSION *********************

	@Override
	public PermissionResponce updatePermission(Permission permission) {

		if(permission.getPerId()!=null) {
			permissionRepository.save(permission);
		}
		else {
			System.out.println("User Is already there");
			throw new ResourceNotFoundException("Permission is Not", "PerId", permission.getPerId());
		}
		

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
				.orElseThrow(() -> new ResourceNotFoundException("Permission is Not", "PerId", permissionId));

		this.permissionRepository.deleteById(permissionId);

	}

	@Override
	public PermissionResponce updatePermission(Permission permission, String permissionId) {
		// TODO Auto-generated method stub
		Permission permission2 = permissionRepository.findById(permissionId).orElseThrow(() -> new ResourceNotFoundException("Permission is Not", "PerId", permissionId));
		
		permission.setPerId(permission2.getPerId());
		permissionRepository.save(permission);
		PermissionDto permissionDto = this.modelMapper.map(permission, PermissionDto.class);
		PermissionResponce permissionResponce = new PermissionResponce();
		permissionResponce.setPermissionDto(permissionDto);

		return permissionResponce;
	}

}
