package com.hiredin.app.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hiredin.app.model.Job;
import com.hiredin.app.service.JobService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/jobs")
@Tag(name="Jobs Management", description="Endpoints for managing Jobs")
public class JobController {
    
	@Autowired
	JobService jobService;
	
    private static final Logger logger = LoggerFactory.getLogger(JobController.class);
    
    @GetMapping
    public ResponseEntity<?> getJobs() {
        try {
            logger.info("Attempting to fetch all jobs");
            List<Job> jobs = jobService.fetchJobs();
            logger.info("Successfully retrieved {} jobs", jobs.size());
            return ResponseEntity.ok(jobs);
        } catch (Exception e) {
            logger.error("Error retrieving jobs", e);
            return ResponseEntity
                .internalServerError()
                .body("Error retrieving jobs: " + e.getMessage());
        }
    }
    
    @PostMapping
    public ResponseEntity<?> hire(@RequestBody Job job){
    	if (jobService.newJob(job)) {
    		return new ResponseEntity<>(HttpStatus.CREATED);
    	}
    	return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    
    @GetMapping("/{text}")
    public ResponseEntity<List<Job>> search(@PathVariable String text){
    	try {
			if (jobService.searchJob(text).isEmpty()) {
				return new ResponseEntity<List<Job>>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<List<Job>>(jobService.searchJob(text),HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<List<Job>>(HttpStatus.BAD_REQUEST);
		}
    }
    
}