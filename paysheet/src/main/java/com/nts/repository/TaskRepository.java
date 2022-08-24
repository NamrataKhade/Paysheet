package com.nts.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.nts.model.entity.Task;

public interface TaskRepository extends MongoRepository<Task, String> {
}
