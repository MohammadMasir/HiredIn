package com.hiredin.app.service;

import java.time.LocalDateTime;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hiredin.app.model.Job;
import com.hiredin.app.model.JobApplication;
import com.hiredin.app.model.JobApplication.ApplicantSnapshot;
import com.hiredin.app.model.JobApplication.ApplicationStatus;
import com.hiredin.app.model.JobApplication.JobSnapshot;
import com.hiredin.app.model.User;
//import com.hiredin.app.model.enums.ApplicationStatus;
import com.hiredin.app.repository.JobApplicationRepository;
import com.hiredin.app.repository.JobRepository;
import com.hiredin.app.repository.UserRepository;

@Service
public class JobApplicationService {
	
	@Autowired
	JobRepository jobRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	private JobApplicationRepository jobAppRepo;
	
	public Boolean newApplication(String jobId, String applicantId, String coverLetter) {
        // Create a new job application
        JobApplication application = new JobApplication();
        application.setJobId(new ObjectId(jobId));
        application.setApplicantId(new ObjectId(applicantId));
        application.setCoverLetter(coverLetter);
        application.setStatus(ApplicationStatus.REVIEWING);
        application.setAppliedDate(LocalDateTime.now());
        application.setUpdatedAt(LocalDateTime.now());
        
        // Fetch job and applicant details to create snapshots
        Job job = jobRepository.findById(new ObjectId(jobId))
            .orElseThrow(() -> new RuntimeException("Job not found"));
            
        User applicant = userRepository.findById(new ObjectId(applicantId))
            .orElseThrow(() -> new RuntimeException("Applicant not found"));
        
        // Create job snapshot
        JobSnapshot jobSnapshot = new JobSnapshot();
        jobSnapshot.setTitle(job.getTitle());
        jobSnapshot.setCompany(job.getCompany().getName());
        application.setJobSnapshot(jobSnapshot);
        
        // Create applicant snapshot
        ApplicantSnapshot applicantSnapshot = new ApplicantSnapshot();
        applicantSnapshot.setName(applicant.getFirstName() + " " + applicant.getLastName());
        applicantSnapshot.setEmail(applicant.getEmail());
        // Populate other fields as available
        application.setApplicantSnapshot(applicantSnapshot);
        
        // Save and return the application
         jobAppRepo.save(application);

         return true;
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
				jApplication.setResume(updjobApplication.getResume());
				jobAppRepo.save(jApplication);
				return true;
			}
		} 
		catch (Exception e) {
			return false;
		}
		return false;
	}
	
	public void deleteUser(String id) {
		ObjectId bid = new ObjectId(id);
		jobAppRepo.deleteById(bid);
	}
	
}
