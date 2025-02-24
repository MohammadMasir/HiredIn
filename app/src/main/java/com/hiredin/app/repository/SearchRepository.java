package com.hiredin.app.repository;

import java.util.List;

import com.hiredin.app.model.Job;

public interface SearchRepository {
	List<Job> searchByText(String text);
}
