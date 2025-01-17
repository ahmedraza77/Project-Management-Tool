package com.demo.ppmtool.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
public class Project {
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotBlank(message = "Project Name is required")
	private String projectName;
	
	@NotBlank(message = "Project ID is required")
    @Size(min=4, max=5, message = "Please use 4 to 5 characters")
    @Column(updatable = false, unique = true)
	private String projectId;
    
	@NotBlank(message = "Project Description is required")
	private String projectDescription;
    
    @JsonFormat(pattern = "yyyy-mm-dd")
	private Date start_date;
    @JsonFormat(pattern = "yyyy-mm-dd")
	private Date end_date;
    @JsonFormat(pattern = "yyyy-mm-dd")
	private Date created_at;
    @JsonFormat(pattern = "yyyy-mm-dd")
	private Date updated_at;
    
    public Project() {}
    
    public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getProjectDescription() {
		return projectDescription;
	}

	public void setProjectDescription(String projectDescription) {
		this.projectDescription = projectDescription;
	}

	public Date getStart_date() {
		return start_date;
	}

	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}

	public Date getEnd_date() {
		return end_date;
	}

	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}

	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	public Date getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(Date updated_at) {
		this.updated_at = updated_at;
	}

	@PrePersist
    protected void onCreate(){
        this.created_at = new Date();
    }

    @PreUpdate
    protected void onUpdate(){
        this.updated_at = new Date();
    }

}
