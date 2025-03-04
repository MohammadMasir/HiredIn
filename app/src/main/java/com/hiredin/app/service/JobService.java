package com.hiredin.app.service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bson.Document;
import org.bson.types.ObjectId;
//import org.springdoc.core.converters.models.Sort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import com.hiredin.app.model.Job;
import com.hiredin.app.model.Location;
import com.hiredin.app.model.SalaryRange;
import com.hiredin.app.model.enums.JobStatus;
import com.hiredin.app.repository.JobRepository;
import com.hiredin.app.repository.SearchRepository;

@Service
public class JobService {
	
	@Autowired
	JobRepository jobRepo;
	
	@Autowired
	SearchRepository searchRepo;
	
	@Autowired
    MongoTemplate mongoTemplate;
	
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
	
//	@GetMapping("/stats")
//	@Operation(summary = "Get job statistics", description = "Retrieves statistics about job postings")
	public Map<String, Object> getJobStats() {

		AggregationResults<Document> results = mongoTemplate.aggregate(
	        Aggregation.newAggregation(
	            Aggregation.match(Criteria.where("status").is(JobStatus.OPEN)),
	            Aggregation.group("location")
	                .count().as("count")
	                .avg("salary.minimum").as("averageMinimumSalary"),
	            Aggregation.sort(Sort.Direction.DESC, "count")
	        ),
	        Job.class,
	        Document.class
	    );
	    
	    // Process the results and create a response map
	    List<Document> statsList = results.getMappedResults();
	    Map<String, Object> response = new HashMap<>();
	    response.put("locationStats", statsList);
	    response.put("totalOpenJobs", jobRepo.countByStatus(JobStatus.OPEN));
	    
	    return response;
	}
	
}
