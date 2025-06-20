package com.flipkart.thrive.models;

import com.flipkart.thrive.enums.ProjectCategory;
import com.flipkart.thrive.enums.ProjectStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.locks.ReentrantLock;

public class Project {
    // A lock is used to ensure atomic updates to a project's state,
    // especially during request handling and assignment to prevent race conditions.
    private final transient ReentrantLock lock = new ReentrantLock();

    private final String id;
    private final String name;
    private final String description;
    private final ProjectCategory category;
    private final Lead lead;
    private ProjectStatus status;
    private Developer assignedDeveloper;
    private final List<ProjectRequest> requests;
    private final LocalDateTime creationTime;
    private Double developerRating;


    public Project(String name, String description, ProjectCategory category, Lead lead) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.description = description;
        this.category = category;
        this.lead = lead;
        this.status = ProjectStatus.OPEN;
        this.requests = new ArrayList<>();
        this.creationTime = LocalDateTime.now();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public ProjectCategory getCategory() {
        return category;
    }

    public Lead getLead() {
        return lead;
    }

    public ProjectStatus getStatus() {
        return status;
    }

    public void setStatus(ProjectStatus status) {
        this.status = status;
    }

    public Developer getAssignedDeveloper() {
        return assignedDeveloper;
    }

    public void setAssignedDeveloper(Developer assignedDeveloper) {
        this.assignedDeveloper = assignedDeveloper;
    }

    public List<ProjectRequest> getRequests() {
        synchronized (requests) {
            return new ArrayList<>(requests);
        }
    }

    public void addRequest(ProjectRequest request) {
        synchronized (requests) {
            this.requests.add(request);
        }
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public Double getDeveloperRating() {
        return developerRating;
    }

    public void setDeveloperRating(Double developerRating) {
        this.developerRating = developerRating;
    }

    public void lock() {
        lock.lock();
    }

    public void unlock() {
        lock.unlock();
    }
} 