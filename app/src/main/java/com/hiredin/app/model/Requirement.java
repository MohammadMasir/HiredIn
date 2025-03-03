package com.hiredin.app.model;

import java.util.List;

public class Requirement {
    private List<String> skills;
    private Experience experience;
    private String qualification;

    // Constructors, getters, setters...

    public Requirement() {}

    public List<String> getSkills() {
        return skills;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }

    public Experience getExperience() {
        return experience;
    }

    public void setExperience(Experience experience) {
        this.experience = experience;
    }

	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

}
