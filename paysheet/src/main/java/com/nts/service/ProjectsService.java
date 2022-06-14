package com.nts.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.nts.model.dto.ProjectsDto;
import com.nts.model.response.PaginationResponse;

@Service
public interface ProjectsService extends UserDetailsService {

	public ProjectsDto createProjects(ProjectsDto projectsDto);

	public ProjectsDto getProjectsById(String proId);

	public PaginationResponse getAllProjects(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);

	public ProjectsDto updateProjects(String proId, ProjectsDto projectsDto);

	public void deleteProjectsById(String proId);

}
