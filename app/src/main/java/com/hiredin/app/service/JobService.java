package com.hiredin.app.service;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import java.time.LocalDateTime;
import com.hiredin.app.model.Job;
import com.hiredin.app.model.Location;
import com.hiredin.app.model.SalaryRange;
import com.hiredin.app.model.User;
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
	
	public void deleteUser(String id) {
		ObjectId bid = new ObjectId(id);
		jobRepo.deleteById(bid);
	}

	public Boolean updateJob(String id, Job updatedJob) {
	    Job oldJob = jobRepo.getJobById(id);
	    if (oldJob != null) {
	        oldJob.setTitle(updatedJob.getTitle() != null && !updatedJob.getTitle().isEmpty() ? updatedJob.getTitle() : oldJob.getTitle());
	        oldJob.setDescription(updatedJob.getDescription() != null && !updatedJob.getDescription().isEmpty() ? updatedJob.getDescription() : oldJob.getDescription());

	        // Update Location (only if updatedLocation is not null)
	        if (updatedJob.getLocation() != null) {
	            if (oldJob.getLocation() == null) {
	                oldJob.setLocation(new Location()); // Initialize if null
	            }
	            if (updatedJob.getLocation().getCity() != null && !updatedJob.getLocation().getCity().isEmpty()) {
	                oldJob.getLocation().setCity(updatedJob.getLocation().getCity());
	            }
	            if (updatedJob.getLocation().getRemote() != null) {
	                oldJob.getLocation().setRemote(updatedJob.getLocation().getRemote());
	            }
	        }

	        // Update SalaryRange (only if updatedSalary is not null)
	        if (updatedJob.getSalary() != null) {
	            if (oldJob.getSalary() == null) {
	                oldJob.setSalary(new SalaryRange()); // Initialize if null
	            }
	            if (updatedJob.getSalary().getMinimum() != null) {
	                oldJob.getSalary().setMinimum(updatedJob.getSalary().getMinimum());
	            }
	            if (updatedJob.getSalary().getMaximum() != null) {
	                oldJob.getSalary().setMaximum(updatedJob.getSalary().getMaximum());
	            }
	            if (updatedJob.getSalary().getCurrency() != null && !updatedJob.getSalary().getCurrency().isEmpty()) {
	                oldJob.getSalary().setCurrency(updatedJob.getSalary().getCurrency());
	            }
	        }

	        // Update Requirements (replace the entire list)
	        if (updatedJob.getRequirements() != null) {
	            oldJob.setRequirements(updatedJob.getRequirements());
	        }

	        oldJob.setCompany(updatedJob.getCompany() != null && !updatedJob.getCompany().toString().equals("") ? updatedJob.getCompany() : oldJob.getCompany());
	        oldJob.setApplicationDeadline(updatedJob.getApplicationDeadline() != null ? updatedJob.getApplicationDeadline() : oldJob.getApplicationDeadline());
	        oldJob.setStatus(updatedJob.getStatus() != null ? updatedJob.getStatus() : oldJob.getStatus());
	        oldJob.setType(updatedJob.getType() != null ? updatedJob.getType() : oldJob.getType());
	        oldJob.setUpdatedAt(LocalDateTime.now());
	        jobRepo.save(oldJob);
	        return true;
	    }
	    return false;
	}
	
}
