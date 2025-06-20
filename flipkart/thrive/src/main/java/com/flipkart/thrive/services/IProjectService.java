package com.flipkart.thrive.services;

import com.flipkart.thrive.enums.ProjectCategory;
import com.flipkart.thrive.models.Project;
import com.flipkart.thrive.models.ProjectRequest;

import java.util.Collection;
import java.util.List;

public interface IProjectService {
    Project registerProject(String leadId, String name, String description, ProjectCategory category);
    List<Project> getAvailableProjects();
    ProjectRequest requestProject(String developerId, String projectId);
    void acceptRequest(String requestId, String projectId, String leadId);
    void cancelProject(String leadId, String projectId);
    void startProject(String developerId, String projectId);
    void completeProject(String developerId, String projectId);
    void rateDeveloperOnProject(String leadId, String projectId, double rating);
    void getProjectDetails(String projectId);
    Project getProject(String projectId);
    Collection<Project> getAllProjects();
    void shutdown();
} 