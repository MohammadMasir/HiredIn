package com.hiredin.app.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.hiredin.app.model.Job;
import com.hiredin.app.model.enums.JobStatus;

public interface JobRepository extends MongoRepository<Job, ObjectId> {
	Job getJobById(String id);

	int countByStatus(JobStatus status);
}
