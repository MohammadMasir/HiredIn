package com.hiredin.app.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.hiredin.app.model.Job;

public interface JobRepository extends MongoRepository<Job, String> {

}
