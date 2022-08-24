package com.nts;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Optional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.nts.model.entity.Employee;
import com.nts.repository.EmployeeRepository;

@SpringBootTest
public class EmployeeTest {

	@Autowired
	EmployeeRepository employeeRepository;

	@Test
	void createEmployee() {
		try {
			Employee employee = new Employee();
			employee.setEmpId("101");
			employee.setFirstName("ASHISH");
			employee.setMiddleName("Bapu");
			employee.setLastName("Boalke");
			employee.setGender("Male");
			employee.setEmail("@gmail.com");
			employee.setStatus("Devloper");
			employee.setReportingManager("K");
			employee.setMobileNumber("122334455");
			employeeRepository.save(employee);
			Employee savedEmployee = employeeRepository.findById("101").get();
			Assertions.assertThat(savedEmployee.getEmpId()).isNotEmpty();
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
	}

	@Test
	void getAllEmployees() {
		List<Employee> findAll = employeeRepository.findAll();
		Assertions.assertThat(findAll.size()).isGreaterThan(0);
	}

	@Test
	void updateEmployee() {
		Employee employee = this.employeeRepository.findById("101").get();
		employee.setFirstName("Akshay");
		employee.setEmail("akshay@gmail.com");
		employeeRepository.save(employee);
		Employee updatedName = employeeRepository.save(employee);
		assertAll("Same name",()->assertEquals("Akshay", updatedName.getFirstName()),
				()->assertEquals("akshay@gmail.com",updatedName.getEmail()));
	}

	@Test
	void deleteEmployee() {
		Employee employee = this.employeeRepository.findById("101").get();
		employeeRepository.delete(employee);
		Employee deletedEmployee = null;

		Optional<Employee> findById = employeeRepository.findById("101");
		if (findById.isPresent()) {
			deletedEmployee = findById.get();
		}
		Assertions.assertThat(deletedEmployee).isNull();
	}

}
