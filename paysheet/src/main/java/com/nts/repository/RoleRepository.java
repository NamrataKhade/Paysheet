package com.nts.repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.nts.model.entity.Role;

@Repository
public interface RoleRepository extends MongoRepository<Role, String> {

	public Optional<Role> findByRoleName(String roleName);

	public List<Role> findByRoleNameIn(Set<String> roles);

}
