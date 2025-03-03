package com.hiredin.app.model;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "jobs")
public class Job {
	
	@Id 
	private ObjectId id;
	@Indexed
	private String employerId;
	private String title;
	private CompanyDetails company;
	private Location location;
	
	@Indexed
	private JobType type;
	private String description;
	
	@Indexed
	private List<Requirement> requirements;
	private SalaryRange salary;
	
	@Indexed
	private Date applicationDeadline;
	
	@Indexed
	private JobStatus status;
	private List<ApplicationCount> applications;
	
	@Indexed
	private LocalDateTime postedAt;
	private LocalDateTime updatedAt;
	
	public static class ApplicationCount {
        private int count;
        private JobStatus status;
        
        // Getters and setters
        public int getCount() { return count; }
        public void setCount(int count) { this.count = count; }
        public JobStatus getStatus() { return status; }
        public void setStatus(JobStatus status) { this.status = status; }
    }
	
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
	public CompanyDetails getCompany() {
		return company;
	}
	public void setCompany(CompanyDetails company) {
		this.company = company;
	}
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
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
	public List<Requirement> getRequirements() {
		return requirements;
	}
	public void setRequirements(List<Requirement> requirements) {
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
	public LocalDateTime getPostedAt() {
		return postedAt;
	}

	public void setPostedAt(LocalDateTime postedAt) {
		this.postedAt = postedAt;
	}
	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public List<ApplicationCount> getApplications() {
		return applications;
	}

	public void setApplications(List<ApplicationCount> applications) {
		this.applications = applications;
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
				+ status + ", createdAt=" + postedAt + ", updatedAt=" + updatedAt + "]";
	}
}
