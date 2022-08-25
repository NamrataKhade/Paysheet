package com.nts.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.nts.model.entity.Projects;

@Repository
public interface ProjectsRepository extends MongoRepository<Projects, String> {

	public List<Projects> findByRolesUsers(String username);

}
