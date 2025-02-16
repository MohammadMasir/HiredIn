package com.hiredin.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hiredin.app.model.Job;
import com.hiredin.app.repository.JobRepository;

@Service
public class JobService {
	
	@Autowired
	JobRepository jobRepo;
	
	public List<Job> fetchJobs(){
		return jobRepo.findAll();
	}
	
}
