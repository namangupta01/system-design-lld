package com.flipkart.thrive.repositories;

import com.flipkart.thrive.models.Project;
import com.flipkart.thrive.models.ProjectRequest;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class ProjectRepositoryImpl implements IProjectRepository {
    private final Map<String, Project> projects = new ConcurrentHashMap<>();
    private final Map<String, ProjectRequest> requests = new ConcurrentHashMap<>();

    @Override
    public Project saveProject(Project project) {
        projects.put(project.getId(), project);
        return project;
    }

    @Override
    public ProjectRequest saveProjectRequest(ProjectRequest projectRequest) {
        requests.put(projectRequest.getId(), projectRequest);
        return projectRequest;
    }

    @Override
    public Optional<Project> findProjectById(String id) {
        return Optional.ofNullable(projects.get(id));
    }

    @Override
    public Optional<ProjectRequest> findProjectRequestById(String id) {
        return Optional.ofNullable(requests.get(id));
    }

    @Override
    public Collection<Project> findAllProjects() {
        return projects.values();
    }

    @Override
    public List<ProjectRequest> findAllProjectRequests() {
        return requests.values().stream().collect(Collectors.toList());
    }
} 