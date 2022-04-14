package com.nts.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.nts.model.entity.Employee;

@Repository
public interface EmployeeRepository extends MongoRepository<Employee, String> {

}
