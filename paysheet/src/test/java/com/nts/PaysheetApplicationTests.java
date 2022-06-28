package com.nts;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.nts.model.entity.Projects;
import com.nts.repository.ProjectsRepository;

@SpringBootTest
class PaysheetApplicationTests {

	@Test
	void contextLoads() {
	}
	
	@Autowired
	private ProjectsRepository projectsRepository;

	@Test
	public void getAllProjectsTest() {
		List<Projects> projects = projectsRepository.findAll();
		Assertions.assertThat(projects.size()).isGreaterThan(0);

	}

	@Test
	public void getPorjectTest() {
		Projects projects = projectsRepository.findById("111").get();
		Assertions.assertThat(projects.getProjectId()).isEqualTo("111");

	}

	@Test
	public void createProjectTest() {
		Projects projects = new Projects();
		projects.setProjectId("111");
		projects.setName("JUNIT Test");
		projects.setTask("abc");
		projects.setAdmin("junit");
		projects.setManager("junit");
		projects.setRoles("junit");
		projectsRepository.save(projects);
		Projects savedProjects = projectsRepository.findById("111").get();
		assertNotNull(savedProjects);
	}

	@Test
	public void updateProjectTest() {
		Projects projects = projectsRepository.findById("627a14f0172af51cc6e54879").get();
		projects.setName("JUNIT 777");
		Projects projectUpdated = projectsRepository.save(projects);
		Assertions.assertThat(projectUpdated.getName()).isEqualTo("JUNIT 777");
	}


}
