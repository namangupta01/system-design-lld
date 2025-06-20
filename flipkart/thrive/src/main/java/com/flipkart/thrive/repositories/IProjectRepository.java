package com.flipkart.thrive.repositories;

import com.flipkart.thrive.models.Project;
import com.flipkart.thrive.models.ProjectRequest;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface IProjectRepository {
    Project saveProject(Project project);
    ProjectRequest saveProjectRequest(ProjectRequest projectRequest);
    Optional<Project> findProjectById(String id);
    Optional<ProjectRequest> findProjectRequestById(String id);
    Collection<Project> findAllProjects();
    List<ProjectRequest> findAllProjectRequests();
} 