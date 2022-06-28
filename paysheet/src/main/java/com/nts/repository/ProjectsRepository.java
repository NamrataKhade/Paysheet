package com.nts.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.nts.model.entity.Projects;

@Repository
public interface ProjectsRepository extends MongoRepository<Projects, String> {

}
