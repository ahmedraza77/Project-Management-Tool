package com.demo.ppmtool.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.ppmtool.domain.Project;
import com.demo.ppmtool.exceptions.ProjectIdException;
import com.demo.ppmtool.repository.ProjectRepository;

@Service
public class ProjectServiceImpl implements ProjectService{
	
	@Autowired
	ProjectRepository projectRepo;

	@Override
	public Project createOrUpdateProject(Project project) {
		try {
			project.setProjectId(project.getProjectId().toUpperCase());
			return projectRepo.save(project);	
		}catch(Exception ex){
			throw new ProjectIdException("Project ID '"+project.getProjectId().toUpperCase()+"' already exists");
		}
	}
	
	@Override
	public Project viewProject(String projectId) {
		Optional<Project> project = projectRepo.findByProjectId(projectId.toUpperCase());
		
		if(!project.isPresent()) {
			throw new ProjectIdException("Project ID '"+projectId+"' does not exist");
		}
		return project.get();
	}

	public Iterable<Project> viewAllProject() {
		return projectRepo.findAll();
	}

	@Override
	public void deleteProject(String projectId) {
		Optional<Project> project = projectRepo.findByProjectId(projectId.toUpperCase());
		
		if(!project.isPresent()) {
			throw new ProjectIdException("Cannot, Project ID '"+projectId+"' does not exist");
		}
		projectRepo.delete(project.get());
	}

}
