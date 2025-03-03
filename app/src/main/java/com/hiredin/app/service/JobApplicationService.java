package com.hiredin.app.service;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hiredin.app.model.JobApplication;
import com.hiredin.app.repository.JobApplicationRepository;

@Service
public class JobApplicationService {
	
	@Autowired
	private JobApplicationRepository jobAppRepo;
	
	public Boolean newApplication(JobApplication jobApplication) {
		try {
			jobAppRepo.save(jobApplication);
			System.out.println("Here IT IS.....");
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public List<JobApplication> getApp(){
		try {
			List<JobApplication> result = jobAppRepo.findAll();
			return result;
		} catch (Exception e) {
			return null;
		}
	}
	
	public List<JobApplication> findById(String id){
		ObjectId obId = new ObjectId(id);
		try {
			List<JobApplication> ja = jobAppRepo.findByApplicantId(id);
			return ja;
		} catch (Exception e) {
			return null;
		}
	}
	
	public JobApplication getJobAppById(String id){
		ObjectId obId = new ObjectId(id);
		try {
			JobApplication ja = jobAppRepo.findById(obId).orElse(null);
			return ja;
		} catch (Exception e) {
			return null;
		}
	}
	
	public Boolean updateApplication(String id, JobApplication updjobApplication) {
		try {
			JobApplication jApplication = getJobAppById(id);
			if(jApplication != null) {
				jApplication.getResume().setUrl(updjobApplication.getResume().getUrl());
				jobAppRepo.save(jApplication);
				return true;
			}
		} 
		catch (Exception e) {
			return false;
		}
		return false;
	}
	
}
