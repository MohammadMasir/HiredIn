package com.hiredin.app.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.hiredin.app.model.User;

public interface UserRepository extends MongoRepository<User, String> {
//    boolean existsByEmail(String email);
//
//	Optional<User> findByUsername(String username);
}
