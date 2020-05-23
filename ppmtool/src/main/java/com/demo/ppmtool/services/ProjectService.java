package com.demo.ppmtool.services;

import com.demo.ppmtool.domain.Project;

public interface ProjectService {
	public Project createOrUpdateProject(Project project);
	
	public Project viewProject(String projectId);
	
	public void deleteProject(String projectId);
}
