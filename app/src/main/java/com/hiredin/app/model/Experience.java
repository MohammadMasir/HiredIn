package com.hiredin.app.model;

import com.hiredin.app.model.enums.Level;

public class Experience {
	private Level level;
	private int years;
	
	public Level getLevel() {
		return level;
	}
	public void setLevel(Level level) {
		this.level = level;
	}
	public int getYears() {
		return years;
	}
	public void setYears(int years) {
		this.years = years;
	}
}
