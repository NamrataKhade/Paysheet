package com.nts;

import java.util.List;

import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.nts.model.entity.Permission;
import com.nts.repository.PermissionRepository;

@SpringBootTest
class PaysheetApplicationTests {

	@Autowired
	private PermissionRepository permissionRepository;
	@Test
	void contextLoads() {
		
	}
	@Test
	public void getAllPermission() {
		List<Permission> findAll = permissionRepository.findAll();
		Assertions.assertThat(findAll.size()).isGreaterThan(0);
	}
	
}
