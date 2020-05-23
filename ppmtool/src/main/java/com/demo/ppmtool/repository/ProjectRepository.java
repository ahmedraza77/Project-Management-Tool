package com.demo.ppmtool.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.demo.ppmtool.domain.Project;

@Repository
public interface ProjectRepository extends CrudRepository<Project, Long>{
	
	Optional<Project> findByProjectId(String projectId);
	
	@Override
	Iterable<Project> findAll();
}
