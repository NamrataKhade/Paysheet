package com.nts.service;

import java.util.List;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import com.nts.model.dto.ProjectsDto;

@Service
public interface ProjectsService extends UserDetailsService {

	public ProjectsDto createProjects(ProjectsDto projectsDto);

	public ProjectsDto getProjectsById(String proId);

	public List<ProjectsDto> getAllProjects(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);

	public ProjectsDto updateProjects(String proId, ProjectsDto projectsDto);

	public void deleteProjectsById(String proId);

}
