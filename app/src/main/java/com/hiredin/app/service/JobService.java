package com.hiredin.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.hiredin.app.model.Job;
import com.hiredin.app.repository.JobRepository;
import com.hiredin.app.repository.SearchRepository;

@Service
public class JobService {
	
	@Autowired
	JobRepository jobRepo;
	
	@Autowired
	SearchRepository searchRepo;
	
	public List<Job> fetchJobs(){
		return jobRepo.findAll();
	}
	
	public Boolean newJob(Job job) {
		try {
			jobRepo.save(job);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public List<Job> searchJob(String text){
		return searchRepo.searchByText(text);
	}
	
}
