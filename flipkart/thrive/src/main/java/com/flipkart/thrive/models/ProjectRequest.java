package com.flipkart.thrive.models;

import com.flipkart.thrive.enums.RequestStatus;

import java.util.UUID;

public class ProjectRequest {
    private final String id;
    private final Project project;
    private final Developer developer;
    private RequestStatus status;

    public ProjectRequest(Project project, Developer developer) {
        this.id = UUID.randomUUID().toString();
        this.project = project;
        this.developer = developer;
        this.status = RequestStatus.PENDING;
    }

    public String getId() {
        return id;
    }

    public Project getProject() {
        return project;
    }

    public Developer getDeveloper() {
        return developer;
    }

    public RequestStatus getStatus() {
        return status;
    }

    public void setStatus(RequestStatus status) {
        this.status = status;
    }
} 