package com.nts.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.nts.model.entity.Client;

@Repository
public interface ClientRepository extends MongoRepository<Client, String> {

}
