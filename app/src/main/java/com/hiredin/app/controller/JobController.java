package com.hiredin.app.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hiredin.app.model.Job;
import com.hiredin.app.service.JobService;

import io.swagger.v3.oas.annotations.Hidden;
import jakarta.servlet.http.HttpServletResponse;

@RestController
public class JobController {
    
	@Autowired
	JobService jobService;
	
	
    @Hidden
    @RequestMapping("/")
    public void redirect(HttpServletResponse response) throws IOException {
        response.sendRedirect("/swagger-ui.html");
    }
    
    @GetMapping("/jobs")
    public List<Job> getJobs(){
    	return jobService.fetchJobs();
    }
    
//    public ResponseEntity<?> getJobs() {
//        try {
//            logger.info("Attempting to fetch all jobs");
//            List<Job> jobs = jobrepo.findAll();
//            logger.info("Successfully retrieved {} jobs", jobs.size());
//            return ResponseEntity.ok(jobs);
//        } catch (Exception e) {
//            logger.error("Error retrieving jobs", e);
//            return ResponseEntity
//                .internalServerError()
//                .body("Error retrieving jobs: " + e.getMessage());
//        }
//    }
    
}