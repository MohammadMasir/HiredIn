package com.hiredin.app.model;

import java.util.Date;

import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "applications")
public class JobApplication {
	
	 @Id
	 private String id;
	 private String jobId;
	 private String applicantId;
	 private ApplicationStatus status;
	 private String resumeUrl; // Cloudinary Service
	 private Binary resumeBinary; // Mongodb Database
	 private String coverLetter;
	 private Date appliedDate;
	 private String employerNotes;
	 private Date createdAt;
	 private Date updatedAt;
	 
	 public JobApplication() {}
	
	public enum ApplicationStatus {
	 PENDING, REVIEWED, SHORTLISTED, REJECTED
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getJobId() {
		return jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	public String getApplicantId() {
		return applicantId;
	}

	public void setApplicantId(String applicantId) {
		this.applicantId = applicantId;
	}

	public ApplicationStatus getStatus() {
		return status;
	}

	public void setStatus(ApplicationStatus status) {
		this.status = status;
	}

	public String getResumeUrl() {
		return resumeUrl;
	}

	public void setResumeUrl(String resumeUrl) {
		this.resumeUrl = resumeUrl;
	}

	public String getCoverLetter() {
		return coverLetter;
	}

	public void setCoverLetter(String coverLetter) {
		this.coverLetter = coverLetter;
	}

	public Date getAppliedDate() {
		return appliedDate;
	}

	public void setAppliedDate(Date appliedDate) {
		this.appliedDate = appliedDate;
	}

	public String getEmployerNotes() {
		return employerNotes;
	}

	public void setEmployerNotes(String employerNotes) {
		this.employerNotes = employerNotes;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

}
