package com.nts.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
import com.nts.model.dto.ProjectsDto;
import com.nts.model.entity.Projects;
import com.nts.model.response.PaginationResponse;
import com.nts.repository.ProjectsRepository;
import com.nts.service.ProjectsService;

@Service
public class ProjectsServiceImpl implements ProjectsService {

	private static final Logger logger = LoggerFactory.getLogger(ProjectsServiceImpl.class);

	@Autowired
	private ProjectsRepository projectsRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return new User("admin", "password", new ArrayList<>());
	}

	@Override
	public ProjectsDto createProjects(ProjectsDto projectsDto) {

		logger.debug("ProjectsServiceImpl | Create Employee Invoked...");
		if (projectsDto.getProjectId() != null) {
			throw new ResourceNotFoundException("Projects", "Id", projectsDto.getProjectId());

		}

		Projects projects = this.modelMapper.map(projectsDto, Projects.class);
		Projects save = projectsRepository.save(projects);

		return this.modelMapper.map(save, ProjectsDto.class);

	}

	@Override
	public ProjectsDto getProjectsById(String proId) {

		Projects projects = projectsRepository.findById(proId)
				.orElseThrow(() -> new ResourceNotFoundException("Project is NOT", "Id", proId));

		return this.modelMapper.map(projects, ProjectsDto.class);

	}

	public PaginationResponse getAllProjects(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {

		Sort sort = null;
		if (sortDir.equalsIgnoreCase("asc")) {
			sort = Sort.by(sortBy).ascending();
		} else {
			sort = Sort.by(sortBy).descending();
		}

		Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
		Page<Projects> projects = projectsRepository.findAll(pageable);
		List<Object> projectsDtos = projects.stream().map((pro) -> this.modelMapper.map(pro, ProjectsDto.class))
				.collect(Collectors.toList());
		List<Projects> allProjects = projects.getContent();

		PaginationResponse paginationResponse = new PaginationResponse();
		paginationResponse.setContent(projectsDtos);
		paginationResponse.setPageNumber(projects.getNumber());
		paginationResponse.setPageSize(projects.getSize());
		paginationResponse.setTotalElement(projects.getTotalElements());
		paginationResponse.setTotalPage(projects.getTotalPages());
		paginationResponse.setLastPage(projects.isLast());

		return paginationResponse;

	}

	@Override
	public ProjectsDto updateProjects(String proId, ProjectsDto projectsDto) {
		Projects project = projectsRepository.findById(proId)
				.orElseThrow(() -> new ResourceNotFoundException("Project is NOT", "Id", proId));

		project.setName(projectsDto.getName());
		project.setTask(projectsDto.getTask());
		project.setStatus(projectsDto.getStatus());
		project.setRoles(projectsDto.getRoles());
		project.setAdmin(projectsDto.getAdmin());
		project.setManager(projectsDto.getManager());
		project.setStartDate(projectsDto.getStartDate());
		project.setEndDate(projectsDto.getEndDate());
		Projects save = projectsRepository.save(project);
		return this.modelMapper.map(project, ProjectsDto.class);

	}

	@Override
	public void deleteProjectsById(String proId) {

		Projects projects = new Projects();
		projectsRepository.findById(proId)
				.orElseThrow(() -> new ResourceNotFoundException("Project is NOT ", "Id", proId));
		this.projectsRepository.deleteById(proId);

	}

}
