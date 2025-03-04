package com.hiredin.app.model;

public class Location {
	private String city;
	private String state;
	private String country;
	private Boolean remote;
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public Boolean getRemote() {
		return remote;
	}
	public void setRemote(Boolean remote) {
		this.remote = remote;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	
	
}
