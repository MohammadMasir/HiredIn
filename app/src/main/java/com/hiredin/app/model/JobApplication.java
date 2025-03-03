package com.hiredin.app.model;

import java.util.Date;

import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.index.Indexed;
import java.time.LocalDateTime;
import java.util.List;
import org.bson.types.ObjectId;

@Document(collection = "job_applications")
public class JobApplication {
    @Id
    private ObjectId id;
    
    @Indexed
    private ObjectId jobId;
    
    @Indexed
    private ObjectId applicantId;
    
    private String coverLetter;
    private Resume resume;
    private ApplicantSnapshot applicantSnapshot;
    private JobSnapshot jobSnapshot;
    
    @Indexed
    private ApplicationStatus status;
    
    private List<Note> notes;
    
    @Indexed
    private LocalDateTime appliedDate;
    private LocalDateTime updatedAt;
    
    // Nested classes
    public static class Resume {
        private String url;
        private String gridFsId;
        
        // Getters and setters
        public String getUrl() { return url; }
        public void setUrl(String url) { this.url = url; }
        public String getGridFsId() { return gridFsId; }
        public void setGridFsId(String gridFsId) { this.gridFsId = gridFsId; }
    }
    
    public static class ApplicantSnapshot {
        private String name;
        private String email;
        private String phone;
        private List<String> skills;
        
        // Getters and setters
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
        public String getPhone() { return phone; }
        public void setPhone(String phone) { this.phone = phone; }
        public List<String> getSkills() { return skills; }
        public void setSkills(List<String> skills) { this.skills = skills; }
    }
    
    public static class JobSnapshot {
        private String title;
        private String company;
        
        // Getters and setters
        public String getTitle() { return title; }
        public void setTitle(String title) { this.title = title; }
        public String getCompany() { return company; }
        public void setCompany(String company) { this.company = company; }
    }
    
    public static class Note {
        private String content;
        private ObjectId addedBy;
        private LocalDateTime addedAt;
        
        // Getters and setters
        public String getContent() { return content; }
        public void setContent(String content) { this.content = content; }
        public ObjectId getAddedBy() { return addedBy; }
        public void setAddedBy(ObjectId addedBy) { this.addedBy = addedBy; }
        public LocalDateTime getAddedAt() { return addedAt; }
        public void setAddedAt(LocalDateTime addedAt) { this.addedAt = addedAt; }
    }
    
    // Enum
    public enum ApplicationStatus {
        PENDING, REVIEWING, REJECTED, ACCEPTED
    }
    
    // Getters and setters
    public ObjectId getId() { return id; }
    public void setId(ObjectId id) { this.id = id; }
    public ObjectId getJobId() { return jobId; }
    public void setJobId(ObjectId jobId) { this.jobId = jobId; }
    public ObjectId getApplicantId() { return applicantId; }
    public void setApplicantId(ObjectId applicantId) { this.applicantId = applicantId; }
    public String getCoverLetter() { return coverLetter; }
    public void setCoverLetter(String coverLetter) { this.coverLetter = coverLetter; }
    public Resume getResume() { return resume; }
    public void setResume(Resume resume) { this.resume = resume; }
    public ApplicantSnapshot getApplicantSnapshot() { return applicantSnapshot; }
    public void setApplicantSnapshot(ApplicantSnapshot applicantSnapshot) { this.applicantSnapshot = applicantSnapshot; }
    public JobSnapshot getJobSnapshot() { return jobSnapshot; }
    public void setJobSnapshot(JobSnapshot jobSnapshot) { this.jobSnapshot = jobSnapshot; }
    public ApplicationStatus getStatus() { return status; }
    public void setStatus(ApplicationStatus status) { this.status = status; }
    public List<Note> getNotes() { return notes; }
    public void setNotes(List<Note> notes) { this.notes = notes; }
    public LocalDateTime getAppliedDate() { return appliedDate; }
    public void setAppliedDate(LocalDateTime appliedDate) { this.appliedDate = appliedDate; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}