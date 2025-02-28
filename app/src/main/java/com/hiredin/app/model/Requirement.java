package com.hiredin.app.model;

import java.util.List;

public class Requirement {
    private List<String> skills;
    private int experience;
    private String qualification;

    // Constructors, getters, setters...

    public Requirement() {}

    public List<String> getSkills() {
        return skills;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

}
