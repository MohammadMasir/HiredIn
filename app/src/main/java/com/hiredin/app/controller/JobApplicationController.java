package com.hiredin.app.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.hiredin.app.model.JobApplication;
import com.hiredin.app.repository.FileServiceInterface;
import com.hiredin.app.service.JobApplicationService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/jobApplication/")
@Tag(name="Applications Management", description="Endpoints for managing job applications")
public class JobApplicationController {
	
	private final JobApplicationService jobAppService;
	private final FileServiceInterface fileService;
	
    @Autowired
    public JobApplicationController(JobApplicationService jobAppService, FileServiceInterface fileService) {
    	this.jobAppService = jobAppService;
        this.fileService = fileService;
    }
	
	@PostMapping
    @Operation(summary = "Creates a Job Application", description = "Creating a Job Application")
	public ResponseEntity<?> application(@RequestBody JobApplication jobApplication){
		
		if (jobAppService.newApplication(jobApplication)) {
				return new ResponseEntity<>(HttpStatus.CREATED);	
		} 
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping
    @Operation(summary = "Shows Every Job Applications", description = "Shows Every Job Application that are stored in the Database.")
	public ResponseEntity<?> allApplications(){
		try {
			List<JobApplication> ja = jobAppService.getApp();
			return new ResponseEntity<>(ja,HttpStatus.OK);
		}
		catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND); 
		}
	}
	
	@GetMapping("/applicant/{id}")
    @Operation(summary = "Shows Job Applications for User", description = "Shows all the Job Applications applied by a particular User")
	public ResponseEntity<?> getByApplicantId(@PathVariable String id) {
		try {
			List<JobApplication> ja = jobAppService.findById(id);
			return new ResponseEntity<>(ja, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/{id}")
    @Operation(summary = "Shows a single Job Application.", description = "Shows all the Job Applications from their ID using Indexing.")
	public ResponseEntity<JobApplication> getById(@PathVariable String id) {
		try {
			JobApplication ja = jobAppService.getJobAppById(id);
			return new ResponseEntity<JobApplication>(ja, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete's a JobApplication")
    public ResponseEntity<?> deleteJob(String id){
    	try {
			jobAppService.deleteUser(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT); 
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
    }
	
	
    @PostMapping(
    		value = "{id}/resume",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Upload Resume", description = "Upload a Resume for a Job Application")
    public ResponseEntity<String> upload(
        @PathVariable String id,
        @Parameter(
                description = "Resume to upload",
                required = true,
                content = @Content(mediaType = MediaType.MULTIPART_FORM_DATA_VALUE)
            )
            @RequestParam("file") MultipartFile file
    ) {      
        try {
            
        	JobApplication ja = jobAppService.getJobAppById(id);
        	
        	String resumeUrl = fileService.storeFile(file);
            ja.getResume().setUrl(resumeUrl);
            
            jobAppService.updateApplication(id, ja);
            return ResponseEntity.ok("Resume uploaded successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Failed to upload Resume.");
        }
    }
}
