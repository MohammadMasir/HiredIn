package com.hiredin.app.model;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "jobs")
public class Job {
	
	@Id
	private String id;
	private String employerId;
	private String title;
	private String company;
	private String location;
	private JobType type;
	private String description;
	private List<String> requirements;
	private SalaryRange salary;
	private Date applicationDeadline;
	private JobStatus status;
	private Date createdAt;
	private Date updatedAt;
	
	public Job() {
		
	}
	
	public String getEmployerId() {
		return employerId;
	}
	public void setEmployerId(String employerId) {
		this.employerId = employerId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public JobType getType() {
		return type;
	}
	public void setType(JobType type) {
		this.type = type;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<String> getRequirements() {
		return requirements;
	}
	public void setRequirements(List<String> requirements) {
		this.requirements = requirements;
	}
	public SalaryRange getSalary() {
		return salary;
	}
	public void setSalary(SalaryRange salary) {
		this.salary = salary;
	}
	public Date getApplicationDeadline() {
		return applicationDeadline;
	}
	public void setApplicationDeadline(Date applicationDeadline) {
		this.applicationDeadline = applicationDeadline;
	}
	public JobStatus getStatus() {
		return status;
	}
	public void setStatus(JobStatus status) {
		this.status = status;
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
	
	public enum JobStatus {
		 OPEN, FILLED, EXPIRED
		}

	public enum JobType {
		 FULL_TIME, PART_TIME, CONTRACT, REMOTE
	}

	@Override
	public String toString() {
		return "Job [id=" + id + ", employerId=" + employerId + ", title=" + title + ", company=" + company
				+ ", location=" + location + ", type=" + type + ", description=" + description + ", requirements="
				+ requirements + ", salary=" + salary + ", applicationDeadline=" + applicationDeadline + ", status="
				+ status + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
	}
	
}


