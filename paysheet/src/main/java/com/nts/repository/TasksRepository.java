package com.nts.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.nts.model.entity.Tasks;

public interface TasksRepository extends MongoRepository<Tasks, String> {
}
