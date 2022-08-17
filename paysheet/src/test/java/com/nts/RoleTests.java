package com.nts;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.nts.model.entity.Role;
import com.nts.repository.RoleRepository;

@SpringBootTest
class RoleTests {

	@Autowired
	private RoleRepository roleRepository;

	@Test
	void createProjectTest() {
		Role role = new Role();
		role.setRoleId("100");
		role.setRoleName("Junit");
		role.setStatus("Test");
		role.setPermissions(null);
		roleRepository.save(role);
		Role savedRoles = roleRepository.findById("100").get();
		assertNotNull(savedRoles);
	}

	@Test
	void updateProjectTest() {
		Role role = roleRepository.findById("100").get();
		role.setRoleName("Junit_update");
		Role roleUpdated = roleRepository.save(role);
		Assertions.assertThat(roleUpdated.getRoleName()).isEqualTo("Junit_update");
	}

	@Test
	void deleteProjectTest() {
		Role role = roleRepository.findById("100").get();
		roleRepository.delete(role);

		Role deletedRole = null;
		Optional<Role> findById = roleRepository.findById("100");
		if (findById.isPresent()) {
			deletedRole = findById.get();
		}
		Assertions.assertThat(deletedRole).isNull();
	}
}
