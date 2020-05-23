package com.demo.ppmtool.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.ppmtool.domain.Project;
import com.demo.ppmtool.services.MapValidationErrorService;
import com.demo.ppmtool.services.ProjectServiceImpl;

@RestController
@RequestMapping(value = "/api/project", produces = { "application/json" })
public class ProjectController {
	
	@Autowired
	ProjectServiceImpl projectService;
	
	@Autowired
	MapValidationErrorService mapValidationErrorService;
	

	@PostMapping(value = "")
	public ResponseEntity<?> createNewProject(@Valid @RequestBody Project project, BindingResult result) {
				
		ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);	
		if(errorMap!=null) return errorMap;
		
		Project savedProject = projectService.createOrUpdateProject(project);
		return new ResponseEntity<Project>(savedProject, HttpStatus.CREATED);
	}
	
	@GetMapping(value = "/{projectId}")
	public ResponseEntity<Project> getProjectById(@PathVariable String projectId) {
		Project project = projectService.viewProject(projectId);
		return new ResponseEntity<Project>(project, HttpStatus.OK);
	}
	
	@GetMapping(value = "/all")
	public Iterable<Project> getAllProjects() {
		return projectService.viewAllProject();
	}
	
	@DeleteMapping(value = "/{projectId}")
	public ResponseEntity<?> deleteProject(@PathVariable String projectId) {
		projectService.deleteProject(projectId);	
		return new ResponseEntity<String>("Project with ID: '"+projectId+"' was deleted", HttpStatus.OK);
	}
	
	@PutMapping(value = "")
	public ResponseEntity<Project> updateProject(@RequestBody @Valid Project project) {
		//long id is must in body
		Project savedProject = projectService.createOrUpdateProject(project);
		return new ResponseEntity<Project>(savedProject, HttpStatus.OK);
	}
	
}
