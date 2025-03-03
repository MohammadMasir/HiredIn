package com.hiredin.app.repository;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.hiredin.app.model.JobApplication;

public interface JobApplicationRepository extends MongoRepository<JobApplication, ObjectId> {
    List<JobApplication> findByApplicantId(String applicantId);
    List<JobApplication> findByJobId(String jobId);
}
