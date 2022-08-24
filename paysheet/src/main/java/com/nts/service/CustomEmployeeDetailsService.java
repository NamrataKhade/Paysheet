package com.nts.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.nts.exception.ResourceNotFoundException;
import com.nts.model.EmployeeDetailsImpl;
import com.nts.model.ProjectRoles;
import com.nts.model.RolesAndPermisssions;
import com.nts.model.entity.Employee;
import com.nts.model.entity.Projects;
import com.nts.model.entity.Role;
import com.nts.repository.EmployeeRepository;
import com.nts.repository.RoleRepository;

@Service
public class CustomEmployeeDetailsService implements UserDetailsService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private ProjectsService projectsService;

	@Override
	public UserDetails loadUserByUsername(String username) throws ResourceNotFoundException {

		Employee employee = employeeRepository.findByEmail(username);
		if (null != employee) {
			RolesAndPermisssions rolesAndPermisssions = getRolesAndPermissions(employee.getEmail());
			return EmployeeDetailsImpl.build(employee, rolesAndPermisssions);
		} else {
			throw new ResourceNotFoundException("username '%s'", " not found", username);
		}
	}

	private RolesAndPermisssions getRolesAndPermissions(String username) {

		RolesAndPermisssions rolesAndPermisssions = new RolesAndPermisssions();
		Set<String> roleAssignedToUser = new HashSet<String>();
		Set<String> permissions = new HashSet<String>();

		List<Projects> projects = projectsService.findAllByUserName(username);
		if (CollectionUtils.isEmpty(projects)) {
			return null;
		}

		for (Projects projects2 : projects) {
			List<ProjectRoles> roles = projects2.getRoles();
			for (ProjectRoles projectRoles : roles) {
				if (projectRoles.getUsers().contains(username)) {
					roleAssignedToUser.add(projectRoles.getRole());
				}
			}
		}

		List<Role> role = roleRepository.findByRoleNameIn(roleAssignedToUser);

		for (Role roles : role) {
			permissions.addAll(roles.getPermissions());
		}
		rolesAndPermisssions.setRoles(roleAssignedToUser);
		rolesAndPermisssions.setPermissions(permissions);
		return rolesAndPermisssions;

	}

}
