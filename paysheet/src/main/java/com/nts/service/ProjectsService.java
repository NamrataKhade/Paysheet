package com.nts.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nts.model.dto.ProjectsDto;
import com.nts.model.entity.Projects;
import com.nts.model.response.PaginationResponse;

@Service
public interface ProjectsService {

	public ProjectsDto createProjects(ProjectsDto projectsDto);

	public ProjectsDto getProjectsById(String proId);

	public PaginationResponse getAllProjects(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);

	public ProjectsDto updateProjects(String proId, ProjectsDto projectsDto);

	public void deleteProjectsById(String proId);

	public List<Projects> findAllByUserName(String username);

}
