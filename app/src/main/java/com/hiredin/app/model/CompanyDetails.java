package com.hiredin.app.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class CompanyDetails {
	
	private String name;
	private String position;
	private String website;
	
	public CompanyDetails() {}

	public enum UserRole {
		ADMIN, EMPLOYER, USER
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}
	
}